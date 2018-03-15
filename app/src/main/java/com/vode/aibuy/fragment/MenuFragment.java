package com.vode.aibuy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.MenuLeftAdapter;
import com.vode.aibuy.adapter.OnItemClickListener;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.Menudata;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends BaseFragment {


    public RecyclerView recLeft;
    public ArrayList<Menudata> lefrDatas;
    public MenuLeftAdapter leftAdapter;
    public RecyclerView recRight;
    public ArrayList<Menudata> rightDatas;
    public MenuLeftAdapter rightAdapter;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    void initView(View view) {
        recLeft = view.findViewById(R.id.rec_left);
        recLeft.setLayoutManager(new LinearLayoutManager(activity));
        lefrDatas = new ArrayList<>();
        leftAdapter = new MenuLeftAdapter(activity, lefrDatas);
        recLeft.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new OnItemClickListener<Menudata>() {
            @Override
            public void onItemClick(ViewHolder<Menudata> holder, Menudata item, int position) {

            }
        });

        recRight = view.findViewById(R.id.rec_right);
        GridLayoutManager  manager= new GridLayoutManager(activity, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Menudata menudata = rightDatas.get(position);
                if (menudata.getType()==1) {
                    return 1;
                }else {
                    /*标题占三列*/
                    return 3;
                }
            }
        });
        recRight.setLayoutManager(manager);

        rightDatas = new ArrayList<>();
        rightAdapter = new MenuLeftAdapter(activity, rightDatas);
        recRight.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new OnItemClickListener<Menudata>() {
            @Override
            public void onItemClick(ViewHolder<Menudata> holder, Menudata item, int position) {

            }
        });

    }

    @Override
    void initData() {
        leftAdapter.showItemView();
        rightAdapter.showItemView();
        for (int i = 0; i < 10; i++) {
            lefrDatas.add(new Menudata());
        }
        leftAdapter.notifyDataSetChanged();

        Menudata e = new Menudata();
        e.setType(0);
        rightDatas.add(e);
        for (int i = 0; i < 10; i++) {
            Menudata e1 = new Menudata();
            e1.setType(1);
            rightDatas.add(e1);
        }

        rightAdapter.notifyDataSetChanged();
    }
}
