package es.wipop.client.operations.checkout;

import es.wipop.client.domain.Checkout;
import es.wipop.client.operations.checkout.params.CheckoutParams;

/**
 * Interface for checkout-related operations in the Wipop API.
 */
public interface CheckoutOperation {

   /**
    * Creates a new checkout.
    *
    * @param request the checkout creation parameters
    * @return the created checkout
    */
   Checkout createCheckout(CheckoutParams request);

   /**
    * Creates a new checkout for a specific customer.
    *
    * @param customerId the customer ID
    * @param request    the checkout creation parameters
    * @return the created checkout
    */
   Checkout createCheckout(String customerId, CheckoutParams request);
}
