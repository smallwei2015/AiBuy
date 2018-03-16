package com.vode.aibuy.present;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by cj on 2018/3/12.
 */

abstract class MyBasePresent<V extends MvpView> extends MvpBasePresenter<V> {
    abstract void loadData(boolean pullToRefresh);
}
