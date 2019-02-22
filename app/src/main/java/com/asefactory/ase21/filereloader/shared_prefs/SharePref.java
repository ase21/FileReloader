package com.asefactory.ase21.filereloader.shared_prefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {
    private static SharePref sharePref = new SharePref();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static final String PLACE_OBJ = "place_obj";

    private static final String DOWNLOAD_URL = "download_url";
    private static final String TIME_REPEATING = "time_repeating";

    private SharePref() {} //prevent creating multiple instances by making the constructor private

    //The context passed into the getInstance should be application level context.
    public static SharePref getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharePref;
    }

    public void savePlaceObj(String placeObjStr) {
        editor.putString(PLACE_OBJ, placeObjStr);
        editor.commit();
    }

    public String getPlaceObj() {
        return sharedPreferences.getString(PLACE_OBJ, "");
    }

    public void setDownloadUrl(String downloadUrl) {
        editor.putString(DOWNLOAD_URL, downloadUrl);
        editor.commit();
    }

    public void setTimeRepeating(String timeRepeating) {
        editor.putString(TIME_REPEATING, timeRepeating);
        editor.commit();
    }

    public String getDownloadUrl() {
        return sharedPreferences.getString(DOWNLOAD_URL, "");
    }

    public void removePlaceObj() {
        editor.remove(PLACE_OBJ);
        editor.commit();
    }

    public void clearAll() {
        editor.clear();
        editor.commit();
    }

}
