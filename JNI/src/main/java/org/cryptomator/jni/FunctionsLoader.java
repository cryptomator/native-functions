/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class FunctionsLoader {

	private static final Logger LOG = LoggerFactory.getLogger(FunctionsLoader.class);
	private static final String OS_NAME = System.getProperty("os.name");
	private static final String OS_NAME_PREFIX_OSX = "Mac OS X";
	private static final String OS_NAME_PREFIX_WIN = "Windows";

	private final AtomicReference<MacFunctions> macFunctions = new AtomicReference<>();
	private final AtomicReference<WinFunctions> winFunctions = new AtomicReference<>();

	public Optional<MacFunctions> macFunctions() {
		if (OS_NAME.startsWith(OS_NAME_PREFIX_OSX)) {
			try {
				System.loadLibrary(MacFunctions.LIB_NAME);
				LOG.info("loaded {}", System.mapLibraryName(MacFunctions.LIB_NAME));
				MacFunctions fn = macFunctions.updateAndGet(setIfNull(MacFunctions::new));
				return Optional.of(fn);
			} catch (UnsatisfiedLinkError e) {
				LOG.error("Could not load JNI lib {} from path {}", System.mapLibraryName(MacFunctions.LIB_NAME), System.getProperty("java.library.path"));
			}
		}
		return Optional.empty();
	}

	public Optional<WinFunctions> winFunctions() {
		if (OS_NAME.startsWith(OS_NAME_PREFIX_WIN)) {
			try {
				System.loadLibrary(WinFunctions.LIB_NAME);
				LOG.info("loaded {}", System.mapLibraryName(WinFunctions.LIB_NAME));
				WinFunctions fn = winFunctions.updateAndGet(setIfNull(WinFunctions::new));
				return Optional.of(fn);
			} catch (UnsatisfiedLinkError e) {
				LOG.error("Could not load JNI lib {} from path {}", System.mapLibraryName(WinFunctions.LIB_NAME), System.getProperty("java.library.path"));
			}
		}
		return Optional.empty();
	}

	private static <V> UnaryOperator<V> setIfNull(Supplier<V> supplier) {
		return v -> v == null ? supplier.get() : v;
	}

}
