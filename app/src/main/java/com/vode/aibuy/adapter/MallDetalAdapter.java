package com.vode.aibuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.utils.GildeImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cj on 2018/3/14.
 */

public class MallDetalAdapter extends CommonAdapter<Goods> {

    public LayoutInflater inflater;

    public MallDetalAdapter(Context context, List<Goods> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder<Goods> holder, Goods item, int positon) {
        inflater = LayoutInflater.from(mContext);
        switch (item.getType()){
            case 0:
                Banner banner = holder.getView(R.id.mall_detail_scroll);
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
                TextView title=holder.getView(R.id.mall_title);
                title.setText(item.getName());
                TagFlowLayout flow=holder.getView(R.id.mall_flowlayout);
                ArrayList<String> flowDatas = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    flowDatas.add("hello vode");
                }
                TagAdapter adapter = new TagAdapter<String>(flowDatas) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        TextView view = (TextView) inflater.inflate(R.layout.tag_text, parent, false);
                        view.setText(o);
                        return view;
                    }
                };
                flow.setAdapter(adapter);

                break;
        }
    }

    @Override
    public int getDatasItemType(int position, Goods item) {
        switch (item.getType()){
            case 0:
                return R.layout.layout_mall_detail_scroll;
            case 1:
                return R.layout.layout_mall_detial_select;

        }
        return 0;
    }
}
