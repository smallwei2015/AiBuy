package com.vode.aibuy.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.vode.aibuy.R;
import com.vode.aibuy.bean.User;
import com.vode.aibuy.present.LoginPresent;
import com.vode.aibuy.view.LoginView;

public class LoginActivity extends MvpActivity<LoginView,LoginPresent> implements LoginView{

    public TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = ((TextView) findViewById(R.id.name));

        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 1000);*/

    }

    @NonNull
    @Override
    public LoginPresent createPresenter() {
        return new LoginPresent();
    }

    @Override
    public void loginSucccess(User user) {
        name.setText(user.getName());
    }

    public void btn_load(View view) {
        getPresenter().loadData();
    }
}
