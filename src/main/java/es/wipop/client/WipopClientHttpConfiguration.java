package es.wipop.client;

/**
 * HTTP configuration for the Wipop client with timeout settings.
 *
 * @param connectionRequestTimeout timeout for connection request in milliseconds
 * @param responseTimeout          timeout for waiting response in milliseconds
 */
public record WipopClientHttpConfiguration(int connectionRequestTimeout, int responseTimeout) {

   /**
    * Creates HTTP configuration with default timeout values.
    * Connection request timeout: 5 seconds
    * Response timeout: 30 seconds
    */
   public WipopClientHttpConfiguration() {
      this(5000, 30000);
   }
}
