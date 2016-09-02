/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.jni;

import java.util.function.Consumer;

/**
 * Thrown to indicate that a JNI call didn't succeed, i.e. returned an unexpected return value.
 */
public class JniException extends RuntimeException {

	protected JniException(String message) {
		super(message);
	}

	public static <T> Consumer<T> ignore(Consumer<T> consumer) {
		return value -> {
			try {
				consumer.accept(value);
			} catch (JniException e) {
				// no-op
			}
		};
	}

}
