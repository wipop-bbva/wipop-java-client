package es.wipop.client.operations.charge;

import es.wipop.client.domain.Charge;
import es.wipop.client.operations.charge.params.*;

/**
 * Interface for charge-related operations in the Wipop API.
 */
public interface ChargeOperation {

   /**
    * Creates a new charge.
    *
    * @param createCardChargeParams the charge creation parameters
    * @return the created charge
    */
   Charge create(CreateChargeParams createCardChargeParams);

   /**
    * Creates a new charge for a specific customer.
    *
    * @param customerId             the customer ID
    * @param createCardChargeParams the charge creation parameters
    * @return the created charge
    */
   Charge create(String customerId, CreateChargeParams createCardChargeParams);

   /**
    * Confirms a charge transaction.
    *
    * @param transactionId the transaction ID to confirm
    * @param params        the confirmation parameters
    * @return the confirmed charge
    */
   Charge confirm(String transactionId, ConfirmChargeParams params);

   /**
    * Confirms a charge transaction for a specific customer.
    *
    * @param customerId    the customer ID
    * @param transactionId the transaction ID to confirm
    * @param params        the confirmation parameters
    * @return the confirmed charge
    */
   Charge confirm(String customerId, String transactionId, ConfirmChargeParams params);

   /**
    * Refunds a charge transaction.
    *
    * @param transactionId the transaction ID to refund
    * @param params        the refund parameters
    * @return the refunded charge
    */
   Charge refund(String transactionId, RefundParams params);

   /**
    * Reverses a charge transaction.
    *
    * @param transactionId the transaction ID to reverse
    * @param params        the reversal parameters
    * @return the reversed charge
    */
   Charge reversal(String transactionId, ReversalParams params);

   /**
    * Captures a pre-authorized charge.
    *
    * @param transactionId the transaction ID to capture
    * @param params        the capture parameters
    * @return the captured charge
    */
   Charge capture(String transactionId, CaptureParams params);
}
