//
//  org_cryptomator_ui_jni_MacFunctions.m
//  MacFunctions
//
//  Created by Sebastian Stenzel on 29.08.16.
//  Copyright © 2016 Cryptomator. All rights reserved.
//

#import <jni.h>
#import <ApplicationServices/ApplicationServices.h>

#import "org_cryptomator_ui_jni_MacFunctions.h"


// Implementation of native method sayHello() of HelloJNI class
JNIEXPORT jint JNICALL Java_org_cryptomator_ui_jni_MacFunctions_transformToForegroundApplication0(JNIEnv *env, jobject thisObj) {
	ProcessSerialNumber psn = { 0, kCurrentProcess };
	return TransformProcessType(&psn, kProcessTransformToForegroundApplication);
}


JNIEXPORT jint JNICALL Java_org_cryptomator_ui_jni_MacFunctions_transformToAgentApplication0(JNIEnv *env, jobject thisObj) {
	ProcessSerialNumber psn = { 0, kCurrentProcess };
	return TransformProcessType(&psn, kProcessTransformToUIElementApplication);
}
