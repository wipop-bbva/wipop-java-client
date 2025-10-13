package es.wipop.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a physical address with postal information.
 */
public class Address implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Postal code (required) */
   @JsonProperty("zip_code")
   private String zipCode;

   /** First line of address (required) */
   private String line1;

   /** Second line of address (optional) */
   private String line2;

   /** Third line of address (optional) */
   private String line3;

   /** City (required) */
   private String city;

   /** State (required) */
   private String state;

   /** Two-letter ISO 3166-1 country code (optional) */
   @JsonProperty("country_code")
   private String countryCode;

   /**
    * Gets the zip code.
    *
    * @return the zip code
    */
   public String getZipCode() {
      return zipCode;
   }

   /**
    * Sets the zip code.
    *
    * @param zipCode the zip code to set
    */
   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   /**
    * Gets the first line of the address.
    *
    * @return the first address line
    */
   public String getLine1() {
      return line1;
   }

   /**
    * Sets the first line of the address.
    *
    * @param line1 the first address line to set
    */
   public void setLine1(String line1) {
      this.line1 = line1;
   }

   /**
    * Gets the second line of the address.
    *
    * @return the second address line
    */
   public String getLine2() {
      return line2;
   }

   /**
    * Sets the second line of the address.
    *
    * @param line2 the second address line to set
    */
   public void setLine2(String line2) {
      this.line2 = line2;
   }

   /**
    * Gets the third line of the address.
    *
    * @return the third address line
    */
   public String getLine3() {
      return line3;
   }

   /**
    * Sets the third line of the address.
    *
    * @param line3 the third address line to set
    */
   public void setLine3(String line3) {
      this.line3 = line3;
   }

   /**
    * Gets the city.
    *
    * @return the city
    */
   public String getCity() {
      return city;
   }

   /**
    * Sets the city.
    *
    * @param city the city to set
    */
   public void setCity(String city) {
      this.city = city;
   }

   /**
    * Gets the state.
    *
    * @return the state
    */
   public String getState() {
      return state;
   }

   /**
    * Sets the state.
    *
    * @param state the state to set
    */
   public void setState(String state) {
      this.state = state;
   }

   /**
    * Gets the two-letter ISO 3166-1 country code.
    *
    * @return the country code
    */
   public String getCountryCode() {
      return countryCode;
   }

   /**
    * Sets the two-letter ISO 3166-1 country code.
    *
    * @param countryCode the country code to set
    */
   public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
   }
}
