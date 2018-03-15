package com.vode.aibuy.present;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.view.BaseView;

/**
 * Created by cj on 2018/3/14.
 */

public class MallDetailPresent extends MvpBasePresenter<BaseView<Goods>> implements MyBasePresent{
    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
