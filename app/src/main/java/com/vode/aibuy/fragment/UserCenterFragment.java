package com.vode.aibuy.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.UserGridAdapter;
import com.vode.aibuy.bean.UserItem;
import com.vode.aibuy.userview.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCenterFragment extends AutoFreshFragment {


    public NoScrollGridView gridView;

    public UserCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_center, container, false);
    }

    @Override
    void initView(View view) {
        gridView = view.findViewById(R.id.grid_user);
        List<UserItem> datas=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserItem e = new UserItem();
            e.setSrc(R.mipmap.ic_launcher);
            e.setTitle("title");
            datas.add(e);
        }
        UserGridAdapter adapter = new UserGridAdapter(activity, R.layout.user_item,datas);

        gridView.setAdapter(adapter);
    }

    @Override
    void initData() {

    }

    @Override
    void fresh(Intent intent) {

    }
}
