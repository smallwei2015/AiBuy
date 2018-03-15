package com.vode.aibuy.present;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vode.aibuy.bean.User;
import com.vode.aibuy.model.LoadDataInteface;
import com.vode.aibuy.model.ModelClient;
import com.vode.aibuy.view.LoginView;

/**
 * Created by cj on 2018/3/9.
 */

public class LoginPresent extends MvpBasePresenter<LoginView> {

    public void loadData() {

        if (!isViewAttached()) {
            return;
        }
        ModelClient.loadUser(new LoadDataInteface<User>() {
            @Override
            public void onDataLoaded(User data) {
                getView().loginSucccess(data);
            }

            @Override
            public void onDataLoadFailed(Throwable e) {

            }
        });

    }

    @Override
    public void attachView(LoginView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {

    }
}
