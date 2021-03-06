package com.vode.aibuy.model;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.vode.aibuy.BaseApplication;
import com.vode.aibuy.activity.LoginActivity;
import com.vode.aibuy.bean.User;


/**
 * Created by cj on 2017/6/21.
 */

public class UserManager {

    public static final String action_in = "com.aibuy.login";
    public static final String action_out = "com.aibuy.login_out";
    public static final String action_change = "com.aibuy.change";

    private static User cUser;

    public static User getUser() {

        if (cUser != null) {
            return cUser;
        } else {
            return null;
        }
    }

    public static String getAppuserId() {
        if (cUser != null)
            return cUser.getUser_id() + "";
        else
            return "";
    }

    public static void setUser(User user) {
        if (user != null) {
            cUser = user;
            sendLogin();

            setAlias();
        }
    }

    private static void setAlias() {
        /*JPushInterface.setAlias(BaseApplication.getInstance(), cUser.getUserName(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.w("vode",s);
                if (i==6002){
                    //setAlias();
                    Log.w("vode","设置alias失败");
                }else {
                    Log.w("vode","设置alias成功");
                }
            }
        });*/
    }


    public static void saveUser(User user) {

            /*登录过时时间为7天*/
        user.setExpiration(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30);

    }

    private static void sendLogin() {
        Intent intent = new Intent();
        intent.setAction(action_in);
        LocalBroadcastManager.getInstance(BaseApplication.getInstance()).sendBroadcast(intent);
    }

    public static void loginOut(View view, final UserManagerInterface manager) {

        final ProgressDialog dialog = new ProgressDialog(view.getContext());
        dialog.setMessage("正在退出登录...");
        dialog.show();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                manager.success(cUser);


                /*JPushInterface.setAlias(BaseApplication.getInstance(), "", new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Log.w("vode", "取消别名设置");
                    }
                });*/
                deleteUser();
                sendLoginOut();
            }
        }, 1000);


    }

    public static void loginOutWithoutDelay() {
        /*JPushInterface.setAlias(BaseApplication.getInstance(), "", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.w("vode", "取消别名设置");
            }
        });*/

        cUser = null;
        sendLoginOut();
    }

    private static void deleteUser() {
        cUser = null;
    }

    private static void sendLoginOut() {
        Intent intent = new Intent();
        intent.setAction(action_out);
        LocalBroadcastManager.getInstance(BaseApplication.getInstance()).sendBroadcast(intent);
    }

    public static void register(String name, String pass, String nickname, String address, String path, final UserManagerInterface manager) {

    }

    public static boolean isLogin() {
        return cUser != null;
    }

    public static boolean isLoginElse(){
        if (isLogin()){
            return true;
        }else {
            toLogin();
            return false;
        }
    }

    public static void loginThird(String userId, String nameStr, String userIcon, final UserManagerInterface manager) {
    }

    public static void sendChange() {

        loginOutWithoutDelay();

    }

    public static void toLogin() {
        BaseApplication instance = BaseApplication.getInstance();
        Intent intent = new Intent(instance, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        instance.startActivity(intent);
    }
}
