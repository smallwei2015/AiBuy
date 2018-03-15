package com.vode.aibuy.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by cj on 2018/3/13.
 */

public interface BaseView<T> extends MvpView {
    void loadSuccess(T datas);
    void loadFaild(Throwable e);
}
