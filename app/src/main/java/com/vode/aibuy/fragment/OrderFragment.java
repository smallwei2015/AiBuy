package com.vode.aibuy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.CommonAdapter;
import com.vode.aibuy.adapter.ItemDecoration;
import com.vode.aibuy.adapter.OrderListAdapter;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.Order;
import com.vode.aibuy.bean.OrderGoods;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment {


    public RecyclerView rec;
    public ArrayList<Order> orders;
    public CommonAdapter<Order> adapter;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    void initView(View view) {
        rec = ((RecyclerView) view.findViewById(R.id.order_rec));
        rec.setLayoutManager(new LinearLayoutManager(activity));
        rec.addItemDecoration(new ItemDecoration(10));
        orders = new ArrayList<>();
        adapter = new CommonAdapter<Order>(activity, orders) {

            public ArrayList<OrderGoods> listdata;

            @Override
            public void convert(ViewHolder holder, Order item, int positon) {
                ListView listView = (ListView) holder.getView(R.id.order_list);
                listdata = new ArrayList<>();

                for (int i = 0; i < 3; i++) {
                    listdata.add(new OrderGoods());
                }
                listView.setAdapter(new OrderListAdapter(activity, listdata));
            }

            @Override
            public int getDatasItemType(int position, Order item) {
                return R.layout.layout_order_item;
            }
        };
        rec.setAdapter(adapter);
    }

    @Override
    void initData() {
        for (int i = 0; i < 10; i++) {
            orders.add(new Order());
        }
        adapter.showItemView();
        adapter.notifyDataSetChanged();
    }
}
