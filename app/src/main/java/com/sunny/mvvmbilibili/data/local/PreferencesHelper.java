package com.sunny.mvvmbilibili.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "bilibili_pref_file";

    private final SharedPreferences mPref;

    public static final String KEY_IS_LOGIN = "is_login";

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void putBoolean(String key, Boolean value) {
        mPref.edit().putBoolean(key, value).apply();
    }

    public Boolean getBoolean(String key, Boolean defValue) {
        return mPref.getBoolean(key, defValue);
    }

}
