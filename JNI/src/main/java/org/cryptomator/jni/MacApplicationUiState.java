package org.cryptomator.jni;

public class MacApplicationUiState {

	MacApplicationUiState() {
	}

	/**
	 * Makes the current application a foreground application, which appears in the Dock and the Application Switcher.
	 */
	public void transformToForegroundApplication() {
		boolean success = transformToForegroundApplication0();
		if (!success) {
			throw new JniException("Failed to make app a foreground app.");
		}
	}

	private native boolean transformToForegroundApplication0();

	/**
	 * Makes the current application an agent app. Agent apps do not appear in the Dock or in the Force Quit window.
	 */
	public void transformToAgentApplication() {
		boolean success = transformToAgentApplication0();
		if (!success) {
			throw new JniException("Failed to make app an agent app.");
		}
	}

	private native boolean transformToAgentApplication0();

}
