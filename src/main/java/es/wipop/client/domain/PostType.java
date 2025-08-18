package es.wipop.client.domain;

import es.wipop.client.domain.version.WipopJavaClientVersion;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents post-processing type configuration.
 */
public class PostType implements Serializable {

   @Serial
   private static final long serialVersionUID = WipopJavaClientVersion.SERIAL_VERSION;

   /** Post-processing mode */
   private PostTypeMode mode;

   /**
    * Gets the post-processing mode.
    *
    * @return the post-processing mode
    */
   public PostTypeMode getMode() {
      return mode;
   }

   /**
    * Sets the post-processing mode.
    *
    * @param mode the post-processing mode to set
    */
   public void setMode(PostTypeMode mode) {
      this.mode = mode;
   }
}
