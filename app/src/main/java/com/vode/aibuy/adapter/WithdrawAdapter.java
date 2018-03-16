package com.vode.aibuy.adapter;

import android.content.Context;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Datawrap;

import java.util.List;

/**
 * Created by cj on 2018/3/16.
 */

public class WithdrawAdapter extends CommonAdapter<Datawrap> {

    public WithdrawAdapter(Context context, List<Datawrap> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder<Datawrap> holder, Datawrap item, int positon) {

    }

    @Override
    public int getDatasItemType(int position, Datawrap item) {
        if (item.getType()==0) {
            return R.layout.layout_withdraw;
        }else {
            return R.layout.layout_withdraw_item;
        }
    }
}
