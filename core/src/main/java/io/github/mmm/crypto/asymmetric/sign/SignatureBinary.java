package io.github.mmm.crypto.asymmetric.sign;

import io.github.mmm.crypto.CryptoBinary;

/**
 * Simple datatype as container for a {@link SignatureSigner#signAfterUpdate(boolean) signature}. Allows
 * abstraction of actual implementations (such as bouncy-castle) for portability. Further, it is simple and fast to read
 * and store until real semantic parsing and usage is required.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class SignatureBinary extends CryptoBinary {

  /**
   * The constructor.
   *
   * @param data the raw binary {@link #getData() data}.
   */
  public SignatureBinary(byte[] data) {

    super(data);
  }

}
