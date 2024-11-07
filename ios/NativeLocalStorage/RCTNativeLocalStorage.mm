//
//  RCTNativeLocalStorage.m
//  TurboModuleExample
//
//  Created by Bogusz Kaszowski on 06/11/2024.
//

#import "RCTNativeLocalStorage.h"


static NSString *const RCTNativeLocalStorageKey = @"local-storage";


@interface RCTNativeLocalStorage()
@property (strong, nonatomic) NSUserDefaults *localStorage;
@end

@implementation RCTNativeLocalStorage

RCT_EXPORT_MODULE(NativeLocalStorage)

- (id) init {
  if (self = [super init]) {
    _localStorage = [[NSUserDefaults alloc] initWithSuiteName:RCTNativeLocalStorageKey];
  }
  
  return self;
}

- (std::shared_ptr<facebook::react::TurboModule>) getTurboModule:(const facebook::react::ObjCTurboModule::InitParams &)params {
  return std::make_shared<facebook::react::NativeLocalStorageSpecJSI>(params);
}

- (NSString * _Nullable)getItem:(NSString *)key {
  return [self.localStorage stringForKey:key];
}

- (void) removeItem:(NSString *)key {
  return [self.localStorage removeObjectForKey:key];
}

- (void) clear {
  NSDictionary * keys = [self.localStorage dictionaryRepresentation];
  
  for (NSString *key in keys) {
    [self removeItem:key];
  }
}

- (void)setItem:(NSString *)value key:(NSString *)key { 
  [self.localStorage setObject:value forKey:key];
}

@end