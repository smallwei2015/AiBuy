package com.vode.aibuy.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.vode.aibuy.R;
import com.vode.aibuy.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends BaseActivityWithoutMVP {

    public TabLayout tab;
    public ViewPager pager;
    public FragmentStatePagerAdapter adapter;
    public List<String> titles;
    public int type;

    @Override
    void initData() {
        type = getIntent().getIntExtra("type", 0);
        pager.setCurrentItem(type);
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_order);

        initTop(R.mipmap.left_white,"我的订单",-1);
        tab = findViewById(R.id.order_tab);


        titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("待发货");
        titles.add("待收货");
        titles.add("已完成");

        pager = ((ViewPager) findViewById(R.id.order_pager));
        adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                OrderFragment orderFragment = new OrderFragment();
                Bundle args = new Bundle();
                args.putSerializable("data",titles.get(position));
                orderFragment.setArguments(args);
                return orderFragment;
            }

            @Override
            public int getCount() {
                if (titles != null) {
                    return titles.size();
                }
                return 0;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);

            }
        };
        pager.setAdapter(adapter);

        tab.setupWithViewPager(pager);
    }


}
