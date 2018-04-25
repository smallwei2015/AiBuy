package com.vode.aibuy.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.vode.aibuy.R;
import com.vode.aibuy.fragment.CouponFragment;

import java.util.ArrayList;
import java.util.List;

public class CouponActivity extends BaseActivityWithoutMVP {

    public TabLayout tab;
    public ViewPager rec;
    public List<String> titles;
    public FragmentStatePagerAdapter adapter;

    @Override
    void initData() {


    }

    @Override
    void initView() {
        setContentView(R.layout.activity_coupon);
        initTop(R.mipmap.left_white,"我的优惠券",-1);

        tab = ((TabLayout) findViewById(R.id.coupon_tab));
        rec = ((ViewPager) findViewById(R.id.coupon_pager));

        titles = new ArrayList<>();

        titles.add("未使用");
        titles.add("已使用");
        titles.add("已过期");

        adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                if (titles != null) {
                    return titles.size();
                }
                return 0;
            }

            @Override
            public Fragment getItem(int position) {
                CouponFragment couponFragment = new CouponFragment();
                Bundle args = new Bundle();
                args.putInt("flag",position);
                couponFragment.setArguments(args);
                return couponFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };
        rec.setAdapter(adapter);
        tab.setupWithViewPager(rec);
    }



}
