/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import dagger.Lazy;

import javax.inject.Inject;

public class MacFunctions {

	static final String LIB_NAME = "MacFunctions";

	private final Lazy<MacApplicationUiAppearance> uiAppearance;
	private final Lazy<MacApplicationUiState> uiState;
	private final Lazy<MacKeychainAccess> keychainAccess;

	@Inject
	MacFunctions(Lazy<MacApplicationUiAppearance> uiAppearance, Lazy<MacApplicationUiState> uiState, Lazy<MacKeychainAccess> keychainAccess) {
		this.uiAppearance = uiAppearance;
		this.uiState = uiState;
		this.keychainAccess = keychainAccess;
	}

	public MacApplicationUiAppearance uiAppearance() {
		return uiAppearance.get();
	}

	public MacApplicationUiState uiState() {
		return uiState.get();
	}

	public MacKeychainAccess keychainAccess() {
		return keychainAccess.get();
	}

}
