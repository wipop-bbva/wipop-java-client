package es.wipop.client.domain;

import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a payment terminal.
 */
public class Terminal implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Terminal identifier */
   private String id;

   /**
    * Gets the terminal ID.
    *
    * @return the terminal ID
    */
   public String getId() {
      return id;
   }

   /**
    * Sets the terminal ID.
    *
    * @param id the terminal ID to set
    */
   public void setId(String id) {
      this.id = id;
   }
}
