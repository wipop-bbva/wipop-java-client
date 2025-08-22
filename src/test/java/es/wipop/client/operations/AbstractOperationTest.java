package es.wipop.client.operations;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import es.wipop.client.WipopClientConfiguration;
import es.wipop.client.WipopClientHttpConfiguration;
import es.wipop.client.exception.WipopClientException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AbstractOperationTest {

   @RegisterExtension
   static final WireMockExtension wireMock = WireMockExtension.newInstance()
         .options(wireMockConfig().dynamicPort())
         .build();

   @Test
   void shouldTimeoutOnSlowResponse() {
      final var httpConfig = new WipopClientHttpConfiguration(5000, 1000);
      final var config = new WipopClientConfiguration(
            wireMock.baseUrl(), "test-merchant", "test-key", httpConfig);

      wireMock.stubFor(get(urlEqualTo("/test"))
            .willReturn(aResponse()
                  .withStatus(200)
                  .withBody("success")
                  .withFixedDelay(2000)));

      final var operation = new TestOperation(config);

      assertThatThrownBy(operation::testGet)
            .isInstanceOf(WipopClientException.class)
            .hasMessageContaining("I/O error");
   }

   @Test
   void shouldSucceedWithinTimeout() {
      final var httpConfig = new WipopClientHttpConfiguration(5000, 3000);
      final var config = new WipopClientConfiguration(
            wireMock.baseUrl(), "test-merchant", "test-key", httpConfig);

      final var responseBody = "{\"success\": true}";
      wireMock.stubFor(get(urlEqualTo("/test"))
            .willReturn(aResponse()
                  .withStatus(200)
                  .withBody(responseBody)
                  .withFixedDelay(1000)));

      final var operation = new TestOperation(config);
      final var result = operation.testGet();

      assertThat(result).isNotNull()
            .returns(true, TestOperation.TestObject::success);
   }

   private static class TestOperation extends AbstractOperation {
      public TestOperation(WipopClientConfiguration configuration) {
         super(configuration);
      }

      public TestObject testGet() {
         return get("/test", TestObject.class);
      }

      public record TestObject(boolean success) {
      }
   }
}
