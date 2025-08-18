package es.wipop.client;

import es.wipop.client.operations.charge.ChargeOperation;
import es.wipop.client.operations.charge.impl.ChargeOperationImpl;
import es.wipop.client.operations.checkout.CheckoutOperation;
import es.wipop.client.operations.checkout.impl.CheckoutOperationImpl;

/**
 * Main client for interacting with the Wipop API. Provides access to various operation interfaces for payment processing.
 */
public final class WipopClient {

   /** Client configuration */
   private final WipopClientConfiguration configuration;

   /**
    * Creates a new Wipop client with the given configuration.
    *
    * @param configuration the client configuration
    */
   private WipopClient(WipopClientConfiguration configuration) {
      this.configuration = configuration;
   }

   /**
    * Creates a new Wipop client instance.
    *
    * @param configuration the client configuration
    * @return a new WipopClient instance
    */
   public static WipopClient of(WipopClientConfiguration configuration) {
      return new WipopClient(configuration);
   }

   /**
    * Gets the charge operations interface.
    *
    * @return the charge operations
    */
   public ChargeOperation chargeOperation() {
      return new ChargeOperationImpl(configuration);
   }

   /**
    * Gets the checkout operations interface.
    *
    * @return the checkout operations
    */
   public CheckoutOperation checkoutOperation() {
      return new CheckoutOperationImpl(configuration);
   }
}
