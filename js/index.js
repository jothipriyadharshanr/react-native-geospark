
import {NativeEventEmitter,NativeModules} from 'react-native';

if (!NativeModules.RNGeoSpark) {
  throw new Error('NativeModules.RNGeoSpark is undefined');
}

const eventEmitter = new NativeEventEmitter(NativeModules.RNGeoSpark);

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
  startListener,
  stopListener
};


export default GeoSpark;