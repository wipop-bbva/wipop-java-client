package es.wipop.client.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a response from the Wipop API.
 *
 * @param traceId      unique identifier for tracing the request
 * @param timestamp    when the response was generated
 * @param status       overall status of the operation
 * @param responseCode detailed response code information
 */
public record WipopResponse(@JsonProperty("trace_id") String traceId,
                            String timestamp,
                            ResponseStatus status,
                            @JsonProperty("response_code") WipopResponseCode responseCode) {
}
