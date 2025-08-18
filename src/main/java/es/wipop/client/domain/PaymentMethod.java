package es.wipop.client.domain;

import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents payment method details and authentication information.
 */
public class PaymentMethod implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Type of payment method */
   private PaymentMethodType type;

   /** URL for payment processing */
   private String url;

   /** EMV 3D Secure authentication details */
   private Emv3ds emv3ds;

   /**
    * Gets the payment method type.
    *
    * @return the payment method type
    */
   public PaymentMethodType getType() {
      return type;
   }

   /**
    * Sets the payment method type.
    *
    * @param type the payment method type to set
    */
   public void setType(PaymentMethodType type) {
      this.type = type;
   }

   /**
    * Gets the payment processing URL.
    *
    * @return the payment URL
    */
   public String getUrl() {
      return url;
   }

   /**
    * Sets the payment processing URL.
    *
    * @param url the payment URL to set
    */
   public void setUrl(String url) {
      this.url = url;
   }

   /**
    * Gets the EMV 3D Secure authentication details.
    *
    * @return the EMV 3D Secure details
    */
   public Emv3ds getEmv3ds() {
      return emv3ds;
   }

   /**
    * Sets the EMV 3D Secure authentication details.
    *
    * @param emv3ds the EMV 3D Secure details to set
    */
   public void setEmv3ds(Emv3ds emv3ds) {
      this.emv3ds = emv3ds;
   }
}
