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
import com.vode.aibuy.adapter.ItemDecoration;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.Comment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends BaseFragment {


    public RecyclerView rec;
    public ArrayList<Comment> comments;
    public CommonAdapter<Comment> adapter;

    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    void initView(View view) {
        rec = ((RecyclerView) view.findViewById(R.id.comment_rec));
        rec.setLayoutManager(new LinearLayoutManager(activity));
        rec.addItemDecoration(new ItemDecoration(10));
        comments = new ArrayList<>();


        adapter = new CommonAdapter<Comment>(activity, comments) {
            @Override
            public void convert(ViewHolder holder, Comment item, int positon) {

            }

            @Override
            public int getDatasItemType(int position, Comment item) {
                return R.layout.layout_comment_item;
            }
        };
        rec.setAdapter(adapter);
    }

    @Override
    void initData() {
        for (int i = 0; i < 10; i++) {
            comments.add(new Comment());
        }

        adapter.showItemView();
        adapter.notifyDataSetChanged();
    }
}
