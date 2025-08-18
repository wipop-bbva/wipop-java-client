package es.wipop.client.requests;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for building API request parameters using the builder pattern.
 */
public abstract class RequestBuilder {

   /** Map to store request parameters */
   private final Map<String, Object> parameters = new HashMap<>();

   /**
    * Adds a parameter to the request.
    *
    * @param <T>   the concrete builder type
    * @param name  the parameter name
    * @param value the parameter value
    * @return this instance for method chaining
    */
   @SuppressWarnings("unchecked")
   public <T extends RequestBuilder> T with(final String name, final Object value) {
      this.parameters.put(name, value);
      return (T) this;
   }

   /**
    * Returns an unmodifiable map of all parameters.
    *
    * @return the parameters map
    */
   public Map<String, Object> asMap() {
      return Collections.unmodifiableMap(this.parameters);
   }

}
