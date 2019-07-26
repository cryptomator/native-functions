package org.cryptomator.jni;

import java.util.Optional;

public class JniFunctions {

	private static final JniComponent COMP = DaggerJniComponent.create();

	public static Optional<MacFunctions> macFunctions() {
		return COMP.macFunctions();
	}

	public static Optional<WinFunctions> winFunctions() {
		return COMP.winFunctions();
	}

}
