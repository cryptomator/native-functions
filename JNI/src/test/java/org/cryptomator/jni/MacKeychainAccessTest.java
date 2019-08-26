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

@EnabledOnOs(OS.MAC)
public class MacKeychainAccessTest {

	@Test
	public void testKeychainAccess() {
		MacKeychainAccess keychain = JniFunctions.macFunctions().get().keychainAccess();

		String storedPw = "h€llo wørld123";
		keychain.storePassword("foo", storedPw);
		char[] loadedPw2 = keychain.loadPassword("bar");
		Assertions.assertNull(loadedPw2);

		char[] loadedPw = keychain.loadPassword("foo");
		Assertions.assertArrayEquals(storedPw.toCharArray(), loadedPw);

		keychain.deletePassword("foo");
		char[] deletedPw = keychain.loadPassword("foo");
		Assertions.assertNull(deletedPw);
	}

}
