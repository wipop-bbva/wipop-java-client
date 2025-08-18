package es.wipop.client.exception;

import es.wipop.client.domain.response.WipopResponseCode;

/**
 * Exception thrown when an error occurs in the Wipop client operations.
 */
public class WipopClientException extends RuntimeException {

   /** Response code details from the Wipop API */
   private final WipopResponseCode responseCode;

   /**
    * Creates a new exception with response code details.
    *
    * @param responseCode the response code from the Wipop API
    */
   public WipopClientException(WipopResponseCode responseCode) {
      super(responseCode.message());
      this.responseCode = responseCode;
   }

   /**
    * Creates a new exception with a custom message.
    *
    * @param message the error message
    */
   public WipopClientException(String message) {
      super(message);
      this.responseCode = null;
   }

   /**
    * Creates a new exception with a custom message and cause.
    *
    * @param message the error message
    * @param cause   the underlying cause of the exception
    */
   public WipopClientException(String message, Throwable cause) {
      super(message, cause);
      this.responseCode = null;
   }

   /**
    * Gets the response code details from the Wipop API.
    *
    * @return the response code, or null if not available
    */
   public WipopResponseCode getResponseCode() {
      return responseCode;
   }
}
