package com.vode.aibuy.adapter;

import android.content.Context;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.userview.GoodsView;

import java.util.List;

/**
 * Created by cj on 2018/3/13.
 */

public class SearchGoodsAdapter extends CommonAdapter<Goods> {
    private int layoutType=0;

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public SearchGoodsAdapter(Context context, List<Goods> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder<Goods> holder, Goods item, int positon) {
        switch (layoutType){
            case 0:

                break;
            case 1:
                GoodsView goodsView=holder.getView(R.id.mall_goods_count);

                goodsView.setCount(2);
                break;
        }
    }

    @Override
    public int getDatasItemType(int position, Goods item) {

        if (layoutType==0){
            return R.layout.layout_search_goods_item_linear;
        }else {
            return R.layout.layout_search_goods_item_grid;
        }

    }
}
