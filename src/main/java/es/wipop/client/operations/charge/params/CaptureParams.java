package es.wipop.client.operations.charge.params;

import es.wipop.client.requests.RequestBuilder;

import java.math.BigDecimal;

/**
 * Parameters for capturing a pre-authorized charge.
 */
public class CaptureParams extends RequestBuilder {

   /**
    * Sets the amount to capture.
    *
    * @param amount the amount to capture
    * @return this instance for method chaining
    */
   public CaptureParams amount(final BigDecimal amount) {
      return this.with("amount", amount);
   }
}
