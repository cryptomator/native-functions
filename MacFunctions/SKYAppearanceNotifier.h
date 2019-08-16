//
//  SKYAppearanceNotifier.h
//  MacFunctions
//
//  Created by Tobias Hagemann on 16.08.19.
//  Copyright © 2019 Cryptomator. All rights reserved.
//

#import <Foundation/Foundation.h>

@class SKYAppearanceObserver;

@interface SKYAppearanceNotifier : NSObject

+ (instancetype)sharedInstance;
- (void)addObserver:(SKYAppearanceObserver *)observer;

@end
