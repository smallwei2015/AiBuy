package com.vode.aibuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Condition;

import java.util.List;

/**
 * Created by cj on 2018/3/21.
 */

public class ConditionGridAdapter extends BaseAdapter {

    public Context context;
    public List<Condition> datas;

    public ConditionGridAdapter(Context context, List<Condition> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        if (datas != null) {
            return datas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_condition_grid_item, parent, false);


        return inflate;
    }
}
