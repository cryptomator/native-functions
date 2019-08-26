//
//  SKYAppearanceObserver.m
//  MacFunctions
//
//  Created by Tobias Hagemann on 16.08.19.
//  Copyright © 2019 Cryptomator. All rights reserved.
//

#import "SKYAppearanceObserver.h"

@interface SKYAppearanceObserver ()
@property (nonatomic, assign) JavaVM *vm;
@property (nonatomic, assign) jobject listener;
@end

@implementation SKYAppearanceObserver

- (instancetype)initWithListener:(jobject)listener vm:(JavaVM *)vm {
	if (self = [super init]) {
		self.listener = listener;
		self.vm = vm;
	}
	return self;
}

- (void)interfaceThemeChangedNotification:(NSNotification *)notification {
	JNIEnv *env = NULL;
	if ((*self.vm)->GetEnv(self.vm, (void **)&env, JNI_VERSION_10) == JNI_EDETACHED) {
		(*self.vm)->AttachCurrentThread(self.vm, (void **)&env, NULL);
	}
	if (env == NULL) {
		return;
	}
	jclass listenerClass = (*env)->GetObjectClass(env, self.listener);
	if (listenerClass == NULL) {
		return;
	}
	jmethodID listenerMethodID = (*env)->GetMethodID(env, listenerClass, "macInterfaceThemeChanged", "()V");
	if (listenerMethodID == NULL) {
		return;
	}
	(*env)->CallVoidMethod(env, self.listener, listenerMethodID);
}

@end
