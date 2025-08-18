package es.wipop.client.domain;

import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents payment options configuration.
 */
public class PaymentOptions implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Payment configuration options */
   private String payments;

   /**
    * Gets the payment configuration options.
    *
    * @return the payment options
    */
   public String getPayments() {
      return payments;
   }

   /**
    * Sets the payment configuration options.
    *
    * @param payments the payment options to set
    */
   public void setPayments(String payments) {
      this.payments = payments;
   }
}
