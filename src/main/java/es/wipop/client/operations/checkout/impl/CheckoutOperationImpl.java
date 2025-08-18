package es.wipop.client.operations.checkout.impl;

import es.wipop.client.WipopClientConfiguration;
import es.wipop.client.domain.Checkout;
import es.wipop.client.operations.AbstractOperation;
import es.wipop.client.operations.checkout.CheckoutOperation;
import es.wipop.client.operations.checkout.params.CheckoutParams;

/**
 * Implementation of checkout operations for the Wipop API.
 */
public class CheckoutOperationImpl extends AbstractOperation implements CheckoutOperation {

   /**
    * Creates a new checkout operation implementation.
    *
    * @param configuration the client configuration
    */
   public CheckoutOperationImpl(WipopClientConfiguration configuration) {
      super(configuration);
   }

   /** {@inheritDoc} */
   @Override
   public Checkout createCheckout(CheckoutParams request) {
      return post("/k/v1/{merchantId}/checkouts", request.asMap(), Checkout.class, configuration.merchantId());
   }

   /** {@inheritDoc} */
   @Override
   public Checkout createCheckout(String customerId, CheckoutParams request) {
      return post("/k/v1/{merchantId}/customers/{customerId}/checkouts", request.asMap(), Checkout.class, configuration.merchantId(), customerId);
   }
}
