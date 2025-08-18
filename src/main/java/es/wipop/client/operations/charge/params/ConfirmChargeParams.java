package es.wipop.client.operations.charge.params;

import es.wipop.client.domain.PaymentType;
import es.wipop.client.requests.RequestBuilder;

/**
 * Parameters for confirming a charge transaction.
 */
public class ConfirmChargeParams extends RequestBuilder {

   /**
    * Sets the token ID for the charge confirmation.
    *
    * @param tokenId the token ID
    * @return this instance for method chaining
    */
   public ConfirmChargeParams tokenId(final String tokenId) {
      return this.with("token_id", tokenId);
   }

   /**
    * Sets the payment type for the charge.
    *
    * @param paymentsType the payment type
    * @return this instance for method chaining
    */
   public ConfirmChargeParams paymentsType(final PaymentType paymentsType) {
      return this.with("payments_type", paymentsType);
   }

   /**
    * Sets the device session ID for fraud prevention.
    *
    * @param deviceSessionId the device session ID
    * @return this instance for method chaining
    */
   public ConfirmChargeParams deviceSessionId(final String deviceSessionId) {
      return this.with("device_session_id", deviceSessionId);
   }
}
