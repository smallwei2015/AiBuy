package com.vode.aibuy.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Result1;
import com.vode.aibuy.model.ModelClient;
import com.vode.aibuy.utils.SignUtils;
import com.vode.aibuy.utils.UIUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterActivity extends BaseActivityWithoutMVP {

    private EditText phoneEdit;
    private EditText verifyEdit;
    private TextView getVerify;
    private TextView check;

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
    public String phone;
    public String key;
    public EditText passEdit;
    public EditText pass1Edit;
    public String pass;
    public String pass1;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_register);
        initTop(R.mipmap.left_white,"注册",-1);
        phoneEdit=findViewById(R.id.phone_edit);
        verifyEdit=findViewById(R.id.verify_edit);
        getVerify=findViewById(R.id.getVerify);
        check=findViewById(R.id.register);

        passEdit = findViewById(R.id.pass_edit);
        pass1Edit = findViewById(R.id.pass1_edit);

        getVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVer();
            }
        });

    }
    public void getVer() {
        phone=phoneEdit.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            UIUtils.showToast("手机号不能为空");
            return;
        }

        if (getVerify.isEnabled()) {
            getVerify.setEnabled(false);
            handler.sendEmptyMessage(100);

            ModelClient.getSms(phone);

        }

    }


    private boolean checkPhoneAndKey() {

        phone = phoneEdit.getText().toString().trim();
        key = verifyEdit.getText().toString().trim();
        pass = pass1Edit.getText().toString().trim();
        pass1 = passEdit.getText().toString().trim();



        if (TextUtils.isEmpty(phone)){
            UIUtils.showToast("请输入手机号");
            return false;
        }

        if (TextUtils.isEmpty(key)){
            UIUtils.showToast("请输入验证码");
            return false;
        }

        if (TextUtils.isEmpty(pass)){
            UIUtils.showToast("请输入密码");
            return false;
        }

        if (!pass.equals(pass1)){
            UIUtils.showToast("两次输入密码不一致");
            return false;
        }

        return true;
    }

    public void btn_register(View view) {
        if (checkPhoneAndKey()) {
            final ProgressDialog dialog = new ProgressDialog(mActivity);
            dialog.setMessage("加载中...");
            dialog.show();


            Map<String,String> map=SignUtils.getMap();
            map.put("password",pass);
            map.put("mobile",phone);
            map.put("verify",key);

            String result = SignUtils.getResult(map);

            ModelClient.retrofit.userRegister(result)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Result1>() {
                        @Override
                        public void onCompleted() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            UIUtils.showToast("网络请求失败");
                        }

                        @Override
                        public void onNext(Result1 result1) {
                            if (result1.getCode()==200) {

                                UIUtils.showToast("注册成功");
                                finish();
                            }else {
                                UIUtils.showToast(result1.getMessage());
                            }
                        }
                    });


        }
    }

}
