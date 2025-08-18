package es.wipop.client.domain;

/**
 * Represents the type of payment method processing.
 */
public enum PaymentMethodType {
   /** Redirect-based payment processing */
   REDIRECT,
   /** 3D Secure authentication required */
   THREE_DS
}
