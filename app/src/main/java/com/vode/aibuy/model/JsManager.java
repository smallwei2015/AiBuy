package com.vode.aibuy.model;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.vode.aibuy.utils.UIUtils;

/**
 * Created by cj on 2018/4/24.
 */

public class JsManager extends Object {

    @JavascriptInterface
    public void hello(String msg){
        Log.w("vode","1234567890");
        UIUtils.showToast(msg);
    }
}
