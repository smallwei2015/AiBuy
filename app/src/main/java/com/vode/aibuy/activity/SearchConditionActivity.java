package com.vode.aibuy.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.ConditionAdapter;
import com.vode.aibuy.bean.Condition;

import java.util.ArrayList;

public class SearchConditionActivity extends BaseActivityWithoutMVP {

    public RecyclerView rec;
    public ArrayList<Condition> datas;
    public ConditionAdapter adapter;

    @Override
    void initData() {
        for (int i = 0; i < 4; i++) {
            Condition condition = new Condition();
            condition.setTitle("title");
            ArrayList<Condition> sons = new ArrayList<>();
            condition.setSons(sons);
            for (int j = 0; j < 10; j++) {
                Condition e = new Condition();
                e.setType(1);
                sons.add(e);
            }

            datas.add(condition);
        }

        adapter.showItemView();
        adapter.notifyDataSetChanged();
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_search_condition);
        initTop(R.mipmap.left_white,"条件选择",-1);


        rec = ((RecyclerView) findViewById(R.id.condition_rec));
        GridLayoutManager layout = new GridLayoutManager(mActivity, 4);
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Condition condition = datas.get(position);

                if (condition.getType()==0){
                    return 4;
                }else {
                    return 1;
                }
            }
        });
        rec.setLayoutManager(layout);
        datas = new ArrayList<>();
        adapter = new ConditionAdapter(mActivity, datas);
        rec.setAdapter(adapter);
    }

}
