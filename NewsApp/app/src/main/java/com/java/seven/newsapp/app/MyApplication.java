package com.java.seven.newsapp.app;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.java.seven.newsapp.util.AppConstants;
import com.java.seven.newsapp.util.SharedPreferencesUtil;

import org.litepal.LitePal;

/**
 * Created by linsawako on 2017/2/7.
 */

public class MyApplication extends android.support.multidex.MultiDexApplication {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        LitePal.getDatabase();
        boolean isNight = SharedPreferencesUtil.getBoolean(this, AppConstants.ISNIGHT, false);
        if (isNight) {
            //使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //不使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        context = getApplicationContext();
    }

    public static Context getContextObject() {
        return context;
    }
}
