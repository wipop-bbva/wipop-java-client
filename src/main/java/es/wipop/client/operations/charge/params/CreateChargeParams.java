package es.wipop.client.operations.charge.params;

import es.wipop.client.domain.*;
import es.wipop.client.requests.RequestBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Parameters for creating a new charge transaction.
 */
public class CreateChargeParams extends RequestBuilder {

   /**
    * Sets the charge method.
    *
    * @param method the charge method
    * @return this instance for method chaining
    */
   public CreateChargeParams card(final ChargeMethod method) {
      return this.with("method", method);
   }

   /**
    * Sets the card information for the charge.
    *
    * @param card the card details
    * @return this instance for method chaining
    */
   public CreateChargeParams card(final Card card) {
      return this.with("card", card);
   }

   /**
    * Sets the source ID for the charge.
    *
    * @param source_id the source ID
    * @return this instance for method chaining
    */
   public CreateChargeParams sourceId(final String source_id) {
      return this.with("source_id", source_id);
   }

   /**
    * Sets the origin channel for the charge.
    *
    * @param originChannel the origin channel
    * @return this instance for method chaining
    */
   public CreateChargeParams originChannel(final OriginChannel originChannel) {
      return this.with("origin_channel", originChannel);
   }

   /**
    * Sets the charge amount.
    *
    * @param amount the amount to charge
    * @return this instance for method chaining
    */
   public CreateChargeParams amount(final BigDecimal amount) {
      return this.with("amount", amount);
   }

   /**
    * Sets the charge description.
    *
    * @param description the charge description
    * @return this instance for method chaining
    */
   public CreateChargeParams description(final String description) {
      return this.with("description", description);
   }

   /**
    * Sets the order ID for the charge.
    *
    * @param orderId the order ID
    * @return this instance for method chaining
    */
   public CreateChargeParams orderId(final String orderId) {
      return this.with("order_id", orderId);
   }

   /**
    * Sets whether to capture the charge immediately.
    *
    * @param capture true to capture immediately, false for pre-authorization
    * @return this instance for method chaining
    */
   public CreateChargeParams capture(final Boolean capture) {
      return this.with("capture", capture);
   }

   /**
    * Sets whether to confirm the charge automatically.
    *
    * @param confirm true to auto-confirm, false otherwise
    * @return this instance for method chaining
    */
   public CreateChargeParams confirm(final Boolean confirm) {
      return this.with("confirm", confirm);
   }

   /**
    * Sets the device session ID for fraud prevention.
    *
    * @param deviceSessionId the device session ID
    * @return this instance for method chaining
    */
   public CreateChargeParams deviceSessionId(final String deviceSessionId) {
      return this.with("device_session_id", deviceSessionId);
   }

   /**
    * Sets the currency for the charge.
    *
    * @param currency the currency
    * @return this instance for method chaining
    */
   public CreateChargeParams currency(final Currency currency) {
      return this.with("currency", currency);
   }

   /**
    * Sets the customer information.
    *
    * @param customer the customer details
    * @return this instance for method chaining
    */
   public CreateChargeParams customer(final Customer customer) {
      return this.with("customer", customer);
   }

   /**
    * Sets the due date for the charge.
    *
    * @param dueDate the due date
    * @return this instance for method chaining
    */
   public CreateChargeParams dueDate(final LocalDateTime dueDate) {
      return this.with("due_date", dueDate);
   }

   /**
    * Sets custom metadata for the charge.
    *
    * @param metadata the metadata key-value pairs
    * @return this instance for method chaining
    */
   public CreateChargeParams metadata(final Map<String, String> metadata) {
      return this.with("metadata", metadata);
   }

   /**
    * Sets the payment options.
    *
    * @param paymentOptions the payment options
    * @return this instance for method chaining
    */
   public CreateChargeParams paymentOptions(final PaymentOptions paymentOptions) {
      return this.with("payment_options", paymentOptions);
   }

   /**
    * Sets whether to send email notifications.
    *
    * @param sendEmail true to send email, false otherwise
    * @return this instance for method chaining
    */
   public CreateChargeParams sendEmail(final boolean sendEmail) {
      return this.with("send_email", sendEmail);
   }

   /**
    * Sets the redirect URL after payment completion.
    *
    * @param redirectUrl the redirect URL
    * @return this instance for method chaining
    */
   public CreateChargeParams redirectUrl(final String redirectUrl) {
      return this.with("redirect_url", redirectUrl);
   }

   /**
    * Sets the product type for the charge.
    *
    * @param productType the product type
    * @return this instance for method chaining
    */
   public CreateChargeParams productType(final ProductType productType) {
      return this.with("product_type", productType);
   }

   /**
    * Sets the post-processing type.
    *
    * @param productType the post type
    * @return this instance for method chaining
    */
   public CreateChargeParams postType(final PostType productType) {
      return this.with("post_type", productType);
   }

   /**
    * Sets the terminal information.
    *
    * @param terminal the terminal details
    * @return this instance for method chaining
    */
   public CreateChargeParams terminal(final Terminal terminal) {
      return this.with("terminal", terminal);
   }

   /**
    * Sets whether to use Card on File (COF) functionality.
    *
    * @param useCof true to use COF, false otherwise
    * @return this instance for method chaining
    */
   public CreateChargeParams useCof(boolean useCof) {
      return with("use_cof", useCof);
   }
}
