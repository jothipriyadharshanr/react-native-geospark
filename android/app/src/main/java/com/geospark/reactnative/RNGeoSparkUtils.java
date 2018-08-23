package com.geospark.reactnative;

import android.location.Location;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.geospark.lib.GeoSpark;
import com.geospark.lib.model.GeoSparkUser;

import java.util.ArrayList;
import java.util.List;


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

    static GeoSpark.Type stringToEnum(String type) {
        return Enum.valueOf(GeoSpark.Type.class, type);
    }

    static boolean isArrayNotNull(ReadableArray readableArray) {
        return readableArray != null && readableArray.size() > 0;
    }

    static boolean isStringNotNull(String type) {
        return type != null && type.trim().length() > 0;
    }

    static GeoSpark.Type[] arrayToEnum(ReadableArray readableArray) {
        List<GeoSpark.Type> types = new ArrayList<>();
        for (int i = 0; i < readableArray.size(); i++) {
            types.add(stringToEnum(readableArray.getString(i)));
        }
        return types.toArray(new GeoSpark.Type[types.size()]);
    }

    static boolean isLocationSettings(GeoSpark.Type types) {
        return types.equals(GeoSpark.Type.HIGH) ||
                types.equals(GeoSpark.Type.MEDIUM) ||
                types.equals(GeoSpark.Type.LOW) ||
                types.equals(GeoSpark.Type.OPTIMISED);
    }

    static boolean isModeSettings(GeoSpark.Type types) {
        return types.equals(GeoSpark.Type.HIGH_ACCURACY) ||
                types.equals(GeoSpark.Type.BALANCED_POWER_ACCURACY) ||
                types.equals(GeoSpark.Type.LOW_POWER);
    }

}
