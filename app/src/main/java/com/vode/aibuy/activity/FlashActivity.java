package com.vode.aibuy.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;

import com.vode.aibuy.MainActivity;
import com.vode.aibuy.R;

import static com.vode.aibuy.BaseApplication.handler;

public class FlashActivity extends BaseActivityWithoutMVP {

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_flash);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what){
                    case 0:
                        btn_jump(null);
                        break;
                }
                return true;
            }
        });


        handler.sendEmptyMessageDelayed(0,3000);
    }


    public void btn_jump(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

        handler.removeMessages(0);
        finish();
    }
}
