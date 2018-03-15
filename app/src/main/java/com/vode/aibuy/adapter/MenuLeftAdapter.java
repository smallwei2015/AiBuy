package com.vode.aibuy.adapter;

import android.content.Context;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Menudata;

import java.util.List;

/**
 * Created by cj on 2018/3/12.
 */

public class MenuLeftAdapter extends CommonAdapter<Menudata> {
    public MenuLeftAdapter(Context context, List<Menudata> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder<Menudata> holder, Menudata item, int positon) {
        if (item.getType()==1){
            holder.setText(R.id.title,"title");
        }else {
            holder.setText(R.id.title, "vode");
        }
    }

    @Override
    public int getDatasItemType(int position, Menudata item) {

        if (item.getType()==1){
            return R.layout.goods_item;
        }else {
            return R.layout.menu_left_item;
        }
    }
}
