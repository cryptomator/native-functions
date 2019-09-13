//
//  org_cryptomator_jni_MacApplicationUiState.m
//  MacFunctions
//
//  Created by Sebastian Stenzel on 29.08.16.
//  Copyright © 2016 Cryptomator. All rights reserved.
//

#import "org_cryptomator_jni_MacApplicationUiState.h"
#import <AppKit/AppKit.h>

JNIEXPORT jboolean JNICALL Java_org_cryptomator_jni_MacApplicationUiState_transformToForegroundApplication0(JNIEnv *env, jobject thisObj) {
	[NSApp activateIgnoringOtherApps:YES];
	if ([NSApp activationPolicy] == NSApplicationActivationPolicyRegular) {
		return YES;
	} else {
		return [NSApp setActivationPolicy:NSApplicationActivationPolicyRegular];
	}
}

JNIEXPORT jboolean JNICALL Java_org_cryptomator_jni_MacApplicationUiState_transformToAgentApplication0(JNIEnv *env, jobject thisObj) {
	if ([NSApp activationPolicy] == NSApplicationActivationPolicyAccessory) {
		return YES;
	} else {
		return [NSApp setActivationPolicy:NSApplicationActivationPolicyAccessory];
	}
}
