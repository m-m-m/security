package net.sf.mmm.crypto.symmetric.crypt;

import net.sf.mmm.crypto.crypt.CipherTransformation;
import net.sf.mmm.crypto.crypt.CryptorConfig;
import net.sf.mmm.crypto.provider.SecurityProvider;

/**
 * {@link CryptorConfig} for {@link SymmetricCryptorFactory symmetric encryption}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class SymmetricCryptorConfig extends CryptorConfig {

  /**
   * The constructor.
   *
   * @param transformation the {@link #getTransformation() transfomation} for the {@link javax.crypto.Cipher}.
   * @param nonceSize the {@link #getNonceSize() nonce size}.
   * @param provider the {@link SecurityProvider}.
   */
  public SymmetricCryptorConfig(CipherTransformation transformation, SecurityProvider provider, int nonceSize) {

    super(transformation, provider, nonceSize);
  }

}
