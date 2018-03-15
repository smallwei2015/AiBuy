package com.vode.aibuy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.UserItem;

import java.util.List;

/**
 * Created by cj on 2018/3/14.
 */

public class UserGridAdapter extends ArrayAdapter<UserItem> {

    public Context context;
    public int resource;
    public List<UserItem> objects;


    @Override
    public int getCount() {
        if (objects != null) {
            return objects.size();
        }else {
            return 0;
        }
    }

    public UserGridAdapter(@NonNull Context context, int resource, @NonNull List<UserItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource=resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        UserItem item = objects.get(position);
        if (item != null) {
            ((ImageView) convertView.findViewById(R.id.img)).setImageResource(item.getSrc());
            ((TextView) convertView.findViewById(R.id.title)).setText(item.getTitle());
        }
        return convertView;

    }
}
