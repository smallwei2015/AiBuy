package com.vode.aibuy.activity;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.CommenStringAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

public class HotSearchActivity extends BaseActivityWithoutMVP {

    public TagFlowLayout flow;
    public ArrayList<String> datas;
    public CommenStringAdapter adapter;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_hot_search);

        flow = ((TagFlowLayout) findViewById(R.id.hot_flow));
        datas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            datas.add("hello vode");
        }
        adapter = new CommenStringAdapter(datas,R.layout.tag_large_text);
        flow.setAdapter(adapter);
    }

}
