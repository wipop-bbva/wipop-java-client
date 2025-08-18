package es.wipop.client.domain.response;

/**
 * Contains detailed information about a response code from the Wipop API.
 *
 * @param level   severity level of the response code
 * @param code    specific response code identifier
 * @param message human-readable message describing the response
 * @param detail  additional details about the response
 */
public record WipopResponseCode(ResponseCodeLevel level,
                                String code,
                                String message,
                                String detail) {
}
