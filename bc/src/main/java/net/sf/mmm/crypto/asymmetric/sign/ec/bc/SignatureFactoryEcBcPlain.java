package net.sf.mmm.crypto.asymmetric.sign.ec.bc;

import java.math.BigInteger;

import net.sf.mmm.crypto.asymmetric.access.ec.bc.CryptoEllipticCurveBc;
import net.sf.mmm.crypto.asymmetric.sign.SignatureFactory;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

/**
 * Implementation of {@link SignatureFactory} for {@link SignatureEcBcPlain}.
 *
 * @since 1.0.0
 */
public class SignatureFactoryEcBcPlain extends SignatureFactoryEcBc<SignatureEcBcPlain> {

  /**
   * The constructor.
   *
   * @param curve the {@link CryptoEllipticCurveBc elliptic curve}.
   */
  public SignatureFactoryEcBcPlain(CryptoEllipticCurveBc curve) {

    super(curve);
  }

  @Override
  public SignatureEcBcPlain createSignature(byte[] data) {

    return new SignatureEcBcPlain(this.curve, data);
  }

  @Override
  public SignatureEcBcPlain create(BigInteger r, BigInteger s, byte[] message, BCECPublicKey publicKey) {

    byte[] data = SignatureEcBcPlain.createData(0, r, s);
    return new SignatureEcBcPlain(this.curve, data, r, s);
  }

}
