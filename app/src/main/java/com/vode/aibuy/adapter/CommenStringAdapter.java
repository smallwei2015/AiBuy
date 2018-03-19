package com.vode.aibuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.utils.UIUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by cj on 2018/3/19.
 */

public class CommenStringAdapter extends TagAdapter<String> {

    public Context context;
    public LayoutInflater inflater;
    public int tagLayout;


    public CommenStringAdapter(List<String> datas) {
        super(datas);
    }


    public CommenStringAdapter(List<String> datas, int tagLayout) {
        super(datas);
        this.tagLayout = tagLayout;
    }

    @Override
    public void onSelected(int position, View view) {
        super.onSelected(position, view);

        String item = getItem(position);
        UIUtils.showToast(item);
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {

        if (context == null || inflater == null) {
            context = parent.getContext();
            inflater = LayoutInflater.from(context);
        }

        TextView view;
        if (tagLayout > 0) {
            view = (TextView) inflater.inflate(tagLayout, parent, false);
        } else {
            view = (TextView) inflater.inflate(R.layout.tag_text, parent, false);
        }
        view.setText(s);
        return view;
    }
}
