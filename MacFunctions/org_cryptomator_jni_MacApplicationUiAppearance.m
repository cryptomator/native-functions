//
//  org_cryptomator_jni_MacApplicationUiAppearance.m
//  MacFunctions
//
//  Created by Tobias Hagemann on 25.07.19.
//  Copyright © 2019 Cryptomator. All rights reserved.
//

#import "org_cryptomator_jni_MacApplicationUiAppearance.h"
#import <AppKit/AppKit.h>

JNIEXPORT void JNICALL Java_org_cryptomator_jni_MacApplicationUiAppearance_setToAqua0(JNIEnv *env, jobject thisObj) {
	NSApp.appearance = [NSAppearance appearanceNamed:NSAppearanceNameAqua];
}

JNIEXPORT jboolean JNICALL Java_org_cryptomator_jni_MacApplicationUiAppearance_setToDarkAqua0(JNIEnv *env, jobject thisObj) {
	if (@available(macOS 10.14, *)) {
		NSApp.appearance = [NSAppearance appearanceNamed:NSAppearanceNameDarkAqua];
		return YES;
	} else {
		return NO;
	}
}
