//
//  org_cryptomator_jni_MacApplicationUiAppearance.m
//  MacFunctions
//
//  Created by Tobias Hagemann on 25.07.19.
//  Copyright © 2019 Cryptomator. All rights reserved.
//

#import "org_cryptomator_jni_MacApplicationUiAppearance.h"
#import <AppKit/AppKit.h>

#import "SKYAppearanceNotifier.h"
#import "SKYAppearanceObserver.h"

JNIEXPORT jstring JNICALL Java_org_cryptomator_jni_MacApplicationUiAppearance_getCurrentInterfaceStyle0(JNIEnv *env, jobject thisObj) {
	NSString *interfaceStyle = [NSUserDefaults.standardUserDefaults stringForKey:@"AppleInterfaceStyle"] ?: @"Light";
	return (*env)->NewStringUTF(env, [interfaceStyle cStringUsingEncoding:NSUTF8StringEncoding]);
}

JNIEXPORT void JNICALL Java_org_cryptomator_jni_MacApplicationUiAppearance_setToAqua0(JNIEnv *env, jobject thisObj) {
	if (@available(macOS 10.14, *)) {
		NSApp.appearance = [NSAppearance appearanceNamed:NSAppearanceNameAqua];
	}
}

JNIEXPORT jboolean JNICALL Java_org_cryptomator_jni_MacApplicationUiAppearance_setToDarkAqua0(JNIEnv *env, jobject thisObj) {
	if (@available(macOS 10.14, *)) {
		NSApp.appearance = [NSAppearance appearanceNamed:NSAppearanceNameDarkAqua];
		return YES;
	} else {
		return NO;
	}
}

JNIEXPORT void JNICALL Java_org_cryptomator_jni_MacApplicationUiAppearance_addListener0(JNIEnv *env, jobject thisObj, jobject listenerObj) {
	JavaVM *vm = NULL;
	if ((*env)->GetJavaVM(env, &vm) == JNI_OK) {
		jobject listener = (*env)->NewGlobalRef(env, listenerObj);
		SKYAppearanceObserver *observer = [[SKYAppearanceObserver alloc] initWithListener:listener vm:vm];
		[SKYAppearanceNotifier.sharedInstance addObserver:observer];
	}
}
