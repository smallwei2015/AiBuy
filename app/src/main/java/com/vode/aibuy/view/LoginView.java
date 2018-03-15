package com.vode.aibuy.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.vode.aibuy.bean.User;

/**
 * Created by cj on 2018/3/9.
 */

public interface LoginView extends MvpView {
    void loginSucccess(User user);
}
