package com.java.seven.newsapp.util;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by caibao on 17-9-11.
 */

public class AppGlobal {
    public static boolean saveDataUsage = AppConstants.DEFAULT_SAVE_DATA_USAGE;
    public static String[] ignoreKeys = {};
    public static Activity activity;
    public static List<Fragment> fragments;
}
