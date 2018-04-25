package com.vode.aibuy.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.WalletRecordAdapter;
import com.vode.aibuy.bean.Result1;
import com.vode.aibuy.bean.WalletRecord;
import com.vode.aibuy.model.ModelClient;
import com.vode.aibuy.model.UserManager;
import com.vode.aibuy.present.WalletPresent;
import com.vode.aibuy.utils.SignUtils;
import com.vode.aibuy.utils.UIUtils;
import com.vode.aibuy.view.BaseView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WalletActivity extends BaseActivity<BaseView<List<WalletRecord>>,WalletPresent> implements BaseView<List<WalletRecord>>, View.OnClickListener {

    public TextView left;
    public TextView center;
    public TextView right;

    private int checkPos=1;
    public RecyclerView rec;
    public ArrayList<WalletRecord> datas;
    public WalletRecordAdapter adapter;

    @Override
    void initData() {
        loadPoint("all");
    }

    private void loadPoint(String type) {

        /*type
        user_id
        count
        first_Row
        page_count*/

        Map<String, String> map = SignUtils.getMap();
        map.put("type",type);
        map.put("user_id", UserManager.getAppuserId());
        String result = SignUtils.getResult(map, 2);

        ModelClient.retrofit.point(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        adapter.showErrorView();
                        UIUtils.showError();
                    }

                    @Override
                    public void onNext(Result1 result1) {

                    }
                });
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_wallet);

        initTop(R.mipmap.left_white,"我的钱包",-1);
        left = ((TextView) findViewById(R.id.menu_left));
        center = ((TextView) findViewById(R.id.menu_center));
        right = ((TextView) findViewById(R.id.menu_right));

        left.setOnClickListener(this);
        right.setOnClickListener(this);
        center.setOnClickListener(this);

        rec = ((RecyclerView) findViewById(R.id.wallet_rec));
        rec.setLayoutManager(new LinearLayoutManager(mActivity));
        rec.addItemDecoration(new DividerItemDecoration(mActivity,LinearLayoutManager.VERTICAL));
        datas = new ArrayList<>();
        adapter = new WalletRecordAdapter(mActivity, datas);
        rec.setAdapter(adapter);
    }


    @NonNull
    @Override
    public WalletPresent createPresenter() {
        return new WalletPresent();
    }

    @Override
    public void loadSuccess(List<WalletRecord> datas) {

    }

    @Override
    public void loadFaild(Throwable e) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_left:

                left.setEnabled(false);
                center.setEnabled(true);
                right.setEnabled(true);

                checkPos=1;
                break;
            case R.id.menu_center:
                left.setEnabled(true);
                center.setEnabled(false);
                right.setEnabled(true);

                checkPos=2;
                break;
            case R.id.menu_right:
                left.setEnabled(true);
                center.setEnabled(true);
                right.setEnabled(false);

                checkPos=3;
                break;
        }
    }
}
