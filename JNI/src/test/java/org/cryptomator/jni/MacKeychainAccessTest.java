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

public class MacKeychainAccessTest {

	@Ignore
	@Test
	public void testKeychainAccess() {
		if (!System.getProperty("os.name").startsWith("Mac OS X")) {
			Assert.fail();
		}
		MacKeychainAccess keychain = JniFunctions.macFunctions().get().keychainAccess();

		String storedPw = "h€llo wørld123";
		keychain.storePassword("foo", storedPw);
		char[] loadedPw2 = keychain.loadPassword("bar");
		Assert.assertNull(loadedPw2);

		char[] loadedPw = keychain.loadPassword("foo");
		Assert.assertArrayEquals(storedPw.toCharArray(), loadedPw);

		keychain.deletePassword("foo");
		char[] deletedPw = keychain.loadPassword("foo");
		Assert.assertNull(deletedPw);
	}

}
