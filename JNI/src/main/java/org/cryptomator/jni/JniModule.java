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

	public static Optional<MacFunctions> macFunctions() {
		return MAC_FUNCTIONS.get();
	}

}
