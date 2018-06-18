#import <React/RCTBridgeModule.h>
#import <React/RCTConvert.h>
#import <React/RCTUtils.h>
#import <React/RCTEventEmitter.h>

@import GeoSpark;
@import CoreLocation;

@interface RNGeoSpark : RCTEventEmitter <RCTBridgeModule,GeoSparkDelegate>
@end
