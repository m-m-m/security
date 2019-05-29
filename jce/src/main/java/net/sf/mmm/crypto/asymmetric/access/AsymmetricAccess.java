package net.sf.mmm.crypto.asymmetric.access;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import net.sf.mmm.crypto.CryptoAccess;
import net.sf.mmm.crypto.asymmetric.crypt.AsymmetricCryptorConfig;
import net.sf.mmm.crypto.asymmetric.crypt.AsymmetricCryptorFactory;
import net.sf.mmm.crypto.asymmetric.key.AsymmetricKeyCreator;
import net.sf.mmm.crypto.asymmetric.key.AsymmetricKeyFactory;
import net.sf.mmm.crypto.asymmetric.key.AsymmetricKeyPair;
import net.sf.mmm.crypto.asymmetric.sign.SignatureBinary;
import net.sf.mmm.crypto.asymmetric.sign.SignatureConfig;
import net.sf.mmm.crypto.asymmetric.sign.SignatureProcessorFactory;
import net.sf.mmm.crypto.asymmetric.sign.SignatureProcessorFactoryImpl;
import net.sf.mmm.crypto.asymmetric.sign.SignatureSigner;
import net.sf.mmm.crypto.asymmetric.sign.SignatureVerifier;
import net.sf.mmm.crypto.crypt.Decryptor;
import net.sf.mmm.crypto.crypt.DecryptorImplCipher;
import net.sf.mmm.crypto.crypt.Encryptor;
import net.sf.mmm.crypto.crypt.EncryptorImplCiper;
import net.sf.mmm.crypto.hash.HashCreator;
import net.sf.mmm.crypto.hash.HashFactory;
import net.sf.mmm.crypto.hash.access.HashAccess;
import net.sf.mmm.crypto.random.RandomFactory;

/**
 * Abstract base implementation of factory for {@link AsymmetricKeyCreator key management},
 * {@link AsymmetricCryptorFactory encryption/decryption}, and {@link SignatureProcessorFactory
 * signature management} based on {@link net.sf.mmm.crypto.asymmetric.key.AsymmetricKeyPair asymmetric}
 * cryptography.
 *
 * @param <S> type of {@link SignatureBinary}.
 * @param <PR> type of {@link PrivateKey}.
 * @param <PU> type of {@link PublicKey}.
 * @param <PAIR> type of {@link AsymmetricKeyPair}.
 * @param <KC> type of {@link AsymmetricKeyCreator}.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class AsymmetricAccess<S extends SignatureBinary, PR extends PrivateKey, PU extends PublicKey, PAIR extends AsymmetricKeyPair<PR, PU>, KC extends AsymmetricKeyCreator<PR, PU, PAIR>>
    extends CryptoAccess implements AsymmetricKeyFactory<KC>, AsymmetricCryptorFactory<PR, PU>,
    SignatureProcessorFactory<S, PR, PU>, HashFactory {

  /** The {@link AsymmetricCryptorConfig}. */
  protected final AsymmetricCryptorConfig<PR, PU> cryptorConfig;

  private final SignatureConfig<S> signatureConfig;

  /** The {@link RandomFactory}. */
  protected final RandomFactory randomFactory;

  private final SignatureProcessorFactory<S, PR, PU> signatureFactory;

  private final HashAccess hashFactory;

  private KC keyCreator;

  /**
   * The constructor.
   *
   * @param signatureConfig the {@link SignatureConfig}.
   * @param cryptorConfig the {@link AsymmetricCryptorConfig}.
   * @param randomFactory the {@link RandomFactory}.
   */
  public AsymmetricAccess(SignatureConfig<S> signatureConfig, AsymmetricCryptorConfig<PR, PU> cryptorConfig,
      RandomFactory randomFactory) {

    this(signatureConfig, null, cryptorConfig, randomFactory);
  }

  /**
   * The constructor.
   *
   * @param signatureConfig the {@link SignatureConfig}.
   * @param signatureFactory the {@link SignatureProcessorFactory}.
   * @param cryptorConfig the {@link AsymmetricCryptorConfig}.
   * @param randomFactory the {@link RandomFactory}.
   */
  public AsymmetricAccess(SignatureConfig<S> signatureConfig, SignatureProcessorFactory<S, PR, PU> signatureFactory,
      AsymmetricCryptorConfig<PR, PU> cryptorConfig, RandomFactory randomFactory) {

    super();
    this.signatureConfig = signatureConfig;
    if (signatureFactory == null) {
      this.signatureFactory = new SignatureProcessorFactoryImpl<>(signatureConfig, randomFactory);
    } else {
      this.signatureFactory = signatureFactory;
    }
    this.cryptorConfig = cryptorConfig;
    this.randomFactory = randomFactory;
    this.hashFactory = new HashAccess(signatureConfig.getHashConfig());
  }

  /**
   * @return the {@link SignatureConfig}.
   */
  public SignatureConfig<S> getSignatureConfig() {

    return this.signatureConfig;
  }

  /**
   * @return the {@link AsymmetricCryptorConfig}.
   */
  public AsymmetricCryptorConfig<PR, PU> getCryptorConfig() {

    return this.cryptorConfig;
  }

  private KC getKeyCreatorInternal() {

    if (this.keyCreator == null) {
      this.keyCreator = newKeyCreator();
    }
    return this.keyCreator;
  }

  @Override
  public Decryptor newDecryptorUnsafe(Key decryptionKey) {

    return new DecryptorImplCipher(this.randomFactory, this.cryptorConfig, decryptionKey);
  }

  @Override
  public Decryptor newDecryptor(PR privateKey) {

    getKeyCreatorInternal().verifyKey(privateKey);
    return newDecryptorUnsafe(privateKey);
  }

  @Override
  public Encryptor newEncryptorUnsafe(Key encryptionKey) {

    return new EncryptorImplCiper(this.randomFactory, this.cryptorConfig, encryptionKey);
  }

  @Override
  public Encryptor newEncryptor(PU publicKey) {

    getKeyCreatorInternal().verifyKey(publicKey);
    return newEncryptorUnsafe(publicKey);
  }

  @Override
  public SignatureSigner<S> newSigner(PR privateKey) {

    getKeyCreatorInternal().verifyKey(privateKey);
    return this.signatureFactory.newSigner(privateKey);
  }

  @Override
  public SignatureVerifier<S> newVerifier(PU publicKey) {

    getKeyCreatorInternal().verifyKey(publicKey);
    return this.signatureFactory.newVerifier(publicKey);
  }

  @Override
  public S createSignature(byte[] data) {

    return this.signatureFactory.createSignature(data);
  }

  @Override
  public HashCreator newHashCreator() {

    return this.hashFactory.newHashCreator();
  }

  @Override
  public SignatureProcessorFactory<S, PR, PU> getSignatureFactoryWithoutHash() {

    return this.signatureFactory.getSignatureFactoryWithoutHash();
  }

}
