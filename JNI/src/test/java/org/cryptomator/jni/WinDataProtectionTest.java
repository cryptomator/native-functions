/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@EnabledOnOs(OS.WINDOWS)
public class WinDataProtectionTest {

	@Test
	public void testDataProtection() {
		WinDataProtection dataProtection = JniFunctions.winFunctions().get().dataProtection();

		String storedPw = "h€llo wørld123";
		byte[] ciphertext = dataProtection.protect(storedPw.getBytes(), "salt".getBytes());
		Assertions.assertNotNull(ciphertext);

		byte[] shouldBeNull = dataProtection.unprotect(ciphertext, "pepper".getBytes());
		Assertions.assertNull(shouldBeNull);

		byte[] cleartext = dataProtection.unprotect(ciphertext, "salt".getBytes());
		Assertions.assertArrayEquals(storedPw.getBytes(), cleartext);
	}

}
