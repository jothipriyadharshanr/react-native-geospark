package com.geospark.reactnative;

import android.app.Activity;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.geospark.lib.GeoSpark;
import com.geospark.lib.callback.ErrorResponse;
import com.geospark.lib.callback.GeoSparkCallBack;
import com.geospark.lib.callback.SuccessResponse;

public class RNGeoSpark extends ReactContextBaseJavaModule {
    ReactApplicationContext reactContext;

    public RNGeoSpark(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

    }

    @Override
    public String getName() {
        return "RNGeoSpark";
    }

    @ReactMethod
    public void checkGooglePlayService(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkPlayServices(GeoSpark.checkGooglePlayService(reactContext)));
    }

    @ReactMethod
    public void checkPermission(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkPermissionStatus(GeoSpark.checkPermission(reactContext)));
    }

    @ReactMethod
    public void checkLocationServices(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkLocationServices(GeoSpark.checkLocationServices(reactContext)));
    }

    @ReactMethod
    public void requestPermission() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            GeoSpark.requestPermission(activity);
        }
    }

    @ReactMethod
    public void requestLocationServices() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            GeoSpark.requestLocationServices(getCurrentActivity());
        }
    }

    @ReactMethod
    public void createUser(final Callback successCallback, final Callback errorCallback) {
        GeoSpark.createUser(reactContext, new GeoSparkCallBack() {
            @Override
            public void success(SuccessResponse successResponse) {
                WritableMap map = Arguments.createMap();
                map.putString("userid", successResponse.getUserID());
                successCallback.invoke(map);
            }

            @Override
            public void failure(ErrorResponse errorResponse) {
                errorCallback.invoke(errorResponse.getErrorMessage());
            }
        });
    }

    @ReactMethod
    public void getUser(String userID, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.getUser(reactContext, userID, new GeoSparkCallBack() {
            @Override
            public void success(SuccessResponse successResponse) {
                WritableMap map = Arguments.createMap();
                map.putString("userid", successResponse.getUserID());
                successCallback.invoke(map);
            }

            @Override
            public void failure(ErrorResponse errorResponse) {
                errorCallback.invoke(errorResponse.getErrorMessage());
            }
        });
    }

    @ReactMethod
    public void setDescription(String description) {
        GeoSpark.setDescription(reactContext, description);
    }

    @ReactMethod
    public void startLocationTracking() {
        GeoSpark.startLocationTracking(reactContext);
    }

    @ReactMethod
    public void stopLocationTracking() {
        GeoSpark.stopLocationTracking(reactContext);
    }

    @ReactMethod
    public void startMockLocationTracking() {
        GeoSpark.startMockLocationTracking(reactContext);
    }

    @ReactMethod
    public void stopMockLocationTracking() {
        GeoSpark.stopMockLocationTracking(reactContext);
    }

}
