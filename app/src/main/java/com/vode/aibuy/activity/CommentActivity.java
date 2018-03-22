package com.vode.aibuy.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.vode.aibuy.R;
import com.vode.aibuy.fragment.CommentFragment;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivityWithoutMVP {

    public TabLayout tab;
    public ViewPager pager;
    public List<String> titles;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_comment);
        initTop(R.mipmap.left_white,"我的评价",-1);

        tab = ((TabLayout) findViewById(R.id.comment_tab));
        pager = ((ViewPager) findViewById(R.id.comment_pager));

        titles = new ArrayList<>();

        titles.add("全部评价");
        titles.add("待评价");
        titles.add("已评价");
        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                if (titles != null) {
                    return titles.size();
                }
                return 0;
            }

            @Override
            public Fragment getItem(int position) {
                CommentFragment commentFragment = new CommentFragment();
                Bundle args = new Bundle();
                args.putSerializable("data",titles.get(position));
                commentFragment.setArguments(args);
                return commentFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });

        tab.setupWithViewPager(pager);
    }

}
