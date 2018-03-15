package com.vode.aibuy.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

/**
 * Created by cj on 2018/3/12.
 */

public abstract class AutoFreshFragment extends BaseFragment {

    private LocalBroadcastManager broadcastManager;
    public static final String AUTO_REFRESH= "action_fragment_refresh";
    private BroadcastReceiver receiver;

    abstract void fresh(Intent intent);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        broadcastManager=LocalBroadcastManager.getInstance(activity);
        IntentFilter filter = new IntentFilter();
        filter.addAction(AUTO_REFRESH);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(AUTO_REFRESH)) {
                    fresh(intent);
                }
            }
        };
        broadcastManager.registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (broadcastManager != null) {
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }

    }
}
