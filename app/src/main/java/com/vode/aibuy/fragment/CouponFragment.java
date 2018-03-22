package com.vode.aibuy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.CommonAdapter;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.Coupon;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends BaseFragment {


    public RecyclerView rec;
    public ArrayList<Coupon> coupons;
    public CommonAdapter<Coupon> adapter;

    public CouponFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coupon, container, false);
    }

    @Override
    void initView(View view) {
        rec = ((RecyclerView) view.findViewById(R.id.coupon_rec));
        rec.setLayoutManager(new LinearLayoutManager(activity));
        //rec.addItemDecoration(new ItemDecoration(20));
        coupons = new ArrayList<>();


        adapter = new CommonAdapter<Coupon>(activity, coupons) {
            @Override
            public void convert(ViewHolder holder, Coupon item, int positon) {

            }

            @Override
            public int getDatasItemType(int position, Coupon item) {
                return R.layout.layout_coupon_item;
            }
        };
        rec.setAdapter(adapter);
    }

    @Override
    void initData() {
        for (int i = 0; i < 10  ; i++) {
            coupons.add(new Coupon());
        }

        adapter.showItemView();
        adapter.notifyDataSetChanged();
    }
}
