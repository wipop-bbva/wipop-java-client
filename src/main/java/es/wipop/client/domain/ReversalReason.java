package es.wipop.client.domain;

/**
 * Represents the reason for a transaction reversal.
 */
public enum ReversalReason {
   /** Reversal due to timeout */
   TIMEOUT,
   /** Reversal due to error */
   ERROR,
   /** Pre-authorization reversal */
   PRE_REVERSAL,
   /** Reversal before expiration */
   PRE_EXPIRATION,
   /** AAC second cryptogram reversal */
   AAC_SECOND_CRYPTOGRAM,
   /** Other reversal reason */
   OTHER
}
