package es.wipop.client.operations.charge.params;

import es.wipop.client.requests.RequestBuilder;

import java.math.BigDecimal;

/**
 * Parameters for refunding a charge transaction.
 */
public class RefundParams extends RequestBuilder {

   /**
    * Sets the amount to refund.
    *
    * @param amount the amount to refund
    * @return this instance for method chaining
    */
   public RefundParams amount(final BigDecimal amount) {
      return this.with("amount", amount);
   }
}
