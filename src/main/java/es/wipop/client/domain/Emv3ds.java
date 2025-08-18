package es.wipop.client.domain;

/**
 * Represents EMV 3D Secure authentication information.
 */
public class Emv3ds {

   /** 3D Secure information */
   private String threeDSInfo;

   /** 3D Secure protocol version */
   private String protocolVersion;

   /** 3D Secure server transaction ID */
   private String threeDSServerTransID;

   /** 3D Secure method URL */
   private String threeDSMethodURL;

   /**
    * Gets the 3D Secure information.
    *
    * @return the 3D Secure info
    */
   public String getThreeDSInfo() {
      return threeDSInfo;
   }

   /**
    * Sets the 3D Secure information.
    *
    * @param threeDSInfo the 3D Secure info to set
    */
   public void setThreeDSInfo(String threeDSInfo) {
      this.threeDSInfo = threeDSInfo;
   }

   /**
    * Gets the 3D Secure protocol version.
    *
    * @return the protocol version
    */
   public String getProtocolVersion() {
      return protocolVersion;
   }

   /**
    * Sets the 3D Secure protocol version.
    *
    * @param protocolVersion the protocol version to set
    */
   public void setProtocolVersion(String protocolVersion) {
      this.protocolVersion = protocolVersion;
   }

   /**
    * Gets the 3D Secure server transaction ID.
    *
    * @return the server transaction ID
    */
   public String getThreeDSServerTransID() {
      return threeDSServerTransID;
   }

   /**
    * Sets the 3D Secure server transaction ID.
    *
    * @param threeDSServerTransID the server transaction ID to set
    */
   public void setThreeDSServerTransID(String threeDSServerTransID) {
      this.threeDSServerTransID = threeDSServerTransID;
   }

   /**
    * Gets the 3D Secure method URL.
    *
    * @return the method URL
    */
   public String getThreeDSMethodURL() {
      return threeDSMethodURL;
   }

   /**
    * Sets the 3D Secure method URL.
    *
    * @param threeDSMethodURL the method URL to set
    */
   public void setThreeDSMethodURL(String threeDSMethodURL) {
      this.threeDSMethodURL = threeDSMethodURL;
   }
}
