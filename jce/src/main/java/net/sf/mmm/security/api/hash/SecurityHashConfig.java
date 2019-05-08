package net.sf.mmm.security.api.hash;

import net.sf.mmm.security.api.AbstractSecurityGetIterationCount;
import net.sf.mmm.security.api.algorithm.SecurityAlgorithmConfig;
import net.sf.mmm.security.api.provider.SecurityProvider;

/**
 * {@link SecurityAlgorithmConfig} for {@link SecurityHashCreator#hash(byte[], boolean) hashing}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class SecurityHashConfig extends SecurityAlgorithmConfig implements AbstractSecurityGetIterationCount {

  private final int iterationCount;

  /**
   * The constructor.
   *
   * @param algorithm the {@link java.security.MessageDigest#getAlgorithm() hash algorithm}.
   */
  public SecurityHashConfig(String algorithm) {

    this(algorithm, null, 1);
  }

  /**
   * The constructor.
   *
   * @param algorithm the {@link java.security.MessageDigest#getAlgorithm() hash algorithm}.
   * @param iterationCount the {@link #getIterationCount() iteration count}.
   */
  public SecurityHashConfig(String algorithm, int iterationCount) {

    this(algorithm, null, iterationCount);
  }

  /**
   * The constructor.
   *
   * @param algorithm the {@link java.security.MessageDigest#getAlgorithm() hash algorithm}.
   * @param provider the {@link SecurityProvider}.
   */
  public SecurityHashConfig(String algorithm, SecurityProvider provider) {

    this(algorithm, provider, 1);
  }

  /**
   * The constructor.
   *
   * @param algorithm the {@link java.security.MessageDigest#getAlgorithm() hash algorithm}.
   * @param provider the {@link SecurityProvider}.
   * @param iterationCount the {@link #getIterationCount() iteration count}.
   */
  public SecurityHashConfig(String algorithm, SecurityProvider provider, int iterationCount) {

    super(algorithm, provider);
    if (iterationCount < 0) {
      throw new IllegalArgumentException("iterationCount:" + iterationCount);
    }
    this.iterationCount = iterationCount;
  }

  @Override
  public int getIterationCount() {

    return this.iterationCount;
  }

  /**
   * @return a {@link SecurityHashConfig} with the same {@link #getAlgorithm() algorithm} but an
   *         {@link #getIterationCount() iteration count} decreased by 1. If the {@link #getIterationCount() iteration
   *         count} is already {@code 1} then {@code null} is returned.
   */
  public SecurityHashConfig decrementIterationCount() {

    if (this.iterationCount <= 0) {
      return null;
    }
    return new SecurityHashConfig(getAlgorithm(), this.provider, this.iterationCount - 1);
  }

}