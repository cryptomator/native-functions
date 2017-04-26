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

import javax.inject.Singleton;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

@Module
public class JniModule {

	private static final Logger LOG = LoggerFactory.getLogger(JniModule.class);

	@Provides
	@Singleton
	public Optional<MacFunctions> macFunctions(Lazy<MacFunctions> macFunctions) {
		if (SystemUtils.IS_OS_MAC_OSX) {
			try {
				System.loadLibrary(MacFunctions.LIB_NAME);
				LOG.info("loaded {}", System.mapLibraryName(MacFunctions.LIB_NAME));
				return Optional.of(macFunctions.get());
			} catch (UnsatisfiedLinkError e) {
				LOG.error("Could not load JNI lib {} from path {}", System.mapLibraryName(MacFunctions.LIB_NAME), System.getProperty("java.library.path"));
			}
		}
		return Optional.empty();
	}

	@Provides
	@Singleton
	public Optional<WinFunctions> winFunctions(Lazy<WinFunctions> winFunction) {
		if (SystemUtils.IS_OS_WINDOWS) {
			try {
				System.loadLibrary(WinFunctions.LIB_NAME);
				LOG.info("loaded {}", System.mapLibraryName(WinFunctions.LIB_NAME));
				return Optional.of(winFunction.get());
			} catch (UnsatisfiedLinkError e) {
				LOG.error("Could not load JNI lib {} from path {}", System.mapLibraryName(WinFunctions.LIB_NAME), System.getProperty("java.library.path"));
			}
		}
		return Optional.empty();
	}

}
