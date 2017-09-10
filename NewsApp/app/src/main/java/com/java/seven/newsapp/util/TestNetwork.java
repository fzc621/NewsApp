package com.java.seven.newsapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.java.seven.newsapp.app.MyApplication;

/**
 * Created by zzy on 17-9-9.
 */

public class TestNetwork {
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) MyApplication.getContextObject()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
