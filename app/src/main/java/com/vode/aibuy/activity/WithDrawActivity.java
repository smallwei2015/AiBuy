package com.vode.aibuy.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.WithdrawAdapter;
import com.vode.aibuy.bean.Datawrap;
import com.vode.aibuy.bean.WithdrawRecord;
import com.vode.aibuy.present.WithdrawPresent;
import com.vode.aibuy.view.BaseView;

import java.util.ArrayList;
import java.util.List;

public class WithDrawActivity extends BaseActivity<BaseView<List<WithdrawRecord>>,WithdrawPresent> implements BaseView<List<WithdrawRecord>>{

    public RecyclerView rec;
    public ArrayList<Datawrap> datas;
    public WithdrawAdapter adapter;

    @Override
    void initData() {
        adapter.showItemView();

        datas.add(new Datawrap());
        for (int i = 0; i < 10; i++) {
            Datawrap e = new Datawrap();
            e.setType(1);
            datas.add(e);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_with_draw);

        initTop(R.mipmap.left_white,"提现申请",-1);
        rec = ((RecyclerView) findViewById(R.id.withdraw_rec));

        rec.setLayoutManager(new LinearLayoutManager(mActivity));
        rec.addItemDecoration(new DividerItemDecoration(mActivity,LinearLayoutManager.VERTICAL));
        datas = new ArrayList<>();
        adapter = new WithdrawAdapter(mActivity, datas);
        rec.setAdapter(adapter);
    }


    @NonNull
    @Override
    public WithdrawPresent createPresenter() {
        return new WithdrawPresent();
    }

    @Override
    public void loadSuccess(List<WithdrawRecord> datas) {

    }

    @Override
    public void loadFaild(Throwable e) {

    }
}
