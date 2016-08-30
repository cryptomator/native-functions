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
		System.loadLibrary("MacFunctions");
		MacKeychainAccess keychain = new MacKeychainAccess();

		String storedPw = "h€llo wørld123";
		keychain.storePassword("foo", storedPw);
		char[] loadedPw2 = keychain.loadPassword("bar");
		Assert.assertNull(loadedPw2);
		char[] loadedPw = keychain.loadPassword("foo");

		Assert.assertArrayEquals(storedPw.toCharArray(), loadedPw);
	}

}
