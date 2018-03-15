package com.vode.aibuy.present;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.model.LoadDataInteface;
import com.vode.aibuy.model.ModelClient;
import com.vode.aibuy.view.BaseView;

import java.util.List;

/**
 * Created by cj on 2018/3/13.
 */

public class SearchActivityPresent  extends MvpBasePresenter<BaseView<List<Goods>>> implements MyBasePresent {
    @Override
    public void loadData(boolean pullToRefresh) {
        if (!isViewAttached()) {
            return;
        }
        ModelClient.loadSearchGoods(new LoadDataInteface<List<Goods>>() {

            @Override
            public void onDataLoaded(List<Goods> data) {
                getView().loadSuccess(data);
                //getView().showContent();
            }

            @Override
            public void onDataLoadFailed(Throwable e) {
                getView().loadFaild(e);
                //getView().showError(e, pullToRefresh);
            }
        });
    }
}
