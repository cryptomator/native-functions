package org.cryptomator.jni;

import java.util.Optional;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {JniModule.class})
interface JniComponent {

	Optional<MacFunctions> macFunctions();

	Optional<WinFunctions> winFunctions();

}
