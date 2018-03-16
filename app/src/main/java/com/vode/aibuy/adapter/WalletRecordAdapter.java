package com.vode.aibuy.adapter;

import android.content.Context;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.WalletRecord;

import java.util.List;

/**
 * Created by cj on 2018/3/16.
 */

public class WalletRecordAdapter extends CommonAdapter<WalletRecord> {
    public WalletRecordAdapter(Context context, List<WalletRecord> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder<WalletRecord> holder, WalletRecord item, int positon) {

    }

    @Override
    public int getDatasItemType(int position, WalletRecord item) {
        return R.layout.layout_wallet_record_item;
    }
}
