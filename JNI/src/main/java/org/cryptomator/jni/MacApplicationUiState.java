/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

public class MacApplicationUiState {

	/**
	 * Makes the current application a foreground application, which appears in the Dock and the Application Switcher.
	 */
	public void transformToForegroundApplication() {
		boolean success = transformToForegroundApplication0();
		if (!success) {
			throw new JniException("Failed to make app a foreground app.");
		}
	}

	private native boolean transformToForegroundApplication0();

	/**
	 * Makes the current application an agent app. Agent apps do not appear in the Dock or in the Force Quit window.
	 */
	public void transformToAgentApplication() {
		boolean success = transformToAgentApplication0();
		if (!success) {
			throw new JniException("Failed to make app an agent app.");
		}
	}

	private native boolean transformToAgentApplication0();

}
