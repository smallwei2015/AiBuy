package com.vode.aibuy.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by cj on 2018/3/9.
 */

public class PhoneUtils {

    public static void callPhone(final Context context, final String phoneNumber, String title, String desc, String positive, String negative) {
        try {
            AlertDialog dialog = new AlertDialog.Builder(context).setTitle(title)
                    .setMessage(desc)
                    .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            //url:统一资源定位符
                            //uri:统一资源标示符（更广）
                            intent.setData(Uri.parse("tel:" + phoneNumber));
                            //开启系统拨号器
                            context.startActivity(intent);
                        }
                    })
                    .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.w(e.getMessage());
        }
    }


    public static void callPhone(final Context context, final String phoneNumber) {
        callPhone(context, phoneNumber, "是否拨号", phoneNumber, "确定", "取消");
    }

    public static void sendSMS(Context context, String number, String message) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        sendIntent.putExtra("sms_body", message);
        context.startActivity(sendIntent);
    }
}
