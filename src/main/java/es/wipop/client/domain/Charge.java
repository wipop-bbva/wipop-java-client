package es.wipop.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;

/**
 * Represents a charge transaction in the payment system.
 */
public final class Charge extends Transaction {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Refund information if the charge has been refunded */
   @JsonProperty("refund")
   private Refund refund;

   /** Payment method details used for the charge */
   @JsonProperty("payment_method")
   private PaymentMethod paymentMethod;

   /** Customer associated with the charge */
   private Customer customer;

   /**
    * Gets the refund information.
    *
    * @return the refund details, or null if not refunded
    */
   public Refund getRefund() {
      return refund;
   }

   /**
    * Sets the refund information.
    *
    * @param refund the refund details to set
    */
   public void setRefund(Refund refund) {
      this.refund = refund;
   }

   /**
    * Gets the payment method details.
    *
    * @return the payment method used for the charge
    */
   public PaymentMethod getPaymentMethod() {
      return paymentMethod;
   }

   /**
    * Sets the payment method details.
    *
    * @param paymentMethod the payment method to set
    */
   public void setPaymentMethod(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
   }

   /**
    * Gets the customer information.
    *
    * @return the customer associated with the charge
    */
   public Customer getCustomer() {
      return customer;
   }

   /**
    * Sets the customer information.
    *
    * @param customer the customer to set
    */
   public void setCustomer(Customer customer) {
      this.customer = customer;
   }
}
