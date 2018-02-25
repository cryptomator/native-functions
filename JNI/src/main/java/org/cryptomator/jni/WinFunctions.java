/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

public class WinFunctions {

	static final String LIB_NAME = "WinFunctions";

	private final WinDataProtection dataProtection = new WinDataProtection();

	WinFunctions(){}

	public WinDataProtection dataProtection() {
		return dataProtection;
	}

}
