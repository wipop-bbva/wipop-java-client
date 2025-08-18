package es.wipop.client.operations.checkout.params;

import es.wipop.client.domain.*;
import es.wipop.client.requests.RequestBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Parameters for creating a new checkout session.
 */
public class CheckoutParams extends RequestBuilder {

   /**
    * Sets the origin channel for the checkout.
    *
    * @param originChannel the origin channel
    * @return this instance for method chaining
    */
   public CheckoutParams originChannel(final OriginChannel originChannel) {
      return this.with("origin", originChannel);
   }

   /**
    * Sets the checkout amount.
    *
    * @param amount the amount for the checkout
    * @return this instance for method chaining
    */
   public CheckoutParams amount(final BigDecimal amount) {
      return this.with("amount", amount);
   }

   /**
    * Sets the checkout description.
    *
    * @param description the checkout description
    * @return this instance for method chaining
    */
   public CheckoutParams description(final String description) {
      return this.with("description", description);
   }

   /**
    * Sets the order ID for the checkout.
    *
    * @param orderId the order ID
    * @return this instance for method chaining
    */
   public CheckoutParams orderId(final String orderId) {
      return this.with("order_id", orderId);
   }

   /**
    * Sets whether to capture the payment immediately.
    *
    * @param capture true to capture immediately, false for pre-authorization
    * @return this instance for method chaining
    */
   public CheckoutParams capture(final Boolean capture) {
      return this.with("capture", capture);
   }

   /**
    * Sets the currency for the checkout.
    *
    * @param currency the currency
    * @return this instance for method chaining
    */
   public CheckoutParams currency(final Currency currency) {
      return this.with("currency", currency);
   }

   /**
    * Sets the customer information.
    *
    * @param customer the customer details
    * @return this instance for method chaining
    */
   public CheckoutParams customer(final Customer customer) {
      return this.with("customer", customer);
   }

   /**
    * Sets the expiration date for the checkout.
    *
    * @param expirationDate the expiration date
    * @return this instance for method chaining
    */
   public CheckoutParams dueDate(final LocalDateTime expirationDate) {
      return this.with("expiration_date", expirationDate);
   }

   /**
    * Sets custom metadata for the checkout.
    *
    * @param metadata the metadata key-value pairs
    * @return this instance for method chaining
    */
   public CheckoutParams metadata(final Map<String, String> metadata) {
      return this.with("metadata", metadata);
   }

   /**
    * Sets the payment options.
    *
    * @param paymentOptions the payment options
    * @return this instance for method chaining
    */
   public CheckoutParams paymentOptions(final PaymentOptions paymentOptions) {
      return this.with("payment_options", paymentOptions);
   }

   /**
    * Sets whether to send email notifications.
    *
    * @param sendEmail true to send email, false otherwise
    * @return this instance for method chaining
    */
   public CheckoutParams sendEmail(final boolean sendEmail) {
      return this.with("send_email", sendEmail);
   }

   /**
    * Sets the redirect URL after checkout completion.
    *
    * @param redirectUrl the redirect URL
    * @return this instance for method chaining
    */
   public CheckoutParams redirectUrl(final String redirectUrl) {
      return this.with("redirect_url", redirectUrl);
   }

   /**
    * Sets the product type for the checkout.
    *
    * @param productType the product type
    * @return this instance for method chaining
    */
   public CheckoutParams productType(final ProductType productType) {
      return this.with("product_type", productType);
   }

   /**
    * Sets the post-processing type.
    *
    * @param productType the post type
    * @return this instance for method chaining
    */
   public CheckoutParams postType(final PostType productType) {
      return this.with("post_type", productType);
   }

   /**
    * Sets the terminal information.
    *
    * @param terminal the terminal details
    * @return this instance for method chaining
    */
   public CheckoutParams terminal(final Terminal terminal) {
      return this.with("terminal", terminal);
   }
}
