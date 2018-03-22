package com.vode.aibuy.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.FeedBackAdapter;
import com.vode.aibuy.bean.Datawrap;
import com.vode.aibuy.bean.Feedback;
import com.vode.aibuy.present.FeedbackPresent;
import com.vode.aibuy.view.BaseView;

import java.util.ArrayList;
import java.util.List;

public class FeedBackActivity extends BaseActivity<BaseView<List<Feedback>>,FeedbackPresent> implements BaseView<List<Feedback>> {

    public RecyclerView rec;
    public List<Datawrap> datas;
    public FeedBackAdapter adapter;

    @Override
    void initData() {
        adapter.showItemView();
        datas.add(new Datawrap());

        for (int i = 0; i < 5; i++) {
            Datawrap e = new Datawrap();
            e.setType(1);
            datas.add(e);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_feed_back);
        initTop(R.mipmap.left_white,"反馈",-1);
        rec = ((RecyclerView) findViewById(R.id.feedback_rec));
        rec.setLayoutManager(new LinearLayoutManager(mActivity));
        rec.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayout.VERTICAL));
        datas = new ArrayList<>();
        adapter = new FeedBackAdapter(mActivity, datas);
        rec.setAdapter(adapter);
    }

    @NonNull
    @Override
    public FeedbackPresent createPresenter() {
        return new FeedbackPresent();
    }

    @Override
    public void loadSuccess(List<Feedback> datas) {

    }

    @Override
    public void loadFaild(Throwable e) {

    }
}
