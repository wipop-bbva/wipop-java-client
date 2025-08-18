package es.wipop.client;

/**
 * Configuration for the Wipop client.
 *
 * @param location   the API base URL
 * @param merchantId the merchant identifier
 * @param secretKey  the secret key for authentication
 */
public record WipopClientConfiguration(String location, String merchantId, String secretKey) {

   /**
    * Creates a configuration using a predefined environment.
    *
    * @param environment the environment (SANDBOX or PRODUCTION)
    * @param merchantId  the merchant identifier
    * @param secretKey   the secret key for authentication
    */
   public WipopClientConfiguration(Environment environment, String merchantId, String secretKey) {
      this(environment.getLocation(), merchantId, secretKey);
   }

   /**
    * Predefined environments for the Wipop API.
    */
   public enum Environment {
      /** Sandbox environment for testing */
      SANDBOX("https://sand-api.wipop.es"),
      /** Production environment */
      PRODUCTION("https://api.wipop.es");

      /** The base URL for this environment */
      private final String location;

      /**
       * Creates an environment with the specified location.
       *
       * @param location the base URL
       */
      Environment(String location) {
         this.location = location;
      }

      /**
       * Gets the base URL for this environment.
       *
       * @return the base URL
       */
      public String getLocation() {
         return this.location;
      }
   }
}
