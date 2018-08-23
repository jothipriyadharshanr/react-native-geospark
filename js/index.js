
import {NativeEventEmitter,NativeModules} from 'react-native';

if (!NativeModules.RNGeoSpark) {
  throw new Error('NativeModules.RNGeoSpark is undefined');
}

const eventEmitter = new NativeEventEmitter(NativeModules.RNGeoSpark);

const Type = {
 HIGH_ACCURACY: 'HIGH_ACCURACY',
 BALANCED_POWER_ACCURACY : 'BALANCED_POWER_ACCURACY',
 LOW_POWER : 'LOW_POWER',
 ALL : 'ALL',
 STOP : 'STOP',
 WALK :'WALK',
 DRIVE : 'DRIVE',
 BICYCLE : 'BICYCLE',
 HIGH :'HIGH',
 MEDIUM : 'MEDIUM',
 LOW :'LOW',
 OPTIMISED : 'OPTIMISED',
 FOREGROUND : 'FOREGROUND',
 BACKGROUND : 'BACKGROUND',
 TERMINATED : 'TERMINATED',
 ALWAYS_ON : 'ALWAYS_ON'
}

const IOSType = {
 High:    'GSHigh',
 Medium:  'GSMedium',
 Low:     'GSLow',
 OPTIMISED:    'GSOPTIMISED',
 BestForNavigation: 'GSBestForNavigation',
 NearestTenMeters: 'GSNearestTenMeters',
 HundredMeters: 'GSHundredMeters',
 ThreeKilometers: 'GSThreeKilometers',
 All: 'GSAll',
 Running: 'GSRunning',
 Walking: 'GSWalking',
 AutoMotive: 'GSAutoMotive',
 Stationary: 'GSStationary',
 Foreground: 'GSForeground',
 Terminated: 'GSTerminated',
 Background: 'GSBackground',
 AlwaysOn: 'GSAlwaysOn'
}

const checkGooglePlayService = (callback) => {
  NativeModules.RNGeoSpark.checkGooglePlayService(callback);
};

const checkPermission = (callback) => {
  NativeModules.RNGeoSpark.checkPermission(callback);
};

const checkLocationServices = (callback) => {
  NativeModules.RNGeoSpark.checkLocationServices(callback);
};

const requestPermission = () => {
  NativeModules.RNGeoSpark.requestPermission();
};

const requestLocationServices = () => {
  NativeModules.RNGeoSpark.requestLocationServices();
};

const createUser = (successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.createUser(successCallback,errorCallback);
};

const getUser = (userid,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.getUser(userid,successCallback,errorCallback);
};

const setDescription = (description) => {
  NativeModules.RNGeoSpark.setDescription(description);
};

const startLocationTracking = () => {
  NativeModules.RNGeoSpark.startLocationTracking();
};

const stopLocationTracking = () => {
  NativeModules.RNGeoSpark.stopLocationTracking();
};

const startMockLocationTracking = () => {
  NativeModules.RNGeoSpark.startMockLocationTracking();
};

const stopMockLocationTracking = () => {
  NativeModules.RNGeoSpark.stopMockLocationTracking();
};

const setTrackingInAppState = (types) =>{
  NativeModules.RNGeoSpark.setTrackingInAppState(types);
}

const setTrackingInMotion = (types) => {
  NativeModules.RNGeoSpark.setTrackingInMotion(types);
}

const setLocationMode = (type) => {
   NativeModules.RNGeoSpark.setLocationMode(type);
}

const setLocationFrequency = (type) => {
  NativeModules.RNGeoSpark.setLocationFrequency(type);
}

const setLocationAccuracy = (type) => {
  NativeModules.RNGeoSpark.setLocationAccuracy(type);
}

const setDistanceFilter = (type) => {
  NativeModules.RNGeoSpark.setDistanceFilter(type);
}

const startListener = (event, callback) => (
  eventEmitter.addListener(event, callback)
);

const stopListener = (event, callback) => {
  if (callback) {
    eventEmitter.removeListener(event, callback);
  } else {
    eventEmitter.removeAllListeners(event);
  }
};

const GeoSpark = {
 Type,
 IOSType,
 checkGooglePlayService,
 checkPermission,
 checkLocationServices,
 requestPermission,
 requestLocationServices,
    createUser,
    getUser,
    setDescription,
    startLocationTracking,
    stopLocationTracking,
    startMockLocationTracking,
    stopMockLocationTracking,
    stopMockLocationTracking,
    setTrackingInAppState,
    setTrackingInMotion,
    setLocationMode,
    setLocationFrequency,
    setLocationAccuracy,
    setDistanceFilter,
    startListener,
    stopListener
};


export default GeoSpark;