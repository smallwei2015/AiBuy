package com.vode.aibuy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.CommonAdapter;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.Coupon;
import com.vode.aibuy.bean.Result1;
import com.vode.aibuy.model.ModelClient;
import com.vode.aibuy.model.UserManager;
import com.vode.aibuy.utils.SignUtils;
import com.vode.aibuy.utils.UIUtils;

import java.util.ArrayList;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends BaseFragment {


    public RecyclerView rec;
    public ArrayList<Coupon> coupons;
    public CommonAdapter<Coupon> adapter;
    public int flag;

    public CouponFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coupon, container, false);
    }

    @Override
    void initView(View view) {
        rec = ((RecyclerView) view.findViewById(R.id.coupon_rec));
        rec.setLayoutManager(new LinearLayoutManager(activity));
        //rec.addItemDecoration(new ItemDecoration(20));
        coupons = new ArrayList<>();


        adapter = new CommonAdapter<Coupon>(activity, coupons) {
            @Override
            public void convert(ViewHolder holder, Coupon item, int positon) {

            }

            @Override
            public int getDatasItemType(int position, Coupon item) {
                return R.layout.layout_coupon_item;
            }
        };
        rec.setAdapter(adapter);
    }

    @Override
    void initData() {

        Bundle arguments = getArguments();
        flag = arguments.getInt("flag", 0);

        loadCoupon(flag+"");


    }

    private void loadCoupon(String type){

        /*type
        user_id
        count
        first_Row
        page_count*/

        Map<String, String> map = SignUtils.getMap();
        map.put("type",type);
        map.put("user_id", UserManager.getAppuserId());

        String result = SignUtils.getResult(map, 2);

        ModelClient.retrofit.coupon(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtils.showError();
                        adapter.showErrorView();
                    }

                    @Override
                    public void onNext(Result1 result1) {

                        if (result1.getCode()==200) {
                            adapter.showItemView();
                            adapter.notifyDataSetChanged();
                        }else {
                            UIUtils.showToast(result1.getMessage());
                        }
                    }
                });
    }
}
