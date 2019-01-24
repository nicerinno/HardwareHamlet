package com.hardwarehamlet.hardwarehamlet.preferences_manager;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREFERENCES_NAME = "prefs";
    private static final String SAVED_DATE = "saved_date";
    private static final String SESSION = "session";
    private static final String USER_ID = "user_id";
    private static final int loggedIn = 1;
    private static final int loggedOut = 0;

    public static long getLastSavedDate(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(SAVED_DATE,0);
    }

    public static void setSavedDate(Context context, long date){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SAVED_DATE,date);
        editor.apply();
    }

    public static void startSession(Context context, long user_id){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SESSION,loggedIn);
        editor.putLong(USER_ID,user_id);
        editor.apply();
    }

    public static void saveUserId(Context context, long user_id){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(USER_ID,user_id);
        editor.apply();
    }

    public static long getSavedUserId(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(USER_ID,1);
    }

    public static boolean getSession(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(SESSION, 0) == loggedIn;
    }

    public static void endSession(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SESSION,loggedOut);
        editor.remove(USER_ID);
        editor.apply();
    }

}
