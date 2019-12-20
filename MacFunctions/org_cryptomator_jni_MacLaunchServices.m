//
//  org_cryptomator_jni_MacLaunchServices.m
//  MacFunctions
//
//  Created by Tobias Hagemann on 16.12.19.
//  Copyright © 2019 Cryptomator. All rights reserved.
//

#import "org_cryptomator_jni_MacLaunchServices.h"
#import <Foundation/Foundation.h>

JNIEXPORT jboolean JNICALL Java_org_cryptomator_jni_MacLaunchServices_isLoginItemEnabled0(JNIEnv *env, jobject thisObj) {
	LSSharedFileListRef sharedFileList = LSSharedFileListCreate(NULL, kLSSharedFileListSessionLoginItems, NULL);
	NSString *applicationPath = NSBundle.mainBundle.bundlePath;
	if (sharedFileList) {
		UInt32 seedValue;
		NSArray *sharedFileListArray = CFBridgingRelease(LSSharedFileListCopySnapshot(sharedFileList, &seedValue));
		for (id sharedFile in sharedFileListArray) {
			LSSharedFileListItemRef sharedFileListItem = (__bridge LSSharedFileListItemRef)sharedFile;
			CFURLRef applicationPathURL = NULL;
			LSSharedFileListItemResolve(sharedFileListItem, 0, (CFURLRef *)&applicationPathURL, NULL);
			if (applicationPathURL != NULL) {
				NSString *resolvedApplicationPath = [(__bridge NSURL *)applicationPathURL path];
				CFRelease(applicationPathURL);
				if ([resolvedApplicationPath compare:applicationPath] == NSOrderedSame) {
					CFRelease(sharedFileList);
					return YES;
				}
			}
		}
		CFRelease(sharedFileList);
	} else {
		NSLog(@"Unable to create the shared file list.");
	}
	return NO;
}

JNIEXPORT jboolean JNICALL Java_org_cryptomator_jni_MacLaunchServices_enableLoginItem0(JNIEnv *env, jobject thisObj) {
	LSSharedFileListRef sharedFileList = LSSharedFileListCreate(NULL, kLSSharedFileListSessionLoginItems, NULL);
	NSString *applicationPath = NSBundle.mainBundle.bundlePath;
	NSURL *applicationPathURL = [NSURL fileURLWithPath:applicationPath];
	if (sharedFileList) {
		LSSharedFileListItemRef sharedFileListItem = LSSharedFileListInsertItemURL(sharedFileList, kLSSharedFileListItemLast, NULL, NULL, (__bridge CFURLRef)applicationPathURL, NULL, NULL);
		if (sharedFileListItem) {
			CFRelease(sharedFileListItem);
		}
		CFRelease(sharedFileList);
		return YES;
	} else {
		NSLog(@"Unable to create the shared file list.");
		return NO;
	}
}

JNIEXPORT jboolean JNICALL Java_org_cryptomator_jni_MacLaunchServices_disableLoginItem0(JNIEnv *env, jobject thisObj) {
	LSSharedFileListRef sharedFileList = LSSharedFileListCreate(NULL, kLSSharedFileListSessionLoginItems, NULL);
	NSString *applicationPath = NSBundle.mainBundle.bundlePath;
	if (sharedFileList) {
		UInt32 seedValue;
		NSArray *sharedFileListArray = CFBridgingRelease(LSSharedFileListCopySnapshot(sharedFileList, &seedValue));
		for (id sharedFile in sharedFileListArray) {
			LSSharedFileListItemRef sharedFileListItem = (__bridge LSSharedFileListItemRef)sharedFile;
			CFURLRef applicationPathURL;
			if (LSSharedFileListItemResolve(sharedFileListItem, 0, &applicationPathURL, NULL) == noErr) {
				NSString *resolvedApplicationPath = [(__bridge NSURL *)applicationPathURL path];
				if ([resolvedApplicationPath compare:applicationPath] == NSOrderedSame) {
					LSSharedFileListItemRemove(sharedFileList, sharedFileListItem);
				}
				CFRelease(applicationPathURL);
			}
		}
		CFRelease(sharedFileList);
		return YES;
	} else {
		NSLog(@"Unable to create the shared file list.");
		return NO;
	}
}
