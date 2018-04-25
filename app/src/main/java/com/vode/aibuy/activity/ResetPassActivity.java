package com.vode.aibuy.activity;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.vode.aibuy.R;
import com.vode.aibuy.model.LoadDataInteface;
import com.vode.aibuy.model.ModelClient;
import com.vode.aibuy.model.UserManager;
import com.vode.aibuy.utils.UIUtils;

public class ResetPassActivity extends BaseActivityWithoutMVP {

    public EditText passRawEdit;
    public EditText passEdit;
    public EditText pass1Edit;
    public String pass1;
    public String pass;
    public String passRaw;
    public View raw;
    public int flag;

    @Override
    void initData() {
        flag = getIntent().getIntExtra("flag", 0);

        if (flag==0){
            raw.setVisibility(View.GONE);
        }else {
            raw.setVisibility(View.VISIBLE);
        }
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_reset_pass);

        initTop(R.mipmap.left_white,"重置密码",-1);
        raw = findViewById(R.id.pass_raw);

        passRawEdit = ((EditText) findViewById(R.id.pass_raw_edit));
        passEdit = ((EditText) findViewById(R.id.pass_edit));
        pass1Edit = ((EditText) findViewById(R.id.pass1_edit));

    }

    public void btn_register(View view) {
        if (check()){


            if (flag==1) {
                changePass();
            }else {
                final ProgressDialog dialog = new ProgressDialog(mActivity);
                dialog.setTitle("加载中...");
                dialog.show();

                ModelClient.resetPass(pass, new LoadDataInteface() {
                    @Override
                    public void onDataLoaded(Object data) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onDataLoadFailed(Throwable e) {

                        dialog.dismiss();
                    }
                });
            }
        }
    }

    private boolean check() {

        pass1 = pass1Edit.getText().toString().trim();
        pass = passEdit.getText().toString().trim();
        passRaw = passRawEdit.getText().toString().trim();


        if (flag==1&&TextUtils.isEmpty(passRaw)){
            UIUtils.showToast("请输入原始密码");
            return false;
        }
        if (TextUtils.isEmpty(pass)){
            UIUtils.showToast("请输入新密码");
            return false;
        }

        if (!pass.equals(pass1)){
            UIUtils.showToast("两次输入密码不一致");
            return false;
        }

        return true;
    }

    private void changePass(){
        ModelClient.changePass(pass,passRaw,pass, UserManager.getAppuserId());
    }
}
