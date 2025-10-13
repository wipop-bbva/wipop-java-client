package es.wipop.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a payment card with its details and capabilities.
 */
public class Card implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Unique identifier for the card */
   private String id;

   /** Name of the issuing bank */
   @JsonProperty("bank_name")
   private String bankName;

   /** Name of the cardholder */
   @JsonProperty("holder_name")
   private String holderName;

   /** Card expiration month */
   @JsonProperty("expiration_month")
   private String expirationMonth;

   /** Card expiration year */
   @JsonProperty("expiration_year")
   private String expirationYear;

   /** Billing address associated with the card */
   private Address address;

   /** Card number (typically masked for security) */
   @JsonProperty("card_number")
   private String cardNumber;

   /** Card brand (e.g., VISA, MASTERCARD) */
   private String brand;

   /** Bank identification code */
   @JsonProperty("bank_code")
   private String bankCode;

   /** Type of the card */
   private String type;

   /** CVV2 security code */
   private String cvv2;

   /** Date when the card was created */
   @JsonProperty("creation_date")
   private LocalDate creationDate;

   /**
    * Gets the card ID.
    *
    * @return the card ID
    */
   public String getId() {
      return id;
   }

   /**
    * Sets the card ID.
    *
    * @param id the card ID to set
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Gets the bank name.
    *
    * @return the bank name
    */
   public String getBankName() {
      return bankName;
   }

   /**
    * Sets the bank name.
    *
    * @param bankName the bank name to set
    */
   public void setBankName(String bankName) {
      this.bankName = bankName;
   }

   /**
    * Gets the cardholder name.
    *
    * @return the cardholder name
    */
   public String getHolderName() {
      return holderName;
   }

   /**
    * Sets the cardholder name.
    *
    * @param holderName the cardholder name to set
    */
   public void setHolderName(String holderName) {
      this.holderName = holderName;
   }

   /**
    * Gets the expiration month.
    *
    * @return the expiration month
    */
   public String getExpirationMonth() {
      return expirationMonth;
   }

   /**
    * Sets the expiration month.
    *
    * @param expirationMonth the expiration month to set
    */
   public void setExpirationMonth(String expirationMonth) {
      this.expirationMonth = expirationMonth;
   }

   /**
    * Gets the expiration year.
    *
    * @return the expiration year
    */
   public String getExpirationYear() {
      return expirationYear;
   }

   /**
    * Sets the expiration year.
    *
    * @param expirationYear the expiration year to set
    */
   public void setExpirationYear(String expirationYear) {
      this.expirationYear = expirationYear;
   }

   /**
    * Gets the billing address.
    *
    * @return the billing address
    */
   public Address getAddress() {
      return address;
   }

   /**
    * Sets the billing address.
    *
    * @param address the billing address to set
    */
   public void setAddress(Address address) {
      this.address = address;
   }

   /**
    * Gets the card number (typically masked).
    *
    * @return the card number
    */
   public String getCardNumber() {
      return cardNumber;
   }

   /**
    * Sets the card number.
    *
    * @param cardNumber the card number to set
    */
   public void setCardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
   }

   /**
    * Gets the card brand (e.g., VISA, MASTERCARD).
    *
    * @return the card brand
    */
   public String getBrand() {
      return brand;
   }

   /**
    * Sets the card brand.
    *
    * @param brand the card brand to set
    */
   public void setBrand(String brand) {
      this.brand = brand;
   }

   /**
    * Gets the bank code.
    *
    * @return the bank code
    */
   public String getBankCode() {
      return bankCode;
   }

   /**
    * Sets the bank code.
    *
    * @param bankCode the bank code to set
    */
   public void setBankCode(String bankCode) {
      this.bankCode = bankCode;
   }

   /**
    * Gets the card type.
    *
    * @return the card type
    */
   public String getType() {
      return type;
   }

   /**
    * Sets the card type.
    *
    * @param type the card type to set
    */
   public void setType(String type) {
      this.type = type;
   }

   /**
    * Gets the CVV2 security code.
    *
    * @return the CVV2 code
    */
   public String getCvv2() {
      return cvv2;
   }

   /**
    * Sets the CVV2 security code.
    *
    * @param cvv2 the CVV2 code to set
    */
   public void setCvv2(String cvv2) {
      this.cvv2 = cvv2;
   }

   /**
    * Gets the card creation date.
    *
    * @return the creation date
    */
   public LocalDate getCreationDate() {
      return creationDate;
   }

   /**
    * Sets the card creation date.
    *
    * @param creationDate the creation date to set
    */
   public void setCreationDate(LocalDate creationDate) {
      this.creationDate = creationDate;
   }
}
