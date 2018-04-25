package com.vode.aibuy.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.User;
import com.vode.aibuy.model.UserManager;
import com.vode.aibuy.present.LoginPresent;
import com.vode.aibuy.utils.UIUtils;
import com.vode.aibuy.view.LoginView;

public class LoginActivity extends BaseActivity<LoginView,LoginPresent> implements LoginView{

    public EditText nameEdit;
    public EditText passEdit;
    public String name;
    public String pass;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_login);

        initTop(R.mipmap.left_white,"登录",-1);
        nameEdit = ((EditText) findViewById(R.id.name_edit));
        passEdit = ((EditText) findViewById(R.id.pass_edit));

        nameEdit.setText("17746547747");
        passEdit.setText("123456");
    }


    @NonNull
    @Override
    public LoginPresent createPresenter() {
        return new LoginPresent();
    }

    @Override
    public void loginSucccess(User user) {

        UIUtils.showToast("登录成功");
        UserManager.setUser(user);

        finish();
    }

    private boolean checkSuccess() {

        name = nameEdit.getText().toString().trim();
        pass = passEdit.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            UIUtils.showToast("请输入用户名");
            return false;
        }

        if (TextUtils.isEmpty(pass)){
            UIUtils.showToast("请输入密码");
            return false;
        }
        return true;
    }

    public void btn_login(View view) {
        if (checkSuccess()) {
            getPresenter().loadData(name,pass);
        }
    }

    public void btn_register(View view) {
        Intent intent=new Intent(mActivity,RegisterActivity.class);
        startActivity(intent);
    }

    public void btn_forget(View view) {
        Intent intent=new Intent(mActivity,CheckPhoneActivity.class);
        startActivity(intent);
    }
}
