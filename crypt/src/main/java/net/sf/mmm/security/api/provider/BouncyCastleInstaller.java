/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.security.api.provider;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Installer that ensures {@link BouncyCastleProvider} is {@link Security#addProvider(java.security.Provider)
 * registered} to java {@link Security} on {@link #install()}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class BouncyCastleInstaller {

  private static final Logger LOG = LoggerFactory.getLogger(BouncyCastleInstaller.class);

  private static boolean installed = false;

  /**
   * ensures {@link BouncyCastleProvider} is {@link Security#addProvider(java.security.Provider) registered} to java
   * {@link Security}.
   */
  public static void install() {

    if (!installed) {
      if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
        Security.addProvider(new BouncyCastleProvider());
        LOG.debug("Successfully installed bouncy castle provider to java security.");
      }
    }
  }

}
