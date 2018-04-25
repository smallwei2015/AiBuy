package com.vode.aibuy.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vode.aibuy.R;
import com.vode.aibuy.model.JsManager;
import com.vode.aibuy.utils.UIUtils;

public class WebActivity extends BaseActivityWithoutMVP {

    public SwipeRefreshLayout swip;
    public WebView web;

    @Override
    void initData() {
        web.loadUrl("file:///android_asset/javascript.html");
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_web);

        initTop(R.mipmap.left_white, "", -1);

        swip = ((SwipeRefreshLayout) findViewById(R.id.swip));
        web = ((WebView) findViewById(R.id.web));

        //声明WebSettings子类
        WebSettings webSettings = web.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        /*//支持插件
        webSettings.setPluginsEnabled(true);*/

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        /*//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件*/

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


        web.addJavascriptInterface(new JsManager(), "vode");

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //web.reload();

                //web.loadUrl("javascript:callJS()");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    web.evaluateJavascript("javascript:callJS(\'vode\')", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            UIUtils.showToast(value);
                        }
                    });
                }
            }
        });

        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //设定加载结束的操作
            }
        });

        web.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                }else {
                    swip.setRefreshing(false);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(mActivity);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });



    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()){
            web.goBack();
        }else {
            super.onBackPressed();
        }

    }
}
