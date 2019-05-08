package net.sf.mmm.security.api.asymmetric.key;

import java.security.PrivateKey;
import java.security.PublicKey;

import net.sf.mmm.binary.api.Binary;
import net.sf.mmm.security.api.SecurityBinaryFormat;
import net.sf.mmm.security.api.SecurityBinaryType;
import net.sf.mmm.security.api.key.SecurityKeyCreator;

/**
 * Extends {@link SecurityKeyCreator} for dealing with asymmetric cryptographic keys.
 *
 * @see #generateKeyPair()
 * @see #createPrivateKey(byte[], String)
 * @see #createPublicKey(byte[], String)
 * @see #createKeyPair(byte[], String)
 *
 * @param <PR> type of {@link PrivateKey}.
 * @param <PU> type of {@link PublicKey}.
 * @param <PAIR> type of {@link SecurityAsymmetricKeyPair}.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface SecurityAsymmetricKeyCreator<PR extends PrivateKey, PU extends PublicKey, PAIR extends SecurityAsymmetricKeyPair<PR, PU>>
    extends SecurityKeyCreator, SecurityAsymmetricKeyPairFactory<PR, PU, PAIR> {

  /**
   * @return a new {@link SecurityAsymmetricKeyPair} with a generated {@link SecurityAsymmetricKeyPair#getPrivateKey()
   *         private} and {@link SecurityAsymmetricKeyPair#getPublicKey() public} key for the underlying cryptography.
   */
  PAIR generateKeyPair();

  @Override
  default byte[] asData(PR privateKey) {

    return asData(privateKey, SecurityBinaryFormat.FORMAT_COMPACT);
  }

  /**
   * @param privateKey the {@link PrivateKey} to serialize.
   * @param format the the {@link SecurityBinaryFormat} to use.
   * @return the {@link Binary}.
   */
  default Binary asBinary(PR privateKey, String format) {

    return new SecurityBinaryType(asData(privateKey, format));
  }

  /**
   * @param privateKey the {@link PrivateKey} to serialize.
   * @param format the the {@link SecurityBinaryFormat} to use.
   * @return the binary data.
   */
  byte[] asData(PR privateKey, String format);

  @Override
  default PR createPrivateKey(byte[] data) {

    return createPrivateKey(data, null);
  }

  /**
   * @param data the {@link PrivateKey} as raw {@code byte} array.
   * @param format the {@link SecurityBinaryFormat} to use.
   * @return the deserialized {@link PrivateKey}.
   */
  PR createPrivateKey(byte[] data, String format);

  @Override
  default byte[] asData(PU publicKey) {

    return asData(publicKey, SecurityBinaryFormat.FORMAT_COMPACT);
  }

  /**
   * @param publicKey the {@link PublicKey} to serialize.
   * @param format the the {@link SecurityBinaryFormat} to use.
   * @return the {@link Binary}.
   */
  default Binary asBinary(PU publicKey, String format) {

    return new SecurityBinaryType(asData(publicKey, format));
  }

  /**
   * @param publicKey the {@link PublicKey} to serialize.
   * @param format the the {@link SecurityBinaryFormat} to use.
   * @return the binary data.
   */
  byte[] asData(PU publicKey, String format);

  @Override
  default PU createPublicKey(byte[] data) {

    return createPublicKey(data, null);
  }

  /**
   * @param data the {@link PublicKey} as raw {@code byte} array.
   * @param format the the {@link SecurityBinaryFormat} to use.
   * @return the deserialized {@link PublicKey}.
   */
  PU createPublicKey(byte[] data, String format);

  @Override
  default byte[] asData(PAIR keyPair) {

    return asData(keyPair, SecurityBinaryFormat.FORMAT_COMPACT);
  }

  /**
   * @param keyPair the {@link SecurityAsymmetricKeyPair} to serialize.
   * @param format the the {@link SecurityBinaryFormat} to use.
   * @return the serialized {@link Binary}.
   */
  default Binary asBinary(PAIR keyPair, String format) {

    return new SecurityBinaryType(asData(keyPair, format));
  }

  /**
   * @param keyPair the {@link SecurityAsymmetricKeyPair} to serialize.
   * @param format the the {@link SecurityBinaryFormat} to use.
   * @return the serialized binary data.
   */
  byte[] asData(PAIR keyPair, String format);

  @Override
  default PAIR createKeyPair(byte[] data) {

    return createKeyPair(data, SecurityBinaryFormat.FORMAT_COMPACT);
  }

  /**
   * @param data the {@link SecurityAsymmetricKeyPair} as raw byte array.
   * @param format the the {@link SecurityBinaryFormat} to use.
   * @return the deserialized {@link SecurityAsymmetricKeyPair}.
   */
  PAIR createKeyPair(byte[] data, String format);

  /**
   * @param privateKey the {@link PrivateKey}.
   * @return the {@link #getKeyLength() key length} of the given key in bits.
   */
  int getKeyLength(PR privateKey);

  /**
   * Verify that the given key matches the criteria of this key creator such as {@link #getKeyLength() key length}.
   *
   * @param privateKey the {@link PrivateKey} to verify.
   */
  default void verifyKey(PR privateKey) {

    int givenKeyLength = getKeyLength(privateKey);
    int expectedKeyLength = getKeyLength();
    if (givenKeyLength != expectedKeyLength) {
      throw new IllegalArgumentException(
          "Private key has a length of " + givenKeyLength + " bits but expected " + expectedKeyLength + " bits!");
    }
  }

  /**
   * @param publicKey the {@link PublicKey}.
   * @return the {@link #getKeyLength() key length} of the given key in bits.
   */
  int getKeyLength(PU publicKey);

  /**
   * Verify that the given key matches the criteria of this key creator such as {@link #getKeyLength() key length}.
   *
   * @param publicKey the {@link PublicKey} to verify.
   */
  default void verifyKey(PU publicKey) {

    int givenKeyLength = getKeyLength(publicKey);
    int expectedKeyLength = getKeyLength();
    if (givenKeyLength != expectedKeyLength) {
      throw new IllegalArgumentException(
          "Public key has a length of " + givenKeyLength + " bits but expected " + expectedKeyLength + " bits!");
    }
  }

}
