package net.sf.mmm.crypto.symmetric.access.pbe;

import javax.crypto.interfaces.PBEKey;

import net.sf.mmm.crypto.symmetric.access.SecurityAccessSymmetricTest;
import net.sf.mmm.crypto.symmetric.access.pbe.Pbkdf2;
import net.sf.mmm.crypto.symmetric.access.pbe.Pbkdf2WithHmacSha224;
import net.sf.mmm.crypto.symmetric.access.pbe.Pbkdf2WithHmacSha256;
import net.sf.mmm.crypto.symmetric.access.pbe.Pbkdf2WithHmacSha384;
import net.sf.mmm.crypto.symmetric.access.pbe.Pbkdf2WithHmacSha512;
import net.sf.mmm.crypto.symmetric.key.SecuritySymmetricKeyCreator;

import org.junit.Test;

/**
 * Test of {@link Pbkdf2}.
 */
public class Pbkdf2Test extends SecurityAccessSymmetricTest {

  /**
   * Test of {@link Pbkdf2WithHmacSha224#of256()}.
   */
  @Test
  public void testPbkdf2HmacSha224() {

    Pbkdf2 pbkdf2 = Pbkdf2WithHmacSha224.of256();
    SecuritySymmetricKeyCreator<PBEKey> keyCreator = pbkdf2.newKeyCreator();
    assertThat(keyCreator.getAlgorithm()).isEqualTo("PBKDF2WithHmacSHA224");
    assertThat(keyCreator.getKeyLength()).isEqualTo(256);
    check(pbkdf2);
  }

  /**
   * Test of {@link Pbkdf2WithHmacSha256#of256()}.
   */
  @Test
  public void testPbkdf2HmacSha256() {

    Pbkdf2 pbkdf2 = Pbkdf2WithHmacSha256.of256();
    SecuritySymmetricKeyCreator<PBEKey> keyCreator = pbkdf2.newKeyCreator();
    assertThat(keyCreator.getAlgorithm()).isEqualTo("PBKDF2WithHmacSHA256");
    assertThat(keyCreator.getKeyLength()).isEqualTo(256);
    check(pbkdf2);
  }

  /**
   * Test of {@link Pbkdf2WithHmacSha384#of256()}.
   */
  @Test
  public void testPbkdf2HmacSha384() {

    Pbkdf2 pbkdf2 = Pbkdf2WithHmacSha384.of256();
    assertThat(pbkdf2.getCryptorConfig().getAlgorithm()).isEqualTo("AES/GCM/NoPadding");
    SecuritySymmetricKeyCreator<PBEKey> keyCreator = pbkdf2.newKeyCreator();
    assertThat(keyCreator.getAlgorithm()).isEqualTo("PBKDF2WithHmacSHA384");
    assertThat(keyCreator.getKeyLength()).isEqualTo(256);
    check(pbkdf2);
  }

  /**
   * Test of {@link Pbkdf2WithHmacSha512#of256()}.
   */
  @Test
  public void testPbkdf2HmacSha512() {

    Pbkdf2 pbkdf2 = Pbkdf2WithHmacSha512.of256();
    assertThat(pbkdf2.getCryptorConfig().getAlgorithm()).isEqualTo("AES/GCM/NoPadding");
    SecuritySymmetricKeyCreator<PBEKey> keyCreator = pbkdf2.newKeyCreator();
    assertThat(keyCreator.getAlgorithm()).isEqualTo("PBKDF2WithHmacSHA512");
    assertThat(keyCreator.getKeyLength()).isEqualTo(256);
    check(pbkdf2);
  }

}