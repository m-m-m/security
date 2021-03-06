package io.github.mmm.crypto.symmetric.key;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import io.github.mmm.crypto.key.KeyConfig;
import io.github.mmm.crypto.provider.SecurityProvider;

/**
 * {@link KeyConfig} for symmetric cryptography.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class SymmetricKeyConfig extends KeyConfig {

  private final SymmetricKeySpecFactory keySpecFactory;

  /**
   * The constructor.
   *
   * @param algorithm the {@link #getAlgorithm() algorithm}.
   * @param provider the {@link SecurityProvider}.
   * @param keyLength the {@link #getKeyLength() key length} in bits.
   * @param keySpecFactory the {@link #getKeySpecFactory() key spec factory}.
   */
  public SymmetricKeyConfig(String algorithm, SecurityProvider provider, int keyLength,
      SymmetricKeySpecFactory keySpecFactory) {

    super(algorithm, provider, keyLength);
    this.keySpecFactory = keySpecFactory;
  }

  /**
   * @return the {@link SymmetricKeySpecFactory}.
   */
  public SymmetricKeySpecFactory getKeySpecFactory() {

    return this.keySpecFactory;
  }

  /**
   * @param key the {@link SecretKey}.
   * @param keyFactory the {@link SecretKeyFactory}.
   * @return the {@link #getKeyLength() key length} of the given {@link SecretKey}.
   */
  public abstract int getKeyLength(SecretKey key, SecretKeyFactory keyFactory);
}
