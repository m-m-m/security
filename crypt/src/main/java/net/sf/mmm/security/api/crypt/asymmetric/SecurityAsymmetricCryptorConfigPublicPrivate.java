package net.sf.mmm.security.api.crypt.asymmetric;

import net.sf.mmm.security.api.key.asymmetric.SecurityAsymmetricKeyConfig;

/**
 * {@link SecurityAsymmetricCryptorConfig} for {@link SecurityAsymmetricCryptorFactoryPrivatePublic asymmetric
 * private-public cryptography}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class SecurityAsymmetricCryptorConfigPublicPrivate extends SecurityAsymmetricCryptorConfig {

  /**
   * The constructor.
   *
   * @param algorithm the {@link javax.crypto.Cipher#getAlgorithm() algorithm} for encryption and decryption.
   * @param keyAlgorithmConfig the corresponding {@link SecurityAsymmetricKeyConfig}.
   * @param nonceSize the {@link #getNonceSize() nonce size}.
   */
  public SecurityAsymmetricCryptorConfigPublicPrivate(String algorithm, SecurityAsymmetricKeyConfig keyAlgorithmConfig,
      int nonceSize) {

    super(algorithm, keyAlgorithmConfig, nonceSize);
  }

  @Override
  public boolean isPrivatePublic() {

    return false;
  }

  @Override
  public boolean isPublicPrivate() {

    return true;
  }

}
