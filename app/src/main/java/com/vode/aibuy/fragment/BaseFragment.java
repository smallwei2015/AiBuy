package com.vode.aibuy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by cj on 2017/3/6.
 */

public abstract class BaseFragment extends Fragment {

    public Bundle bundle;
    public FragmentActivity activity;

    abstract void initView(View view);
    abstract void initData();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = savedInstanceState;
        activity = getActivity();

        initView(view);
        initData();
    }
}
