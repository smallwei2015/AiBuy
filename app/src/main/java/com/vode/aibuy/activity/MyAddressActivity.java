package com.vode.aibuy.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.CommonAdapter;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.Address;

import java.util.ArrayList;

public class MyAddressActivity extends BaseActivityWithoutMVP {

    public RecyclerView rec;
    public ArrayList<Address> addresses;
    public CommonAdapter<Address> adapter;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_my_address);
        initTop(R.mipmap.left_white,"我的地址",-1);

        rec = ((RecyclerView) findViewById(R.id.address_rec));
        rec.setLayoutManager(new LinearLayoutManager(mActivity));
        rec.addItemDecoration(new DividerItemDecoration(mActivity,LinearLayoutManager.VERTICAL));
        addresses = new ArrayList<>();
        adapter = new CommonAdapter<Address>(mActivity, addresses) {
            @Override
            public void convert(ViewHolder<Address> holder, Address item, int positon) {

            }

            @Override
            public int getDatasItemType(int position, Address item) {
                return R.layout.address_item;
            }
        };
        rec.setAdapter(adapter);
    }


    public void btn_addAddress(View view) {

    }
}
