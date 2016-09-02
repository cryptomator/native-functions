/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class MacKeychainAccessTest {

	@Ignore
	@Test
	public void testKeychainAccess() {
		if (!SystemUtils.IS_OS_MAC_OSX) {
			Assert.fail();
		}
		MacKeychainAccess keychain = MacFunctions.loadMacFunctions().get().getKeychainAccess();

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
