package com.vode.aibuy.present;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.model.LoadDataInteface;
import com.vode.aibuy.model.ModelClient;
import com.vode.aibuy.view.GoodsLceView;

import java.util.List;

/**
 * Created by cj on 2018/3/12.
 */

public class GoodsLcePresent extends MvpBasePresenter<GoodsLceView> implements MyBasePresent{

    public interface OnDataLoadedListener {
        void onDataLoaded(List<Goods> goods);
        void onDataLoadFailed(Throwable e);
    }
    public void loadData(final boolean pullToRefresh) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading(pullToRefresh);
        ModelClient.loadGoods(new LoadDataInteface<List<Goods>>() {

            @Override
            public void onDataLoaded(List<Goods> data) {
                getView().setData(data);
                getView().showContent();
            }

            @Override
            public void onDataLoadFailed(Throwable e) {
                getView().showError(e, pullToRefresh);
            }
        });
    }
}
