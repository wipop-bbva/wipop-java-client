package es.wipop.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a checkout session for payment processing.
 */
public class Checkout implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Unique identifier for the checkout */
   private String id;

   /** Amount to be charged */
   private BigDecimal amount;

   /** Description of the checkout */
   private String description;

   /** Order identifier */
   @JsonProperty("order_id")
   private String orderId;

   /** Currency code */
   private String currency;

   /** Current status of the checkout */
   private String status;

   /** URL link for the checkout page */
   @JsonProperty("checkout_link")
   private String checkoutLink;

   /** When the checkout expires */
   @JsonProperty("expiration_date")
   private LocalDateTime expirationDate;

   /** When the checkout was created */
   @JsonProperty("creation_date")
   private LocalDateTime creationDate;

   /** Customer associated with the checkout */
   private Customer customer;

   /** Origin channel of the checkout */
   @JsonProperty("origin")
   private String originChannel;

   /**
    * Gets the checkout ID.
    *
    * @return the checkout ID
    */
   public String getId() {
      return id;
   }

   /**
    * Sets the checkout ID.
    *
    * @param id the checkout ID to set
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Gets the amount to be charged.
    *
    * @return the amount
    */
   public BigDecimal getAmount() {
      return amount;
   }

   /**
    * Sets the amount to be charged.
    *
    * @param amount the amount to set
    */
   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }

   /**
    * Gets the checkout description.
    *
    * @return the description
    */
   public String getDescription() {
      return description;
   }

   /**
    * Sets the checkout description.
    *
    * @param description the description to set
    */
   public void setDescription(String description) {
      this.description = description;
   }

   /**
    * Gets the order ID.
    *
    * @return the order ID
    */
   public String getOrderId() {
      return orderId;
   }

   /**
    * Sets the order ID.
    *
    * @param orderId the order ID to set
    */
   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   /**
    * Gets the currency code.
    *
    * @return the currency code
    */
   public String getCurrency() {
      return currency;
   }

   /**
    * Sets the currency code.
    *
    * @param currency the currency code to set
    */
   public void setCurrency(String currency) {
      this.currency = currency;
   }

   /**
    * Gets the checkout status.
    *
    * @return the status
    */
   public String getStatus() {
      return status;
   }

   /**
    * Sets the checkout status.
    *
    * @param status the status to set
    */
   public void setStatus(String status) {
      this.status = status;
   }

   /**
    * Gets the checkout page URL.
    *
    * @return the checkout link
    */
   public String getCheckoutLink() {
      return checkoutLink;
   }

   /**
    * Sets the checkout page URL.
    *
    * @param checkoutLink the checkout link to set
    */
   public void setCheckoutLink(String checkoutLink) {
      this.checkoutLink = checkoutLink;
   }

   /**
    * Gets the expiration date.
    *
    * @return the expiration date
    */
   public LocalDateTime getExpirationDate() {
      return expirationDate;
   }

   /**
    * Sets the expiration date.
    *
    * @param expirationDate the expiration date to set
    */
   public void setExpirationDate(LocalDateTime expirationDate) {
      this.expirationDate = expirationDate;
   }

   /**
    * Gets the creation date.
    *
    * @return the creation date
    */
   public LocalDateTime getCreationDate() {
      return creationDate;
   }

   /**
    * Sets the creation date.
    *
    * @param creationDate the creation date to set
    */
   public void setCreationDate(LocalDateTime creationDate) {
      this.creationDate = creationDate;
   }

   /**
    * Gets the customer information.
    *
    * @return the customer
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

   /**
    * Gets the origin channel.
    *
    * @return the origin channel
    */
   public String getOriginChannel() {
      return originChannel;
   }

   /**
    * Sets the origin channel.
    *
    * @param originChannel the origin channel to set
    */
   public void setOriginChannel(String originChannel) {
      this.originChannel = originChannel;
   }
}
