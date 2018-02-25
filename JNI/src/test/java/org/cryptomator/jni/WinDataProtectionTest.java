/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class WinDataProtectionTest {

	@Ignore
	@Test
	public void testDataProtection() {
		if (!System.getProperty("os.name").startsWith("Windows")) {
			Assert.fail();
		}
		WinDataProtection dataProtection = JniFunctions.winFunctions().get().dataProtection();

		String storedPw = "h€llo wørld123";
		byte[] ciphertext = dataProtection.protect(storedPw.getBytes(), "salt".getBytes());
		Assert.assertNotNull(ciphertext);

		byte[] shouldBeNull = dataProtection.unprotect(ciphertext, "pepper".getBytes());
		Assert.assertNull(shouldBeNull);

		byte[] cleartext = dataProtection.unprotect(ciphertext, "salt".getBytes());
		Assert.assertArrayEquals(storedPw.getBytes(), cleartext);
	}

}
