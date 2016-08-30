package org.cryptomator.jni;

import org.junit.Assert;
import org.junit.Test;

public class LazySingletonTest {

	@Test
	public void testLazyInitialization() {
		LazySingleton<Object> foo = new LazySingleton<>(() -> new Object());
		Assert.assertSame(foo.get(), foo.get());
	}

}
