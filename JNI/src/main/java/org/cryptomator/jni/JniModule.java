package org.cryptomator.jni;

import java.util.Optional;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JniModule {

	private static final Logger LOG = LoggerFactory.getLogger(JniModule.class);
	private static final LazySingleton<MacFunctions> MAC_FUNCTIONS = new LazySingleton<>(MacFunctions::new);

	public static Optional<MacFunctions> macFunctions() {
		if (SystemUtils.IS_OS_MAC_OSX) {
			try {
				System.loadLibrary("MacFunctions");
				LOG.info("loaded {}", System.mapLibraryName("MacFunctions"));
				return Optional.of(MAC_FUNCTIONS.get());
			} catch (UnsatisfiedLinkError e) {
				LOG.error("Could not load JNI lib from path {}", System.getProperty("java.library.path"));
			}
		}
		return Optional.empty();
	}

}
