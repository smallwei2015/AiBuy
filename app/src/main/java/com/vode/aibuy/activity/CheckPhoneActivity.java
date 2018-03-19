package com.vode.aibuy.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.utils.UIUtils;

public class CheckPhoneActivity extends BaseActivityWithoutMVP {


    EditText phoneEdit;
    EditText verifyEdit;
    TextView getVerify;
    TextView check;

    private int timer = 30;


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {


            switch (msg.what) {
                case 100:
                    getVerify.setText(timer + "S");

                    timer--;
                    if (timer > 0) {
                        handler.sendEmptyMessageDelayed(100, 1000);
                    } else {
                        timer = 30;
                        getVerify.setText("获取验证码");
                        getVerify.setEnabled(true);
                    }

                    break;
                case 200:

                    break;
            }
            return false;
        }
    });
    private AlertDialog dialog;


    @Override
    void initData() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_check_phone);
        initTop(R.mipmap.left_white, "验证手机号", -1);

        phoneEdit=findViewById(R.id.phone_edit);
        verifyEdit=findViewById(R.id.verify_edit);
        getVerify=findViewById(R.id.getVerify);
        check=findViewById(R.id.register);

        getVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVer();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }


    public void getVer() {
        if (getVerify.isEnabled()) {
            getVerify.setEnabled(false);
            handler.sendEmptyMessage(100);
        }

    }

    private void check() {

        final String phone = phoneEdit.getText().toString();
        String key = verifyEdit.getText().toString();

        if (checkPhoneAndKey(phone, key)) {

            final ProgressDialog dialog = new ProgressDialog(mActivity);
            dialog.setMessage("加载中...");
            dialog.show();

        }
    }

    private void checkSuccess() {

        Intent intent = new Intent(mActivity, RegisterActivity.class);
        intent.putExtra("phone", phoneEdit.getText().toString());
        startActivity(intent);

        finish();

    }

    private void changePhone() {

        final ProgressDialog dialog = new ProgressDialog(mActivity);
        dialog.setMessage("修改中...");

        dialog.show();

    }


    private boolean checkPhoneAndKey(String phone, String key) {

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(key)) {

            UIUtils.showToast("请检查手机号和验证码填写是否正确");
            return false;
        }

        return true;

    }
}
