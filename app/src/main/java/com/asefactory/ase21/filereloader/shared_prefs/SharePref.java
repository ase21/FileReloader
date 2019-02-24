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
    private static final String FILE_NAME = "file_name";
    private static final String TIME_REPEATING = "time_repeating";
    private static final String TIMER_FLAG = "timer_is_sets";

    private SharePref() {} //prevent creating multiple instances by making the constructor private

    //The context passed into the getInstance should be application level context.
    public static SharePref getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharePref;
    }

    public void saveDownloadUrl(String downloadUrl) {
        editor.putString(DOWNLOAD_URL, downloadUrl);
        editor.commit();
    }

    public void saveFileName(String filename) {
        editor.putString(FILE_NAME, filename);
        editor.commit();
    }

    public void saveTimeRepeating(String timeRepeating) {
        editor.putString(TIME_REPEATING, timeRepeating);
        editor.commit();
    }

    public void setTimer(Boolean timer) {
        editor.putBoolean(TIMER_FLAG, timer);
        editor.commit();
    }

    public String getDownloadUrl() {
        return sharedPreferences.getString(DOWNLOAD_URL, "");
    }

    public String getFileName() {
        return sharedPreferences.getString(FILE_NAME, "");
    }

    public String getTimeRepeating() {
        return sharedPreferences.getString(TIME_REPEATING, "");
    }

    public boolean isitWorks() {
        return sharedPreferences.getBoolean(TIMER_FLAG,false);
    }
}
