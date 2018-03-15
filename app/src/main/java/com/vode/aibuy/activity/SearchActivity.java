package com.vode.aibuy.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.ItemDecoration2;
import com.vode.aibuy.adapter.SearchGoodsAdapter;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.present.SearchActivityPresent;
import com.vode.aibuy.utils.UIUtils;
import com.vode.aibuy.view.BaseView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<BaseView<List<Goods>>, SearchActivityPresent> implements BaseView<List<Goods>> {

    public EditText search;
    public RecyclerView rec;
    public ArrayList<Goods> datas;
    public SearchGoodsAdapter adapter;
    public ImageView change;

    @Override
    void initData() {
        getPresenter().loadData(true);
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_search);

        search = findViewById(R.id.search_edit);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        UIUtils.showToast("searching...");
                        loadSuccess(null);
                        break;
                }
                return false;
            }
        });

        rec = findViewById(R.id.rec_search);
        rec.setLayoutManager(new LinearLayoutManager(mActivity));
        rec.addItemDecoration(new ItemDecoration2());
        datas = new ArrayList<>();
        adapter = new SearchGoodsAdapter(mActivity, datas);
        rec.setAdapter(adapter);

        change = findViewById(R.id.search_change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getLayoutType() == 0) {
                    adapter.setLayoutType(1);
                    rec.setLayoutManager(new GridLayoutManager(mActivity, 2));
                    rec.setAdapter(adapter);
                } else {
                    adapter.setLayoutType(0);
                    rec.setLayoutManager(new LinearLayoutManager(mActivity));

                    rec.setAdapter(adapter);
                }
            }
        });
    }


    @NonNull
    @Override
    public SearchActivityPresent createPresenter() {
        return new SearchActivityPresent();
    }


    @Override
    public void loadSuccess(List<Goods> datas) {
        adapter.showItemView();

        for (int i = 0; i < 11; i++) {
            Goods e = new Goods();

            this.datas.add(e);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void loadFaild(Throwable e) {
        adapter.showErrorView();
    }
}
