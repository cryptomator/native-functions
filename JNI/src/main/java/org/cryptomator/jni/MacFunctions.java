package org.cryptomator.jni;

import java.util.Optional;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MacFunctions {

	private static final Logger LOG = LoggerFactory.getLogger(MacFunctions.class);

	private final LazySingleton<MacApplicationUiState> uiState;
	private final LazySingleton<MacKeychainAccess> keychainAccess;

	private MacFunctions() {
		this.uiState = new LazySingleton<>(MacApplicationUiState::new);
		this.keychainAccess = new LazySingleton<>(MacKeychainAccess::new);
	}

	/**
	 * @return MacFunctions or empty optional if the native bindings could not be loaded for any reason.
	 */
	static Optional<MacFunctions> loadMacFunctions() {
		if (SystemUtils.IS_OS_MAC_OSX) {
			try {
				System.loadLibrary("MacFunctions");
				LOG.info("loaded {}", System.mapLibraryName("MacFunctions"));
				return Optional.of(new MacFunctions());
			} catch (UnsatisfiedLinkError e) {
				LOG.error("Could not load JNI lib from path {}", System.getProperty("java.library.path"));
			}
		}
		return Optional.empty();
	}

	public MacApplicationUiState uiState() {
		return uiState.get();
	}

	public MacKeychainAccess getKeychainAccess() {
		return keychainAccess.get();
	}

}
