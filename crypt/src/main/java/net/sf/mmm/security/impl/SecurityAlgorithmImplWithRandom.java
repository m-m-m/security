package net.sf.mmm.security.impl;

import java.security.Provider;
import java.security.SecureRandom;

import net.sf.mmm.security.api.random.SecurityRandomFactory;

/**
 * Extends {@link SecurityAlgorithmImpl} with ability to create
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class SecurityAlgorithmImplWithRandom extends SecurityAlgorithmImpl {

  private final SecurityRandomFactory randomFactory;

  /**
   * The constructor.
   *
   * @param algorithm the {@link #getAlgorithm() algorithm}.
   * @param provider the security {@link Provider}.
   * @param randomFactory the {@link SecurityRandomFactory} to use.
   */
  public SecurityAlgorithmImplWithRandom(String algorithm, Provider provider, SecurityRandomFactory randomFactory) {
    super(algorithm, provider);
    this.randomFactory = randomFactory;
  }

  /**
   * @return the {@link SecurityRandomFactory}.
   */
  protected SecurityRandomFactory getRandomFactory() {

    return this.randomFactory;
  }

  /**
   * @return a new {@link SecureRandom}.
   * @see SecurityRandomFactory#newSecureRandom()
   */
  protected SecureRandom createSecureRandom() {

    return this.randomFactory.newSecureRandom();
  }
}
