package com.vode.aibuy.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.ShoppingCartAdapter;
import com.vode.aibuy.bean.ShopCartGoods;
import com.vode.aibuy.model.LoadDataInteface;
import com.vode.aibuy.model.ModelClient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCartFragment extends AutoFreshFragment {


    public RecyclerView recyclerView;
    public ArrayList<List<ShopCartGoods>> datas;
    public ShoppingCartAdapter adapter;
    public SwipeRefreshLayout swip;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    @Override
    void initView(View view) {
        swip = view.findViewById(R.id.swip_cart);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fresh(null);

                swip.setRefreshing(false);
            }
        });
        recyclerView = view.findViewById(R.id.rec_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new DividerItemDecoration(activity,LinearLayoutManager.VERTICAL));
        datas = new ArrayList<>();
        adapter = new ShoppingCartAdapter(activity, datas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    void initData() {
        ModelClient.loadCartGoods(new LoadDataInteface<List<ShopCartGoods>>() {
            @Override
            public void onDataLoaded(List<ShopCartGoods> data) {
                adapter.showItemView();
            }

            @Override
            public void onDataLoadFailed(Throwable e) {
                adapter.showErrorView();
            }
        });
    }

    @Override
    void fresh(Intent intent) {
        //收到广播自动刷新
        adapter.showItemView();
        for (int i = 0; i < 10; i++) {
            datas.add(new ArrayList<ShopCartGoods>());
        }
        adapter.notifyDataSetChanged();
    }
}
