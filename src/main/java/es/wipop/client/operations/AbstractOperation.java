package es.wipop.client.operations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.wipop.client.WipopClientConfiguration;
import es.wipop.client.WipopClientHttpConfiguration;
import es.wipop.client.domain.response.WipopResponse;
import es.wipop.client.exception.WipopClientException;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * Abstract base class for all Wipop API operations. Provides common HTTP client functionality and request/response handling.
 */
public abstract class AbstractOperation {

   /** Client configuration */
   protected final WipopClientConfiguration configuration;
   /** HTTP client for making requests */
   private final CloseableHttpClient httpClient;
   /** Base64 encoded authentication token */
   private final String token;
   /** JSON object mapper for serialization/deserialization */
   private final ObjectMapper objectMapper;

   /**
    * Creates a new operation instance with the given configuration.
    *
    * @param configuration the client configuration
    */
   protected AbstractOperation(WipopClientConfiguration configuration) {
      this.configuration = configuration;
      this.token = this.getToken();
      this.httpClient = createHttpClient(configuration.httpConfiguration());
      this.objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
   }

   private CloseableHttpClient createHttpClient(WipopClientHttpConfiguration httpConfig) {
      final var requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(Timeout.ofMilliseconds(httpConfig.connectionRequestTimeout()))
            .setResponseTimeout(Timeout.ofMilliseconds(httpConfig.responseTimeout()))
            .build();

      return HttpClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .build();
   }

   private <T> T invoke(
         HttpMethod method,
         String path,
         Map<String, String> queryParams,
         Object requestBody,
         Class<T> responseType,
         Object... pathVariables
   ) {
      final var uri = this.getUri(path, queryParams, pathVariables);

      HttpUriRequestBase request = null;
      switch (method) {
         case GET -> request = new HttpGet(uri);
         case POST -> {
            request = new HttpPost(uri);
            setRequestBody(requestBody, request);
         }
         case PUT -> {
            request = new HttpPut(uri);
            setRequestBody(requestBody, request);
         }
         case DELETE -> request = new HttpDelete(uri);
         case PATCH -> {
            request = new HttpPatch(uri);
            setRequestBody(requestBody, request);
         }
      }

      request.setHeader("Authorization", "Basic " + this.token);

      try {
         return httpClient.execute(request, handleHttpClientResponse(responseType));
      } catch (IOException e) {
         throw new WipopClientException("An I/O error occurred executing the request", e);
      }
   }

   private void setRequestBody(Object requestBody, HttpUriRequestBase request) {
      if (requestBody != null) {
         final var json = getJson(requestBody);
         request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
      }
   }

   private String getJson(Object requestBody) {
      try {
         return this.objectMapper.writeValueAsString(requestBody);
      } catch (IOException e) {
         throw new WipopClientException("An I/O error occurred writing the request body", e);
      }
   }

   private <T> HttpClientResponseHandler<T> handleHttpClientResponse(Class<T> responseType) {
      return classicHttpResponse -> {
         final var statusCode = classicHttpResponse.getCode();
         final var entity = classicHttpResponse.getEntity();
         // Checks the status of the response
         if (statusCode >= 200 && statusCode < 300) {
            // Checks if there is a request body
            if (entity != null) {
               final var responseBody = EntityUtils.toString(entity);
               final var jsonNode = objectMapper.readTree(responseBody);
               // There are cases when the services return an HTTP 200 OK but the entity is an error
               this.checkResponseError(jsonNode);
               // Otherwise convert the json node to the expected type
               return objectMapper.convertValue(jsonNode, responseType);
            }
         }
         // Checks if there is a request body
         if (entity != null) {
            final var responseBody = EntityUtils.toString(entity);
            // Check if the request body is of type WipopResponse
            final var jsonNode = objectMapper.readTree(responseBody);
            this.checkResponseError(jsonNode);
         }
         // If the response is not handled then throw an exception
         throw new WipopClientException("An unhandled error occurred executing request.");
      };
   }

   private void checkResponseError(JsonNode jsonNode) {
      // Checks if the response contains status with value error and response_code
      if (jsonNode.has("status") &&
            jsonNode.get("status").asText().equalsIgnoreCase("error") &&
            jsonNode.has("response_code")) {
         final var wipopResponse = this.objectMapper.convertValue(jsonNode, WipopResponse.class);
         throw new WipopClientException(wipopResponse.responseCode());
      }
   }

