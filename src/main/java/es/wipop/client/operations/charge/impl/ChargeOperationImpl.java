package es.wipop.client.operations.charge.impl;

import es.wipop.client.WipopClientConfiguration;
import es.wipop.client.domain.Charge;
import es.wipop.client.operations.AbstractOperation;
import es.wipop.client.operations.charge.ChargeOperation;
import es.wipop.client.operations.charge.params.*;

/**
 * Implementation of charge operations for the Wipop API.
 */
public class ChargeOperationImpl extends AbstractOperation implements ChargeOperation {

   /**
    * Creates a new charge operation implementation.
    *
    * @param configuration the client configuration
    */
   public ChargeOperationImpl(WipopClientConfiguration configuration) {
      super(configuration);
   }

   /** {@inheritDoc} */
   @Override
   public Charge create(CreateChargeParams createCardChargeParams) {
      return post("/c/v1/{merchantId}/charges", createCardChargeParams.asMap(), Charge.class, configuration.merchantId());
   }

   /** {@inheritDoc} */
   @Override
   public Charge create(String customerId, CreateChargeParams createCardChargeParams) {
      return post("/c/v1/{merchantId}/customers/{customerId}/charges", createCardChargeParams.asMap(), Charge.class, configuration.merchantId(), customerId);
   }

   /** {@inheritDoc} */
   @Override
   public Charge confirm(String transactionId, ConfirmChargeParams params) {
      return post("/c/v1/{merchantId}/charges/{transactionId}/confirm", params.asMap(), Charge.class, configuration.merchantId(), transactionId);
   }

   /** {@inheritDoc} */
   @Override
   public Charge confirm(String customerId, String transactionId, ConfirmChargeParams params) {
      return post("/c/v1/{merchantId}/customers/{customerId}/charges/{transactionId}/confirm", params.asMap(), Charge.class, configuration.merchantId(), customerId, transactionId);
   }

   /** {@inheritDoc} */
   @Override
   public Charge refund(String transactionId, RefundParams params) {
      return post("/c/v1/{merchantId}/charges/{transactionId}/refund", params.asMap(), Charge.class, configuration.merchantId(), transactionId);
   }

   /** {@inheritDoc} */
   @Override
   public Charge reversal(String transactionId, ReversalParams params) {
      return post("/c/v1/{merchantId}/charges/{transactionId}/reversal", params.asMap(), Charge.class, configuration.merchantId(), transactionId);
   }

   /** {@inheritDoc} */
   @Override
   public Charge capture(String transactionId, CaptureParams params) {
      return post("/c/v1/{merchantId}/charges/{transactionId}/capture", params.asMap(), Charge.class, configuration.merchantId(), transactionId);
   }
}
