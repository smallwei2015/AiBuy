package com.vode.aibuy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cj on 2018/3/14.
 */

public class GoodsViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    public Context context;

    public GoodsViewPagerAdapter(Context context,List<View> views) {

        this.views=views;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);

        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        container.addView(views.get(position));
        return views.get(position);
    }
}
