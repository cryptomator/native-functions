/*******************************************************************************
 * Copyright (c) 2019 Tobias Hagemann and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Tobias Hagemann - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

public class MacApplicationUiAppearance {

	/**
	 * Gets the current interface style based on AppleInterfaceStyle in NSGlobalDomain.
	 *
	 * @return current {@link MacApplicationUiInterfaceStyle}
	 */
	public MacApplicationUiInterfaceStyle getCurrentInterfaceStyle() {
		String currentAppearanceName = getCurrentInterfaceStyle0();
		if (currentAppearanceName.equals("Light")) {
			return MacApplicationUiInterfaceStyle.LIGHT;
		} else if (currentAppearanceName.equals("Dark")) {
			return MacApplicationUiInterfaceStyle.DARK;
		} else {
			// unknown interface style, defaults to light
			return MacApplicationUiInterfaceStyle.LIGHT;
		}
	}

	private native String getCurrentInterfaceStyle0();

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

	/**
	 * Adds a {@link MacApplicationUiInterfaceThemeListener} which will be notified whenever the interface theme has changed.
	 *
	 * @param listener The listener to register
	 */
	public void addListener(MacApplicationUiInterfaceThemeListener listener) {
		addListener0(listener);
	}

	private native void addListener0(MacApplicationUiInterfaceThemeListener listener);

}
