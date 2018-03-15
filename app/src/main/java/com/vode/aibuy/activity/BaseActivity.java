package com.vode.aibuy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.vode.aibuy.R;
import com.vode.aibuy.utils.StatusBarUtils;

/**
 * Created by cj on 2018/3/13.
 */

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {
    private View.OnClickListener ToolbarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.left:
                    onLeftClick(v);
                    break;
                case R.id.left_text:
                    onLeftTextClick(v);
                    break;
                case R.id.right_icon:
                    onRightIconClick(v);
                    break;
                case R.id.right_text:
                    onRightTextClick(v);
                    break;
                case R.id.left_icon:
                    onLeftIconClick(v);
                    break;
            }
        }
    };

    public void onRightTextClick(View v) {

    }

    public void onLeftTextClick(View v) {
    }

    public void onLeftIconClick(View view) {

    }

    public void onRightIconClick(View view) {

    }

    public void onLeftClick(View view) {
        finish();
    }


    abstract void initData();
    abstract void initView();
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity=this;
        initView();
        initData();
    }

    public void initTop(int left, String content, int right) {
        initTop(left, content, right, -1);
    }

    public void initTop(int left, String content, String right) {
        initTop(left, content, right,-1, R.color.bgwhite);
    }

    public void initTop(int left, String content, int right, int bgColor) {
        initTop(left, content, right, bgColor, -1);
    }

    public void initTop(int left, String content, Object right, int bgColor, int textColor) {

        Toolbar toolbar = findViewById(R.id.toolbar);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (bgColor != -1) {
                toolbar.setBackgroundColor(getResources().getColor(bgColor));
                StatusBarUtils.setWindowStatusBarColor(this, bgColor);
            } else {
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                StatusBarUtils.setWindowStatusBarColor(this, R.color.colorPrimary);
            }

            ImageView rightIcon = findViewById(R.id.right_icon);
            TextView right_text = findViewById(R.id.right_text);
            ImageView leftI = findViewById(R.id.left);
            ImageView leftIcon = findViewById(R.id.left_icon);
            TextView center = findViewById(R.id.center_text);

            if (right instanceof Integer) {
                int rightInt = (int) right;
                rightIcon.setVisibility(View.VISIBLE);
                right_text.setVisibility(View.GONE);
                if (rightInt > 0) {
                    rightIcon.setImageResource(rightInt);
                } else {
                    rightIcon.setVisibility(View.GONE);
                }
                rightIcon.setOnClickListener(ToolbarListener);
            } else if (right instanceof String) {
                String rightStr = (String) right;

                right_text.setVisibility(View.VISIBLE);
                rightIcon.setVisibility(View.GONE);

                if (TextUtils.isEmpty(rightStr)) {
                    right_text.setVisibility(View.GONE);
                } else {
                    right_text.setText(rightStr);
                }
                right_text.setTextColor(getResources().getColor(textColor));
                right_text.setOnClickListener(ToolbarListener);

            }
            if (left <= 0) {
                leftI.setVisibility(View.GONE);
            } else {
                leftI.setImageResource(left);
                leftI.setOnClickListener(ToolbarListener);
            }


            if (!TextUtils.isEmpty(content)) {

                center.setVisibility(View.VISIBLE);
                if (textColor != -1) {
                    center.setTextColor(getResources().getColor(textColor));
                } else {
                    center.setTextColor(getResources().getColor(R.color.bgwhite));
                }
                center.setText(content);
            }else {
                center.setVisibility(View.GONE);
            }


        }
    }
}
