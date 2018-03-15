package com.vode.aibuy.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.utils.GildeImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhouwei.indicatorview.CircleIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cj on 2018/3/12.
 */

public class GoodsAdapter  extends CommonAdapter<Goods> {


    public GoodsAdapter(Context context, List<Goods> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder<Goods> holder, Goods item, int positon) {
        switch (item.getType()){
            case 0:
                Banner banner = holder.getView(R.id.banner);
                banner.isAutoPlay(false);
                banner.setImageLoader(new GildeImageLoader());

                String strings[] = {
                        "http://218.192.170.132/BS80.jpg",
                        "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
                        "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
                        "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
                        "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
                        "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
                        "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};


                List<String> list = Arrays.asList(strings);

                banner.setImages(list);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.setDelayTime(2000);
                banner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                });
                banner.start();
                break;
            case 1:
                break;
            case 2:
                ViewPager pager = holder.getView(R.id.pager_goods);
                ArrayList<View> views = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_scroll_item, null);
                    views.add(inflate);
                }

                pager.setAdapter(new GoodsViewPagerAdapter(mContext, views));
                CircleIndicatorView indicatorView =  holder.getView(R.id.indicator_goods);
                indicatorView.setUpWithViewPager(pager);
                break;
            default:

                break;
        }

    }

    @Override
    public int getDatasItemType(int position, Goods item) {

        switch (item.getType()){
            case 0:
                return R.layout.goods_head_autoscroll;

            case 1:
                return R.layout.goods_head;

            case 2:
                return R.layout.goods_head_scroll;

            default:
                return R.layout.goods_item;

        }
    }

}
