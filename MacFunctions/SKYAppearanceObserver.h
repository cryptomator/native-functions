//
//  SKYAppearanceObserver.h
//  MacFunctions
//
//  Created by Tobias Hagemann on 16.08.19.
//  Copyright © 2019 Cryptomator. All rights reserved.
//

#include <jni.h>

#import <Foundation/Foundation.h>

@interface SKYAppearanceObserver : NSObject

- (instancetype)initWithListener:(jobject)listener vm:(JavaVM *)vm NS_DESIGNATED_INITIALIZER;
- (instancetype)init NS_UNAVAILABLE;
- (void)interfaceThemeChangedNotification:(NSNotification *)notification;

@end
