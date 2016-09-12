package org.cryptomator.jni;

import java.util.Optional;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = JniModule.class)
public interface JniTestComponent {

	Optional<MacFunctions> macFunctions();

	Optional<WinFunctions> winFunctions();

}
