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
- (SKYAppearanceObserver *)observerWithIdentifier:(NSString *)identifier;
- (void)addObserver:(SKYAppearanceObserver *)observer;
- (void)removeObserver:(SKYAppearanceObserver *)observer;

@end
