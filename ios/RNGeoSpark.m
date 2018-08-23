#import "RNGeoSpark.h"
@import GeoSpark;

@implementation RNGeoSpark{
    BOOL hasListeners;
}
RCT_EXTERN_METHOD(supportedEvents)
RCT_EXPORT_MODULE();


- (instancetype)init {
  self = [super init];
  if (self) {
    [GeoSpark sharedInstance].delegate = self;
  }
  return self;
}

- (NSArray<NSString *> *)supportedEvents {
  return @[@"events", @"location", @"error"];
}

- (void)startObserving {
  hasListeners = YES;
}

- (void)stopObserving {
  hasListeners = NO;
}


- (void)didUpdateLocation:(CLLocation * )location user:(GeoSparkUser * )user{
  NSMutableDictionary *dict = [NSMutableDictionary new];

  if (hasListeners) {
    NSDictionary *locationDict = [self dictionaryForLocation:location];
    if (locationDict) {
      [dict setObject:locationDict forKey:@"location"];
    }
    NSDictionary *userDict = [self dictionaryForUser:user];
    if (userDict) {
      [dict setObject:userDict forKey:@"user"];
    }
    [self sendEventWithName:@"location" body:dict];
  }
}

RCT_EXPORT_METHOD(startLocationTracking){
  [self startObserving];
  [[GeoSpark sharedInstance] startLocationTracking];
}

RCT_EXPORT_METHOD(stopLocationTracking){
  [self stopObserving];
  [[GeoSpark sharedInstance] stopLocationTracking];
}

RCT_EXPORT_METHOD(createUser :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [[GeoSpark sharedInstance] createUser:^(BOOL succeeded, NSError * error, NSNumber * errorCode, NSString * userID) {
    NSLog(@"User Id %@",userID);
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:userID, nil];
    if (succeeded) {
      successCallback(dict);
    }else{
      errorCallback(error);
    }
  }];
}


RCT_EXPORT_METHOD(getUser:(NSString *)userId :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [[GeoSpark sharedInstance] startSessionForUser:userId completion:^(BOOL succeeded, NSError * error, NSNumber * errorCode, NSString * userID) {
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:userID, nil];
    if (succeeded) {
      successCallback(dict);
    }else{
      errorCallback(error);
    }
  }];
}

RCT_EXPORT_METHOD(setDescription:(NSString *)userDescription){
  [[GeoSpark sharedInstance] setDescription:userDescription];
}

- (NSDictionary *)dictionaryForLocation:(CLLocation *)location {
  if (!location) {
    return nil;
  }
  
  NSMutableDictionary *dict = [NSMutableDictionary new];
  [dict setValue:@(location.coordinate.latitude) forKey:@"latitude"];
  [dict setValue:@(location.coordinate.longitude) forKey:@"longitude"];
  if (location.horizontalAccuracy) {
    [dict setValue:@(location.horizontalAccuracy) forKey:@"accuracy"];
  }
  return dict;
}

RCT_EXPORT_METHOD(setTrackingInAppState:(NSArray *)states){
  [[GeoSpark sharedInstance] trackLocationInAppState:states];
}
RCT_EXPORT_METHOD(setTrackingInMotion:(NSArray *)motions){
  [[GeoSpark sharedInstance] trackLocationInMotion:motions];
}

RCT_EXPORT_METHOD(setLocationMode:(NSString *)locationMode ){
  [[GeoSpark sharedInstance] setLocationMode:locationMode];
}
RCT_EXPORT_METHOD(setLocationFrequency:(NSString *)frequency){
  [[GeoSpark sharedInstance] setLocationFrequency:frequency];
}

RCT_EXPORT_METHOD(setLocationAccuracy:(NSString *)accuracy){
  [[GeoSpark sharedInstance] setLocationAccuracy:accuracy];
}

RCT_EXPORT_METHOD(setDistanceFilter:(NSString *)filter){
  [[GeoSpark sharedInstance] setDistanceFilter:filter];
}



- (NSDictionary *)dictionaryForUser:(GeoSparkUser *)user{
  if (!user) {
    return nil;
  }
  
  NSMutableDictionary *dict = [NSMutableDictionary new];
  if (user) {
    [dict setValue:user.userId forKey:@"place"];
  }
  return dict;
}

@end
