/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import java.util.Optional;

public class JniModule {

	private static final LazySingleton<Optional<MacFunctions>> MAC_FUNCTIONS = new LazySingleton<>(MacFunctions::loadMacFunctions);
	private static final LazySingleton<Optional<WinFunctions>> WIN_FUNCTIONS = new LazySingleton<>(WinFunctions::loadWinFunctions);

	public static Optional<MacFunctions> macFunctions() {
		return MAC_FUNCTIONS.get();
	}

	public static Optional<WinFunctions> winFunctions() {
		return WIN_FUNCTIONS.get();
	}

}
