package com.vode.aibuy.adapter;


public interface OnItemClickListener<T> {
    void onItemClick(ViewHolder<T> holder, T item, int position);
}
