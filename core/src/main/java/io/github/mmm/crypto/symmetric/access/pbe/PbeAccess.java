package io.github.mmm.crypto.symmetric.access.pbe;

import javax.crypto.interfaces.PBEKey;

import io.github.mmm.crypto.symmetric.access.SymmetricAccess;
import io.github.mmm.crypto.symmetric.crypt.SymmetricCryptorConfig;
import io.github.mmm.crypto.symmetric.key.pbe.SymmetricKeyConfigPbe;

/**
 * {@link SymmetricAccess} for PBE (Password Based Encryption).
 *
 * @since 1.0.0
 */
public class PbeAccess extends SymmetricAccess<PBEKey> {

  /**
   * The constructor.
   *
   * @param keyConfig the {@link SymmetricKeyConfigPbe}.
   * @param cryptorConfig the {@link SymmetricCryptorConfig}.
   */
  public PbeAccess(SymmetricKeyConfigPbe keyConfig, SymmetricCryptorConfig cryptorConfig) {

    super(keyConfig, cryptorConfig);
  }

}