   private URI getUri(String path, Map<String, String> queryParams, Object... pathVariables) {
      URIBuilder uriBuilder;
      final var host = this.configuration.location();
      final var uriStr = host + buildPath(path, pathVariables);

      try {
         uriBuilder = new URIBuilder(uriStr);
         if (queryParams != null) {
            queryParams.forEach(uriBuilder::addParameter);
         }
         return uriBuilder.build();
      } catch (URISyntaxException e) {
         throw new WipopClientException("Invalid URI: " + uriStr, e);
      }
   }

   /**
    * Builds the path by replacing placeholders with provided path variables.
    *
    * @param path          Path template containing placeholders in the format {variableName}
    * @param pathVariables Values to replace in the path template
    * @return Resolved path with placeholders replaced by actual values
    */
   private String buildPath(String path, Object... pathVariables) {
      if (pathVariables == null || pathVariables.length == 0) {
         return path;
      }

      var resolvedPath = path;
      for (var value : pathVariables) {
         final var strValue = String.valueOf(value);
         final var startIdx = resolvedPath.indexOf('{');
         if (startIdx != -1) {
            final var endIdx = resolvedPath.indexOf('}', startIdx);
            if (endIdx != -1) {
               final var placeholder = resolvedPath.substring(startIdx, endIdx + 1);
               resolvedPath = resolvedPath.replace(placeholder, strValue);
            }
         }
      }
      return resolvedPath;
   }

   private String getToken() {
      final var secret = configuration.secretKey() + ":";
      return Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
   }

   /**
    * Performs a GET request with query parameters.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param queryParams   query parameters to include
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T get(String path, Map<String, String> queryParams, Class<T> responseType, Object... pathVariables) {
      return invoke(HttpMethod.GET, path, queryParams, null, responseType, pathVariables);
   }

   /**
    * Performs a GET request without query parameters.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T get(String path, Class<T> responseType, Object... pathVariables) {
      return get(path, null, responseType, pathVariables);
   }

   /**
    * Performs a POST request with query parameters and request body.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param queryParams   query parameters to include
    * @param requestBody   the request body object
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T post(String path, Map<String, String> queryParams, Object requestBody, Class<T> responseType, Object... pathVariables) {
      return invoke(HttpMethod.POST, path, queryParams, requestBody, responseType, pathVariables);
   }

   /**
    * Performs a POST request with request body.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param requestBody   the request body object
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T post(String path, Object requestBody, Class<T> responseType, Object... pathVariables) {
      return post(path, null, requestBody, responseType, pathVariables);
   }

   /**
    * Performs a PUT request with query parameters and request body.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param queryParams   query parameters to include
    * @param requestBody   the request body object
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T put(String path, Map<String, String> queryParams, Object requestBody, Class<T> responseType, Object... pathVariables) {
      return invoke(HttpMethod.PUT, path, queryParams, requestBody, responseType, pathVariables);
   }

   /**
    * Performs a PUT request with request body.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param requestBody   the request body object
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T put(String path, Object requestBody, Class<T> responseType, Object... pathVariables) {
      return put(path, null, requestBody, responseType, pathVariables);
   }

   /**
    * Performs a PATCH request with query parameters and request body.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param queryParams   query parameters to include
    * @param requestBody   the request body object
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T patch(String path, Map<String, String> queryParams, Object requestBody, Class<T> responseType, Object... pathVariables) {
      return invoke(HttpMethod.PATCH, path, queryParams, requestBody, responseType, pathVariables);
   }

   /**
    * Performs a PATCH request with request body.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param requestBody   the request body object
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T patch(String path, Object requestBody, Class<T> responseType, Object... pathVariables) {
      return patch(path, null, requestBody, responseType, pathVariables);
   }

   /**
    * Performs a DELETE request with query parameters.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param queryParams   query parameters to include
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T delete(String path, Map<String, String> queryParams, Class<T> responseType, Object... pathVariables) {
      return invoke(HttpMethod.DELETE, path, queryParams, null, responseType, pathVariables);
   }

   /**
    * Performs a DELETE request without query parameters.
    *
    * @param <T>           the response type
    * @param path          the API path
    * @param responseType  the expected response class
    * @param pathVariables path variables to substitute
    * @return the response object
    */
   protected <T> T delete(String path, Class<T> responseType, Object... pathVariables) {
      return delete(path, null, responseType, pathVariables);
   }

   /**
    * HTTP methods supported by the client.
    */
   public enum HttpMethod {
      /** HTTP GET method */
      GET,
      /** HTTP POST method */
      POST,
      /** HTTP PUT method */
      PUT,
      /** HTTP DELETE method */
      DELETE,
      /** HTTP PATCH method */
      PATCH
   }

}
