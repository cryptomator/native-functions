/*******************************************************************************
 * Copyright (c) 2019 Tobias Hagemann and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Tobias Hagemann - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import javax.inject.Inject;

public class MacApplicationUiAppearance {

	@Inject
	MacApplicationUiAppearance() {
	}

	/**
	 * Sets the current appearance to aqua (light mode).
	 */
	public void setToAqua() {
		setToAqua0();
	}

	private native void setToAqua0();

	/**
	 * Sets the current appearance to dark aqua (dark mode). Requires at least macOS 10.14.
	 *
	 * @return <code>true</code> if the appearance successfully changed to dark aqua, <code>false</code> if the macOS minimum requirement has not been met.
	 */
	public boolean setToDarkAqua() {
		return setToDarkAqua0();
	}

	private native boolean setToDarkAqua0();

}
