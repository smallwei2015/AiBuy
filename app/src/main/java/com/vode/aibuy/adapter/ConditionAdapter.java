package com.vode.aibuy.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Condition;

import java.util.List;

/**
 * Created by cj on 2018/3/21.
 */

public class ConditionAdapter extends CommonAdapter<Condition> implements View.OnClickListener{

    public List<Condition> datas;

    public ConditionAdapter(Context context, List<Condition> datas) {
        super(context, datas);

        this.datas = datas;
    }


    @Override
    public int getDatasItemType(int position, Condition item) {
        switch (item.getType()) {
            case 0:
                return R.layout.layout_condition_first;
            case 1:
                return R.layout.layout_condition_grid_item;
            default:
                return 0;
        }
    }


    @Override
    public void convert(ViewHolder<Condition> holder, Condition item, int positon) {
        switch (item.getType()) {
            case 0:

                ((TextView) holder.getView(R.id.title)).setText(item.getTitle());
                TextView state = (TextView) holder.getView(R.id.count);
                if (item.getState()==0) {
                    state.setText("展开");
                }else {
                    state.setText("收起");
                }

                ImageView menu = holder.getView(R.id.menu);
                menu.setTag(item);
                menu.setOnClickListener(this);
                break;
            case 1:

                /*NoScrollGridView gridView = (NoScrollGridView) holder.getView(R.id.condition_grid);

                gridView.setNumColumns(4);
                ArrayList<Condition> datas = new ArrayList<>();

                datas.addAll(item.getSons());
                gridView.setAdapter(new ConditionGridAdapter(mContext, datas));*/
                break;
        }
    }


    @Override
    public void onClick(View v) {
        Condition connectionData = (Condition) v.getTag();

        int tag = datas.indexOf(connectionData);

        if (v.getId() == R.id.menu) {
            List<Condition> sons = connectionData.getSons();
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
                notifyItemChanged(tag);

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
                notifyItemChanged(tag);
            }

        }
    }
}
