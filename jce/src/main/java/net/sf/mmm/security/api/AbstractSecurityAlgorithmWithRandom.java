package net.sf.mmm.security.api;

import java.security.SecureRandom;

import net.sf.mmm.security.api.algorithm.SecurityAlgorithmImpl;
import net.sf.mmm.security.api.provider.SecurityProvider;
import net.sf.mmm.security.api.random.SecurityRandomFactory;
import net.sf.mmm.security.api.random.SecurityRandomFactoryImpl;

/**
 * Extends {@link SecurityAlgorithmImpl} with ability to create
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class AbstractSecurityAlgorithmWithRandom extends AbstractSecurityAlgorithmWithProvider {

  private final SecurityRandomFactory randomFactory;

  /**
   * The constructor.
   *
   * @param provider the {@link SecurityProvider}.
   * @param randomFactory the {@link SecurityRandomFactory} to use.
   */
  public AbstractSecurityAlgorithmWithRandom(SecurityProvider provider, SecurityRandomFactory randomFactory) {

    super(provider);
    if (randomFactory == null) {
      this.randomFactory = SecurityRandomFactoryImpl.ofStrong();
    } else {
      this.randomFactory = randomFactory;
    }
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