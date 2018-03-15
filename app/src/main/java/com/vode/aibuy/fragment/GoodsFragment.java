package com.vode.aibuy.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.vode.aibuy.R;
import com.vode.aibuy.activity.SearchActivity;
import com.vode.aibuy.adapter.GoodsAdapter;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.present.GoodsLcePresent;
import com.vode.aibuy.view.GoodsLceView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsFragment extends MvpLceFragment<SwipeRefreshLayout,List<Goods>,GoodsLceView,GoodsLcePresent> implements GoodsLceView,SwipeRefreshLayout.OnRefreshListener {


    public RecyclerView rec;
    public ArrayList<Goods> datas;
    public GoodsAdapter adapter;
    public EditText search_edit;

    public GoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public GoodsLcePresent createPresenter() {
        return new GoodsLcePresent();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contentView.setOnRefreshListener(this);

        search_edit = view.findViewById(R.id.search_edit);
        search_edit.setFocusable(false);
        search_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions
                            .makeSceneTransitionAnimation(getActivity(), v, getString(R.string.search_name));
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }

            }
        });
        rec = view.findViewById(R.id.rec_goods);
        datas = new ArrayList<>();
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new GoodsAdapter(getActivity(), datas);
        rec.setAdapter(adapter);
    }

    @Override
    public void setData(List<Goods> data) {
        datas.clear();
        if (data != null) {
            datas.addAll(data);
            adapter.notifyDataSetChanged();
        }else {

            showContent();
            adapter.showItemView();
            Goods e = new Goods();
            e.setType(0);
            datas.add(e);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }


    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);

        adapter.showEmptyView();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}
