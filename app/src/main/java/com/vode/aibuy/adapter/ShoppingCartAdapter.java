package com.vode.aibuy.adapter;

import android.content.Context;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.ShopCartGoods;

import java.util.List;

/**
 * Created by cj on 2018/3/14.
 */

public class ShoppingCartAdapter extends CommonAdapter<List<ShopCartGoods>> {
    public ShoppingCartAdapter(Context context, List<List<ShopCartGoods>> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder<List<ShopCartGoods>> holder, List<ShopCartGoods> item, int positon) {

    }

    @Override
    public int getDatasItemType(int position, List<ShopCartGoods> item) {
        return R.layout.layout_cart_item;
    }
}
