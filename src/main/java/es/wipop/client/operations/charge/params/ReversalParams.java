package es.wipop.client.operations.charge.params;

import es.wipop.client.domain.ReversalReason;
import es.wipop.client.requests.RequestBuilder;

/**
 * Parameters for reversing a charge transaction.
 */
public class ReversalParams extends RequestBuilder {

   /**
    * Sets the reason for the reversal.
    *
    * @param reason the reversal reason
    * @return this instance for method chaining
    */
   public ReversalParams reason(final ReversalReason reason) {
      return this.with("reason", reason);
   }
}
