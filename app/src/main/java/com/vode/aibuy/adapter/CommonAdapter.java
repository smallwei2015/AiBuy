package com.vode.aibuy.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vode.aibuy.R;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter {
    public static final int STATE_NORMOR = Integer.MAX_VALUE;
    public static final int STATE_ERROR = R.layout.error_view;
    public static final int STATE_EMPTY = R.layout.empty_view;
    public static final int STATE_LOADING = R.layout.loading_view;

    protected Context mContext;
    protected Resources mResources;
    int state = STATE_LOADING;//状态
    protected List<T> mDatas;
    List<View> mHeaders = new ArrayList<>();
    List<View> mFooters = new ArrayList<>();

    OnItemClickListener<T> onItemClickListener;

    public CommonAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        mResources = mContext.getResources();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (state) {
            case STATE_EMPTY://展示空数据
            case STATE_ERROR://展示网络错误
            case STATE_LOADING://加载中
                if (viewType == state) {
                    View view = LayoutInflater.from(mContext).inflate(state, parent, false);
                    return new ViewHolder<T>(view);
                } else
                    return new ViewHolder<T>(mHeaders.get(-viewType));
            default://展示普通情况
                if (viewType <= 0) {//是header或者footer
                    if (mHeaders.size() > 0 && -viewType < mHeaders.size())//是header
                        return new ViewHolder<T>(mHeaders.get(-viewType));
                    else//是footer
                        return new ViewHolder<T>(mFooters.get(-viewType - mHeaders.size() - mDatas.size()));
                } else
                    return new ViewHolder<T>(LayoutInflater.from(mContext).inflate(viewType, parent, false));
        }
    }

    protected void setState(int state) {
        this.state = state;
        notifyDataSetChanged();
    }

    public void showEmptyView() {
        setState(STATE_EMPTY);
    }

    public void showErrorView() {
        setState(STATE_ERROR);
    }

    public void showLoadingView() {
        setState(STATE_LOADING);
    }

    public void showItemView() {
        setState(STATE_NORMOR);
    }

    public int getState() {
        return state;
    }

    public int getHeaderSize() {
        return mHeaders.size();
    }

    public int getFooterSize() {
        return mFooters.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //各种页面计算应显示高度
        if (state == STATE_EMPTY || state == STATE_ERROR || state == STATE_LOADING) {
//            View typeView = holder.itemView.findViewById(R.id.iv_type);
//            if (typeView == null)
//                return;
//            int headerHeight = 0;
//            for (View view : mHeaders)
//                headerHeight += view.getHeight();
//            ViewGroup.LayoutParams layoutParams = typeView.getLayoutParams();
//
//            int viewHeight = UIUtils.getWindowHeight((Activity) mContext) - headerHeight - UIUtils.getStatusBarHeight(mContext) - UIUtils.dp2px(mContext, 150);
//            layoutParams.height = viewHeight;
//            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            typeView.setLayoutParams(layoutParams);
            return;
        }

        if (getItemViewType(position) > 0) {//如果是普通条目
            final int tPosition = position - mHeaders.size();
            //需要实际数据位置需要减去headers的数量
            convert((ViewHolder<T>) holder, mDatas.get(tPosition), tPosition);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick((ViewHolder<T>) holder, mDatas.get(tPosition), tPosition);
                }
            });
        }
    }

    public abstract void convert(ViewHolder<T> holder, T item, int positon);

    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public int getItemCount() {
        switch (state) {//错误页面或者空白页
            case STATE_EMPTY:
            case STATE_ERROR:
            case STATE_LOADING:
                return 1 + mHeaders.size();
            default://普通情况
                return mDatas.size() + mHeaders.size() + mFooters.size();
        }

    }

    public abstract int getDatasItemType(int position, T item);

    @Override
    public int getItemViewType(int position) {
        switch (state) {
            //加载错误或者空白
            case STATE_EMPTY:
            case STATE_ERROR:
            case STATE_LOADING:
                //先显示头再显示错误或者空白页
                if (position < mHeaders.size())
                    return -position;
                else
                    return state;
            default://普通情况
                if (position > mHeaders.size() - 1 && position < mHeaders.size() + mDatas.size()) {
                    int pos = position - mHeaders.size();
                    return getDatasItemType(pos, mDatas.get(pos));
                } else//是header或者footer
                    return -position;
        }
    }

    public void addHeader(View header) {
        mHeaders.add(header);
        notifyDataSetChanged();
    }

    public void addFooter(View footer) {
        mFooters.add(footer);
        notifyDataSetChanged();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected int getColor(int colorRes) {
        if (mResources != null)
            return mResources.getColor(colorRes);
        else
            return 0;
    }

    protected Drawable getDrawable(int drawableRes) {
        if (mResources != null)
            return mResources.getDrawable(drawableRes);
        else
            return null;
    }

    public List<View> getmFooters() {
        return mFooters;
    }

}
