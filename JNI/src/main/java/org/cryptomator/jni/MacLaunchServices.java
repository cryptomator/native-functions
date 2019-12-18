/*******************************************************************************
 * Copyright (c) 2019 Tobias Hagemann and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Tobias Hagemann - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

public class MacLaunchServices {

	/**
	 * Check if login item is currently enabled.
	 *
	 * @return <code>true</code> if login item is currently enabled, <code>false</code> otherwise.
	 */
	public boolean isLoginItemEnabled() {
		return isLoginItemEnabled0();
	}

	private native boolean isLoginItemEnabled0();

	/**
	 * Enable login item. If it is already enabled, nothing happens and it will be handled as successful.
	 *
	 * @return <code>true</code> if enabling login item was successful, <code>false</code> otherwise.
	 */
	public boolean enableLoginItem() {
		return enableLoginItem0();
	}

	private native boolean enableLoginItem0();

	/**
	 * Disable login item. If it is already disabled, nothing happens and it will be handled as successful.
	 *
	 * @return <code>true</code> if disabling login item was successful, <code>false</code> otherwise.
	 */
	public boolean disableLoginItem() {
		return disableLoginItem0();
	}

	private native boolean disableLoginItem0();

}
