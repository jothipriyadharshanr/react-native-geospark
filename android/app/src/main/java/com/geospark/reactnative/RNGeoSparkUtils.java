package com.geospark.reactnative;

import android.location.Location;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.geospark.lib.model.GeoSparkUser;


public class RNGeoSparkUtils {

    static String checkPermissionStatus(boolean hasGranted) {
        if (hasGranted) {
            return "GRANTED";
        }
        return "DENIED";
    }

    static String checkPlayServices(boolean hasGranted) {
        if (hasGranted) {
            return "YES";
        }
        return "NO";
    }

    static String checkLocationServices(boolean hasGranted) {
        if (hasGranted) {
            return "ENABLED";
        }
        return "DISABLED";
    }

    static WritableMap mapForLocation(Location location) {
        if (location == null) {
            return null;
        }
        WritableMap map = Arguments.createMap();
        map.putDouble("latitude", location.getLatitude());
        map.putDouble("longitude", location.getLongitude());
        map.putDouble("accuracy", location.getAccuracy());
        return map;
    }

    static WritableMap mapForUser(GeoSparkUser geoSparkUser) {
        if (geoSparkUser == null) {
            return null;
        }
        WritableMap map = Arguments.createMap();
        map.putString("userId", geoSparkUser.getmUserID());
        return map;
    }

}
