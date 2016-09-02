package org.cryptomator.jni;

import java.util.Optional;

public class JniModule {

	private static final LazySingleton<Optional<MacFunctions>> MAC_FUNCTIONS = new LazySingleton<>(MacFunctions::loadMacFunctions);

	public static Optional<MacFunctions> macFunctions() {
		return MAC_FUNCTIONS.get();
	}

}
