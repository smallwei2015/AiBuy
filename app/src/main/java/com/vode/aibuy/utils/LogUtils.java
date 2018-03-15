package com.vode.aibuy.utils;

import android.util.Log;

import com.vode.aibuy.BuildConfig;

/**
 * Created by cj on 2018/3/12.
 */

public class LogUtils {
    public static final String TAG="vode";
    public static void w(String content){
        if (BuildConfig.DEBUG){
            Log.w(TAG, content);
        }
    }
}
