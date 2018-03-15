package com.vode.aibuy.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.MallDetalAdapter;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.present.MallDetailPresent;
import com.vode.aibuy.view.BaseView;

import java.util.ArrayList;

public class MallDetailActivity extends BaseActivity<BaseView<Goods>,MallDetailPresent> implements BaseView<Goods> {

    public SwipeRefreshLayout swip;
    public RecyclerView rec;
    public ArrayList<Goods> datas;
    public MallDetalAdapter adapter;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_mall_detail);

        swip = findViewById(R.id.mall_swip);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swip.setRefreshing(false);
            }
        });
        rec = findViewById(R.id.mall_rec);
        rec.setLayoutManager(new LinearLayoutManager(mActivity));
        rec.addItemDecoration(new DividerItemDecoration(mActivity,LinearLayoutManager.VERTICAL));
        datas = new ArrayList<>();

        datas.add(new Goods());
        adapter = new MallDetalAdapter(mActivity, datas);
        adapter.showItemView();
        rec.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public MallDetailPresent createPresenter() {
        return new MallDetailPresent();
    }

    @Override
    public void loadSuccess(Goods datas) {

    }

    @Override
    public void loadFaild(Throwable e) {

    }
}
