//
//  org_cryptomator_ui_jni_MacFunctions.m
//  MacFunctions
//
//  Created by Sebastian Stenzel on 29.08.16.
//  Copyright © 2016 Cryptomator. All rights reserved.
//

#import <jni.h>
#import <Security/Security.h>

#import "org_cryptomator_jni_MacKeychainAccess.h"

static const char CRYPTOMATOR[] = "Cryptomator";


JNIEXPORT jint JNICALL Java_org_cryptomator_jni_MacKeychainAccess_storePassword0(JNIEnv *env, jobject thisObj, jbyteArray key, jbyteArray password) {
	const int keyLen = (*env)->GetArrayLength(env, key);
	jbyte *keyStr = (*env)->GetByteArrayElements(env, key, NULL);
	const int pwLen = (*env)->GetArrayLength(env, password);
	jbyte *pwStr = (*env)->GetByteArrayElements(env, password, NULL);
	
	// find existing:
	SecKeychainItemRef itemRef = NULL;
	OSStatus status = SecKeychainFindGenericPassword(
													NULL,                // default keychain
													sizeof(CRYPTOMATOR), // length of service name
													CRYPTOMATOR,         // service name
													keyLen,              // length of account name
													(char *) keyStr,     // account name
													NULL,                // length of password
													NULL,                // pointer to password data
													&itemRef             // the item reference
												);
	if (status == noErr) {
		// update existing:
		status = SecKeychainItemModifyAttributesAndData(
													itemRef, // the item reference
													NULL,    // no change to attributes
													pwLen,   // length of password
													pwStr    // pointer to password data
												);
		return status;
	} else if (status == errSecItemNotFound) {
		// add new:
		status = SecKeychainAddGenericPassword(
											NULL,                // default keychain
											sizeof(CRYPTOMATOR), // length of service name
											CRYPTOMATOR,         // service name
											keyLen,              // length of account name
											(char *) keyStr,     // account name
											pwLen,               // length of password
											pwStr,               // pointer to password data
											NULL                 // the item reference
										);
	}
	
	(*env)->ReleaseByteArrayElements(env, key, keyStr, JNI_ABORT);
	(*env)->ReleaseByteArrayElements(env, password, pwStr, JNI_ABORT);
	if (itemRef) CFRelease(itemRef);
	return status;
}


JNIEXPORT jbyteArray JNICALL Java_org_cryptomator_jni_MacKeychainAccess_loadPassword0(JNIEnv *env, jobject thisObj, jbyteArray key) {
	const int keyLen = (*env)->GetArrayLength(env, key);
	jbyte *keyStr = (*env)->GetByteArrayElements(env, key, NULL);
	void *pwStr = NULL;
	UInt32 pwLen;
	OSStatus status = SecKeychainFindGenericPassword(
											NULL,                // default keychain
											sizeof(CRYPTOMATOR), // length of service name
											CRYPTOMATOR,         // service name
											keyLen,              // length of account name
											(char *) keyStr,     // account name
											&pwLen,              // length of password
											&pwStr,              // pointer to password data
											NULL                 // the item reference
										);
	
	jbyteArray result;
	if (status == noErr) {
		result = (*env)->NewByteArray(env, pwLen);
		(*env)->SetByteArrayRegion(env, result, 0, pwLen, pwStr);
	} else {
		result = NULL;
	}
	
	(*env)->ReleaseByteArrayElements(env, key, keyStr, JNI_ABORT);
	if (pwStr) SecKeychainItemFreeContent(NULL, pwStr);
	return result;
}