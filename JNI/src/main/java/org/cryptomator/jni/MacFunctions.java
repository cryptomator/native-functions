/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

public class MacFunctions {

	static final String LIB_NAME = "MacFunctions";

	private final MacApplicationUiState uiState = new MacApplicationUiState();
	private final MacKeychainAccess keychainAccess = new MacKeychainAccess();

	MacFunctions() {
	}

	public MacApplicationUiState uiState() {
		return uiState;
	}

	public MacKeychainAccess keychainAccess() {
		return keychainAccess;
	}

}
