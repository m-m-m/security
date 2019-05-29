package net.sf.mmm.crypto.symmetric.access.pbe;

import net.sf.mmm.crypto.provider.BouncyCastle;
import net.sf.mmm.crypto.symmetric.access.pbe.PbeAccess;
import net.sf.mmm.crypto.symmetric.crypt.SymmetricCryptorConfig;
import net.sf.mmm.crypto.symmetric.crypt.aes.SymmetricCryptorConfigAes;
import net.sf.mmm.crypto.symmetric.key.pbe.SecuritySymmetricKeyConfigPbe;

/**
 * {@link PbeAccess} for <a href="https://en.wikipedia.org/wiki/PBKDF2">PBKDF2</a> (Password-Based Key
 * Derivation Function 2) from <em>PKCS #5 v2.0</em>.
 *
 * @since 1.0.0
 */
public class Pbkdf2 extends PbeAccess {

  static final SymmetricCryptorConfigAes CRYPTOR_CONFIG_AES = new SymmetricCryptorConfigAes(
      BouncyCastle.getSecurityProvider());

  /**
   * The constructor.
   *
   * @param keyConfig the {@link SecuritySymmetricKeyConfigPbkdf2}.
   */
  Pbkdf2(SecuritySymmetricKeyConfigPbe keyConfig) {

    super(keyConfig, CRYPTOR_CONFIG_AES);
  }

  /**
   * The constructor.
   *
   * @param keyConfig the {@link SecuritySymmetricKeyConfigPbkdf2}.
   * @param cryptorConfig the {@link SymmetricCryptorConfig}.
   */
  Pbkdf2(SecuritySymmetricKeyConfigPbe keyConfig, SymmetricCryptorConfig cryptorConfig) {

    super(keyConfig, cryptorConfig);
  }

  /**
   * The constructor.
   *
   * @param keyConfig the {@link SecuritySymmetricKeyConfigPbkdf2}.
   * @param cryptorConfig the {@link SymmetricCryptorConfig}.
   */
  Pbkdf2(String keyAlgorithm, int keyLength) {

    this(keyAlgorithm, keyLength, CRYPTOR_CONFIG_AES);
  }

  /**
   * The constructor.
   *
   * @param keyConfig the {@link SecuritySymmetricKeyConfigPbkdf2}.
   * @param cryptorConfig the {@link SymmetricCryptorConfig}.
   */
  Pbkdf2(String keyAlgorithm, int keyLength, SymmetricCryptorConfig cryptorConfig) {

    super(new SecuritySymmetricKeyConfigPbe(keyAlgorithm, BouncyCastle.getSecurityProvider(), keyLength), cryptorConfig);
  }

}
