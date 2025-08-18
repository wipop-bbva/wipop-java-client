package es.wipop.client.domain.version;

/**
 * Provides version information for the Wipop Java Client. This class follows semantic versioning (MAJOR.MINOR.PATCH).
 */
public final class WipopJavaClientVersion {

   /** Major version number */
   private static final int MAJOR = 1;
   /** Minor version number */
   private static final int MINOR = 0;
   /** Patch version number */
   private static final int PATCH = 0;

   /** Serial version UID based on the current version */
   public static final long SERIAL_VERSION = getVersion().hashCode();

   /**
    * Gets the current version string in MAJOR.MINOR.PATCH format.
    *
    * @return the version string
    */
   private static String getVersion() {
      return MAJOR + "." + MINOR + "." + PATCH;
   }
}
