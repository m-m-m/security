package net.sf.mmm.security.api.key.store;

import java.util.Locale;
import java.util.Objects;

import net.sf.mmm.security.api.io.SecurityDataResource;

/**
 * Configuration for {@link SecurityKeyStoreFactoryBuilder#keyStore(SecurityKeyStoreConfig)}.<br>
 * Additional proprietary configs:
 * <ul>
 * <li>"Windows-MY" / "SunMSCAPI"</li>
 * <li>"KeychainStore" / "Apple"</li>
 * </ul>
 *
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class SecurityKeyStoreConfig {

  private final String type;

  private final SecurityDataResource resource;

  private final String password;

  /**
   * The constructor.
   *
   * @param type the {@link #getType() type}.
   * @param resource the {@link #getResource() resource}.
   * @param password the {@link #getPassword() password}.
   */
  public SecurityKeyStoreConfig(String type, SecurityDataResource resource, String password) {
    super();
    Objects.requireNonNull(type, "type");
    Objects.requireNonNull(resource, "resource");
    Objects.requireNonNull(password, "password");
    this.type = type;
    this.resource = resource;
    this.password = password;
  }

  /**
   * @return the (default) file extension for the keystore.
   */
  public String getExtension() {

    return "." + this.type.toLowerCase(Locale.US);
  }

  /**
   * @return type the {@link java.security.KeyStore} {@link java.security.KeyStore#getType() type}. For details see the
   *         available sub-classes as well as this <a href=
   *         "http://www.pixelstech.net/article/1408345768-Different-types-of-keystore-in-Java----Overview">overview</a>.
   */
  public String getType() {

    return this.type;
  }

  /**
   * @return resource the {@link SecurityDataResource} to read/write the {@link java.security.KeyStore} from/to.
   * @see java.security.KeyStore#load(java.io.InputStream, char[])
   * @see java.security.KeyStore#store(java.io.OutputStream, char[])
   */
  public SecurityDataResource getResource() {

    return this.resource;
  }

  /**
   * @return the password used to protect the {@link java.security.KeyStore}.
   * @see java.security.KeyStore#load(java.io.InputStream, char[])
   * @see java.security.KeyStore#store(java.io.OutputStream, char[])
   */
  public String getPassword() {

    return this.password;
  }

}
