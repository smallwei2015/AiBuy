package com.vode.aibuy.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.ConnectionData;

import java.util.List;

/**
 * Created by cj on 2018/3/16.
 */

public class ConnectionAdapter extends CommonAdapter<ConnectionData> implements View.OnClickListener {


    public List<ConnectionData> datas;


    public ConnectionAdapter(Context context, List<ConnectionData> datas) {
        super(context, datas);

        this.datas = datas;
    }

    @Override
    public void convert(ViewHolder<ConnectionData> holder, ConnectionData item, int positon) {

        holder.itemView.setTag(item);
        switch (item.getType()) {
            case 0:

                ImageView menu = holder.getView(R.id.menu);
                menu.setTag(item);
                menu.setOnClickListener(this);
                break;
            case 1:

                break;
        }
    }

    @Override
    public int getDatasItemType(int position, ConnectionData item) {

        switch (item.getType()) {
            case 0:
                return R.layout.layout_connection_first;
            case 1:
                return R.layout.layout_connection_second;
            default:
                return 0;
        }
    }

    @Override
    public void onClick(View v) {
        ConnectionData connectionData = (ConnectionData) v.getTag();

        int tag = datas.indexOf(connectionData);

        if (v.getId() == R.id.menu) {
            List<ConnectionData> sons = connectionData.getSons();
            if (connectionData.getState() == 0) {
                //设置打开
                RotateAnimation rotate = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                LinearInterpolator lin = new LinearInterpolator();
                rotate.setInterpolator(lin);
                rotate.setDuration(300);//设置动画持续周期
                rotate.setRepeatCount(0);//设置重复次数
                rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
                rotate.setStartOffset(10);//执行前的等待时间
                v.setAnimation(rotate);

                connectionData.setState(1);
                datas.addAll(tag + 1, sons);
                notifyItemRangeInserted(tag + 1, sons.size());

            } else {
                //设置关闭
                RotateAnimation rotate = new RotateAnimation(90f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                LinearInterpolator lin = new LinearInterpolator();
                rotate.setInterpolator(lin);
                rotate.setDuration(300);//设置动画持续周期
                rotate.setRepeatCount(0);//设置重复次数
                rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
                rotate.setStartOffset(10);//执行前的等待时间
                v.setAnimation(rotate);

                connectionData.setState(0);
                datas.removeAll(sons);
                notifyItemRangeRemoved(tag + 1, sons.size());
            }
        }
    }
}
