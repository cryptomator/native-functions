//
//  SKYAppearanceNotifier.m
//  MacFunctions
//
//  Created by Tobias Hagemann on 16.08.19.
//  Copyright © 2019 Cryptomator. All rights reserved.
//

#import "SKYAppearanceNotifier.h"
#import "SKYAppearanceObserver.h"

@interface SKYAppearanceNotifier ()
@property (nonatomic, strong) NSMutableArray<SKYAppearanceObserver *> *observers;
@end

@implementation SKYAppearanceNotifier

+ (instancetype)sharedInstance {
	static SKYAppearanceNotifier *sharedInstance;
	static dispatch_once_t onceToken;
	dispatch_once(&onceToken, ^{
		sharedInstance = [[SKYAppearanceNotifier alloc] init];
	});
	return sharedInstance;
}

- (instancetype)init {
	if (self = [super init]) {
		self.observers = [NSMutableArray array];
	}
	return self;
}

- (SKYAppearanceObserver *)observerWithIdentifier:(NSString *)identifier {
	NSUInteger indexOfObserver = [self.observers indexOfObjectPassingTest:^BOOL(SKYAppearanceObserver *observer, NSUInteger idx, BOOL *stop) {
		return [observer.identifier isEqualToString:identifier];
	}];
	return indexOfObserver != NSNotFound ? self.observers[indexOfObserver] : nil;
}

- (void)addObserver:(SKYAppearanceObserver *)observer {
	[self.observers addObject:observer];
	[[NSDistributedNotificationCenter defaultCenter] addObserver:observer selector:@selector(interfaceThemeChangedNotification:) name:@"AppleInterfaceThemeChangedNotification" object:nil];
}

- (void)removeObserver:(SKYAppearanceObserver *)observer {
	[self.observers removeObject:observer];
	[[NSDistributedNotificationCenter defaultCenter] removeObserver:observer];
}

@end
