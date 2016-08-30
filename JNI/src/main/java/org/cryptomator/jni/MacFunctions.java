package org.cryptomator.jni;

public class MacFunctions {

	private final LazySingleton<MacApplicationUiState> uiState;
	private final LazySingleton<MacKeychainAccess> keychainAccess;

	MacFunctions() {
		this.uiState = new LazySingleton<>(MacApplicationUiState::new);
		this.keychainAccess = new LazySingleton<>(MacKeychainAccess::new);
	}

	public MacApplicationUiState uiState() {
		return uiState.get();
	}

	public MacKeychainAccess getKeychainAccess() {
		return keychainAccess.get();
	}

}
