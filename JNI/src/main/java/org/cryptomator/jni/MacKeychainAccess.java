/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

import javax.inject.Inject;

public class MacKeychainAccess {

	private static final int OSSTATUS_SUCCESS = 0;
	private static final int OSSTATUS_NOT_FOUND = -25300;

	@Inject
	MacKeychainAccess() {
	}

	/**
	 * Associates the specified password with the specified key in the system keychain.
	 * 
	 * @param account Unique account identifier
	 * @param password Passphrase to store
	 */
	public void storePassword(String account, CharSequence password) {
		ByteBuffer pwBuf = UTF_8.encode(CharBuffer.wrap(password));
		byte[] pwBytes = new byte[pwBuf.remaining()];
		pwBuf.get(pwBytes);
		int errorCode = storePassword0(account.getBytes(UTF_8), pwBytes);
		Arrays.fill(pwBytes, (byte) 0x00);
		Arrays.fill(pwBuf.array(), (byte) 0x00);
		if (errorCode != OSSTATUS_SUCCESS) {
			throw new JniException("Failed to store password. Error code " + errorCode);
		}
	}

	private native int storePassword0(byte[] account, byte[] value);

	/**
	 * Loads the password associated with the specified key from the system keychain.
	 * 
	 * @param account Unique account identifier
	 * @return password or <code>null</code> if no such keychain entry could be loaded from the keychain.
	 */
	public char[] loadPassword(String account) {
		byte[] pwBytes = loadPassword0(account.getBytes(UTF_8));
		if (pwBytes == null) {
			return null;
		} else {
			CharBuffer pwBuf = UTF_8.decode(ByteBuffer.wrap(pwBytes));
			char[] pw = new char[pwBuf.remaining()];
			pwBuf.get(pw);
			Arrays.fill(pwBytes, (byte) 0x00);
			Arrays.fill(pwBuf.array(), (char) 0x00);
			return pw;
		}
	}

	private native byte[] loadPassword0(byte[] account);

	/**
	 * Deletes the password associated with the specified key from the system keychain.
	 * 
	 * @param account Unique account identifier
	 * @return <code>true</code> if the passwords has been deleted, <code>false</code> if no entry for the given key exists.
	 */
	public boolean deletePassword(String account) {
		int errorCode = deletePassword0(account.getBytes(UTF_8));
		if (errorCode == OSSTATUS_SUCCESS) {
			return true;
		} else if (errorCode == OSSTATUS_NOT_FOUND) {
			return false;
		} else {
			throw new JniException("Failed to delete password. Error code " + errorCode);
		}
	}

	private native int deletePassword0(byte[] account);

}
