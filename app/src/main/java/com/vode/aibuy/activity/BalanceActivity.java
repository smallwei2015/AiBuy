package com.vode.aibuy.activity;

import android.util.Log;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Result1;
import com.vode.aibuy.model.ModelClient;
import com.vode.aibuy.model.UserManager;
import com.vode.aibuy.utils.SignUtils;
import com.vode.aibuy.utils.UIUtils;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BalanceActivity extends BaseActivityWithoutMVP {

    @Override
    void initData() {
        loadBalance("1");
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_balance);
        initTop(R.mipmap.left_white, "我的余额", -1);

    }


    private void loadBalance(String type){
        /*type
                user_id
        count
                first_Row
        page_count*/

        Map<String, String> map = SignUtils.getMap();
        map.put("type",type);
        map.put("user_id", UserManager.getAppuserId());

        String result = SignUtils.getResult(map, 2);
        ModelClient.retrofit.balance(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtils.showError();
                    }

                    @Override
                    public void onNext(Result1 result) {
                        Log.w("vode",result.getResult().toString());
                    }
                });
    }

}
