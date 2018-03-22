package com.vode.aibuy.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.activity.BalanceActivity;
import com.vode.aibuy.activity.CollectionActivity;
import com.vode.aibuy.activity.CommentActivity;
import com.vode.aibuy.activity.ConnectionActivity;
import com.vode.aibuy.activity.CouponActivity;
import com.vode.aibuy.activity.FeedBackActivity;
import com.vode.aibuy.activity.MyAddressActivity;
import com.vode.aibuy.activity.OrderActivity;
import com.vode.aibuy.activity.SettingActivity;
import com.vode.aibuy.activity.WalletActivity;
import com.vode.aibuy.activity.WithDrawActivity;
import com.vode.aibuy.adapter.UserGridAdapter;
import com.vode.aibuy.bean.UserItem;
import com.vode.aibuy.userview.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCenterFragment extends AutoFreshFragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    public NoScrollGridView gridView;
    public TextView tv_name;
    public TextView tv_date;
    public ImageView im_icon;
    public List<UserItem> datas;
    public UserGridAdapter adapter;
    public TextView tv_order;
    public TextView tv_collect;
    public TextView tv_comment;
    public TextView tv_balance;
    public TextView tv_coupon;
    public TextView tv_score;
    public TextView tv_order1Count;
    public TextView tv_order2Count;
    public TextView tv_order3Count;
    public TextView tv_order4Count;


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

        view.findViewById(R.id.user_setting).setOnClickListener(this);
        tv_name = ((TextView) view.findViewById(R.id.user_name));
        tv_date = ((TextView) view.findViewById(R.id.user_date));
        im_icon = ((ImageView) view.findViewById(R.id.user_icon));



        view.findViewById(R.id.user_order_parent).setOnClickListener(this);
        tv_order = ((TextView) view.findViewById(R.id.user_order));
        tv_order.setText("0");

        view.findViewById(R.id.user_collect_parent).setOnClickListener(this);
        tv_collect = ((TextView) view.findViewById(R.id.user_collect));
        tv_collect.setText("10");

        view.findViewById(R.id.user_comment_parent).setOnClickListener(this);
        tv_comment = ((TextView) view.findViewById(R.id.user_comment));
        tv_comment.setText("10");


        view.findViewById(R.id.user_balance_parent).setOnClickListener(this);
        tv_balance = ((TextView) view.findViewById(R.id.user_balance));
        tv_balance.setText("100.00");

        view.findViewById(R.id.user_coupon_parent).setOnClickListener(this);
        tv_coupon = ((TextView) view.findViewById(R.id.user_coupon));
        tv_coupon.setText("20.10");

        view.findViewById(R.id.user_score_parent).setOnClickListener(this);
        tv_score = ((TextView) view.findViewById(R.id.user_score));
        tv_score.setText("100");


        view.findViewById(R.id.user_order1).setOnClickListener(this);
        view.findViewById(R.id.user_order2).setOnClickListener(this);
        view.findViewById(R.id.user_order3).setOnClickListener(this);
        view.findViewById(R.id.user_order4).setOnClickListener(this);

        tv_order1Count = ((TextView) view.findViewById(R.id.user_order1_count));
        tv_order2Count = ((TextView) view.findViewById(R.id.user_order2_count));
        tv_order3Count = ((TextView) view.findViewById(R.id.user_order3_count));
        tv_order4Count = ((TextView) view.findViewById(R.id.user_order4_count));

        tv_order1Count.setText("2");
        tv_order2Count.setText("1");
        tv_order3Count.setText("0");
        tv_order4Count.setText("100");

        view.findViewById(R.id.user_allorder).setOnClickListener(this);

        gridView = view.findViewById(R.id.grid_user);
        datas = new ArrayList<>();
        adapter = new UserGridAdapter(activity, R.layout.user_item, datas);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(adapter);

    }

    @Override
    void initData() {
        UserItem e = new UserItem("我的二维码", R.mipmap.user5);
        datas.add(e);
        e = new UserItem("地址管理", R.mipmap.user6);
        datas.add(e);
        e = new UserItem("我的人脉", R.mipmap.user7);
        datas.add(e);
        e = new UserItem("我的钱包", R.mipmap.user8);
        datas.add(e);
        e = new UserItem("企业福利", R.mipmap.user9);
        datas.add(e);
        e = new UserItem("领取奖励", R.mipmap.user10);
        datas.add(e);
        e = new UserItem("商家入驻", R.mipmap.user11);
        datas.add(e);
        e = new UserItem("我要提现", R.mipmap.user12);
        datas.add(e);
        e = new UserItem("Plus会员", R.mipmap.user13);
        datas.add(e);
        e = new UserItem("无息贷款", R.mipmap.user14);
        datas.add(e);
        e = new UserItem("公益服务", R.mipmap.user15);
        datas.add(e);
        e = new UserItem("我要反馈", R.mipmap.user16);
        datas.add(e);
        adapter.notifyDataSetChanged();
    }

    @Override
    void fresh(Intent intent) {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.user_setting:
                intent = new Intent(activity, SettingActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Bundle options = ActivityOptions.makeSceneTransitionAnimation(activity, im_icon, getResources().getString(R.string.tran_name)).toBundle();
                    startActivity(intent, options);
                } else {
                    startActivity(intent);
                }
                break;
            case R.id.user_order_parent:
                intent=new Intent(activity,OrderActivity.class);
                startActivity(intent);
                break;

            case R.id.user_collect_parent:
                intent=new Intent(activity,CollectionActivity.class);
                startActivity(intent);
                break;

            case R.id.user_comment_parent:
                intent=new Intent(activity,CommentActivity.class);
                startActivity(intent);
                break;
            case R.id.user_balance_parent:
                intent=new Intent(activity,BalanceActivity.class);
                startActivity(intent);
                break;
            case R.id.user_coupon_parent:
                intent=new Intent(activity,CouponActivity.class);
                startActivity(intent);
                break;
            case R.id.user_score_parent:
                intent=new Intent(activity,WalletActivity.class);
                startActivity(intent);
                break;

            case R.id.user_order1:
                intent = new Intent(activity, OrderActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.user_order2:
                intent = new Intent(activity, OrderActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            case R.id.user_order3:
                intent = new Intent(activity, OrderActivity.class);
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            case R.id.user_order4:
                intent = new Intent(activity, OrderActivity.class);
                intent.putExtra("type",4);
                startActivity(intent);
                break;

            case R.id.user_allorder:
                intent = new Intent(activity, OrderActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position){
            case 0:

                break;
            case 1:
                intent=new Intent(activity, MyAddressActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent=new Intent(activity, ConnectionActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent=new Intent(activity,WalletActivity.class);
                startActivity(intent);
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:
                intent=new Intent(activity, WithDrawActivity.class);
                startActivity(intent);
                break;
            case 8:
                intent=new Intent();
                break;
            case 9:

                break;
            case 10:

                break;
            case 11:
                intent=new Intent(activity, FeedBackActivity.class);
                startActivity(intent);
                break;
        }
    }
}
