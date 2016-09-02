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

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WinFunctions {

	private static final Logger LOG = LoggerFactory.getLogger(WinFunctions.class);
	private static final String LIB_NAME = "WinFunctions";

	private final LazySingleton<WinDataProtection> dataProtection;

	private WinFunctions() {
		this.dataProtection = new LazySingleton<>(WinDataProtection::new);
	}

	/**
	 * @return WinFunctions or empty optional if the native bindings could not be loaded for any reason.
	 */
	static Optional<WinFunctions> loadWinFunctions() {
		if (SystemUtils.IS_OS_WINDOWS) {
			try {
				System.loadLibrary(LIB_NAME);
				LOG.info("loaded {}", System.mapLibraryName(LIB_NAME));
				return Optional.of(new WinFunctions());
			} catch (UnsatisfiedLinkError e) {
				e.printStackTrace();
				LOG.error("Could not load JNI lib {} from path {}", System.mapLibraryName(LIB_NAME), System.getProperty("java.library.path"));
			}
		}
		return Optional.empty();
	}

	public WinDataProtection dataProtection() {
		return dataProtection.get();
	}

}
