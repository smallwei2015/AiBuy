package com.vode.aibuy.fragment;


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
import com.vode.aibuy.adapter.GoodsAdapter;
import com.vode.aibuy.bean.Goods;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsListFragment extends BaseFragment {


    public RecyclerView rec;
    public ArrayList<Goods> datas;
    public GoodsAdapter adapter;
    public SwipeRefreshLayout swip;

    public GoodsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_list, container, false);
    }

    @Override
    void initView(View view) {

        swip = view.findViewById(R.id.swip_goods_list);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swip.setRefreshing(false);
            }
        });
        rec = view.findViewById(R.id.rec_goods_list);
        rec.setLayoutManager(new LinearLayoutManager(activity));
        datas = new ArrayList<>();
        adapter = new GoodsAdapter(activity, datas);
        rec.addItemDecoration(new DividerItemDecoration(activity,LinearLayoutManager.VERTICAL));
        rec.setAdapter(adapter);
    }

    @Override
    void initData() {
        adapter.showItemView();
        Goods e1 = new Goods();
        e1.setType(1);
        datas.add(e1);

        for (int i = 0; i < 2; i++) {
            Goods e = new Goods();
            e.setType(0);
            datas.add(e);
        }

        Goods e = new Goods();
        e.setType(2);
        datas.add(e);

        adapter.notifyDataSetChanged();
    }
}
