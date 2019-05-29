package net.sf.mmm.crypto.hash.ripemd;

import net.sf.mmm.crypto.hash.access.HashTest;

import org.junit.Test;

/**
 * Test of {@link RipeMd160}.
 */
public class RipeMd160Test extends HashTest {

  /** Test of {@link RipeMd160#RIPEMD_160}. */
  @Test
  public void testSingle() {

    RipeMd160 hash = RipeMd160.RIPEMD_160;
    check(hash, "The quick brown fox jumps over the lazy dog", "37f332f68db77bd9d7edd4969571ad671cf9dd3b");
  }

}