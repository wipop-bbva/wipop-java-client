package es.wipop.client.domain;

import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;

/**
 * Represents a refund transaction.
 */
public class Refund extends Transaction {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;
}
