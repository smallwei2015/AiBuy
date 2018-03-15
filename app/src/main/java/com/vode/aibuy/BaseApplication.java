package com.vode.aibuy;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import com.vode.aibuy.utils.FileUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends Application {
    //  当你配置好了以后  会首先调用该方法
    public static BaseApplication application;
    public static long MainThreadId;
    public static Handler handler;

    private static List<Activity> mActivitys = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        MainThreadId = android.os.Process.myTid();
        application = this;

        //极光推送
        /*JPushInterface.setDebugMode(false);
        JPushInterface.init(this);*/


        //Thread.setDefaultUncaughtExceptionHandler (new MyUncaughtExceptionHandler());
    }


    public static BaseApplication getInstance() {
        return application;
    }

    private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {
        //Throwable  代表发生异常
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            FileUtils.string2File(getErrorInfo(ex), FileUtils.LOGPATH);
            android.os.Process.killProcess(android.os.Process.myPid());//自杀  杀死当前自己进程
        }

        /**
         * 获取错误的信息
         *
         * @param arg1
         * @return
         */
        private String getErrorInfo(Throwable arg1) {
            Writer writer = new StringWriter();
            PrintWriter pw = new PrintWriter(writer);
            arg1.printStackTrace(pw);
            pw.close();
            String error = writer.toString();
            return error;
        }
    }

    public static Handler getHandler() {
        return handler;
    }

    //---------------------------管理activity----------------------
    public static List<Activity> getActivitys() {
        return mActivitys;
    }

    public static void addActivity(Activity activity) {
        mActivitys.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mActivitys.remove(activity);
    }

    public static void exit() {
        for (Activity activity : mActivitys) {
            activity.finish();
        }
    }

}
