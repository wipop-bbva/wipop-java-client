package es.wipop.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a customer in the payment system.
 */
public class Customer implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Unique identifier for the customer */
   private String id;

   /** Customer's first name */
   private String name;

   /** Customer's email address */
   private String email;

   /** Customer's last name */
   @JsonProperty("last_name")
   private String lastName;

   /** Customer's phone number */
   @JsonProperty("phone_number")
   private String phoneNumber;

   /** Customer's address */
   private Address address;

   /** External system identifier for the customer */
   @JsonProperty("external_id")
   private String externalId;

   /** Date when the customer was created */
   @JsonProperty("creation_date")
   private LocalDate creationDate;

   /**
    * Gets the customer ID.
    *
    * @return the customer ID
    */
   public String getId() {
      return id;
   }

   /**
    * Sets the customer ID.
    *
    * @param id the customer ID to set
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Gets the customer's first name.
    *
    * @return the first name
    */
   public String getName() {
      return name;
   }

   /**
    * Sets the customer's first name.
    *
    * @param name the first name to set
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Gets the customer's email address.
    *
    * @return the email address
    */
   public String getEmail() {
      return email;
   }

   /**
    * Sets the customer's email address.
    *
    * @param email the email address to set
    */
   public void setEmail(String email) {
      this.email = email;
   }

   /**
    * Gets the customer's last name.
    *
    * @return the last name
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * Sets the customer's last name.
    *
    * @param lastName the last name to set
    */
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   /**
    * Gets the customer's phone number.
    *
    * @return the phone number
    */
   public String getPhoneNumber() {
      return phoneNumber;
   }

   /**
    * Sets the customer's phone number.
    *
    * @param phoneNumber the phone number to set
    */
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   /**
    * Gets the customer's address.
    *
    * @return the address
    */
   public Address getAddress() {
      return address;
   }

   /**
    * Sets the customer's address.
    *
    * @param address the address to set
    */
   public void setAddress(Address address) {
      this.address = address;
   }

   /**
    * Gets the external system identifier.
    *
    * @return the external ID
    */
   public String getExternalId() {
      return externalId;
   }

   /**
    * Sets the external system identifier.
    *
    * @param externalId the external ID to set
    */
   public void setExternalId(String externalId) {
      this.externalId = externalId;
   }

   /**
    * Gets the customer creation date.
    *
    * @return the creation date
    */
   public LocalDate getCreationDate() {
      return creationDate;
   }

   /**
    * Sets the customer creation date.
    *
    * @param creationDate the creation date to set
    */
   public void setCreationDate(LocalDate creationDate) {
      this.creationDate = creationDate;
   }
}
