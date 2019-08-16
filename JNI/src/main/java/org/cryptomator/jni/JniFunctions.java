package org.cryptomator.jni;

import java.util.Optional;

public class JniFunctions {

	private static final FunctionsLoader LOADER = new FunctionsLoader();

	public static Optional<MacFunctions> macFunctions() {
		return LOADER.macFunctions();
	}

	public static Optional<WinFunctions> winFunctions() {
		return LOADER.winFunctions();
	}

}
