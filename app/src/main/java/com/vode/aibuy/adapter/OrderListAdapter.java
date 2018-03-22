package com.vode.aibuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.OrderGoods;

import java.util.List;

/**
 * Created by cj on 2018/3/20.
 */

public class OrderListAdapter extends BaseAdapter {


    private List<OrderGoods> goodsData;
    public Context context;

    public OrderListAdapter(Context context,List<OrderGoods> listdata) {
        this.context = context;
        goodsData = listdata;
    }

    @Override
    public int getCount() {
        if (goodsData != null) {
            return goodsData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.order_goods_item, parent, false);

        OrderGoods goods = goodsData.get(position);


        /*ImageView icon = (ImageView) inflate.findViewById(R.id.order_img);
        Glide.with(context).load(goods.getImg()).into(icon);
        ((TextView) inflate.findViewById(R.id.order_des)).setText(goods.getTitle());

        ((TextView) inflate.findViewById(R.id.order_count)).setText("￥" + goods.getPrice() + "(x" + goods.getCount() + ")");

        ((TextView) inflate.findViewById(R.id.order_price)).setText("￥" + String.format("%.2f", goods.getPrice() * goods.getCount()));*/


        return inflate;
    }

}
