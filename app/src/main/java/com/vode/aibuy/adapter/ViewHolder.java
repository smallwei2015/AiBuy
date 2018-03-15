package com.vode.aibuy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vode.aibuy.R;

public class ViewHolder<T> extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;//存储控件的集合

    public ViewHolder(View view) {
        super(view);
        mViews = new SparseArray<>();
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId 组件的id
     * @return view组件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置网络图片地址
     */
    public ViewHolder<T> setImageUrl(int viewId, String url, Context context) {
        ImageView iv = getView(viewId);
        Glide.with(context).load(url).centerCrop().placeholder(R.color.bgcolor).into(iv);
        return this;
    }

    public String getTextString(int viewId) {
        TextView tv = getView(viewId);
        return tv.getText().toString().trim();
    }

    /**
     * 设置TextView的值
     *
     * @param viewId TextView的id
     * @param text   需要设置的文本
     * @return 返回viewholder
     */
    public ViewHolder<T> setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder<T> setText(int viewId, Spannable span) {
        TextView tv = getView(viewId);
        tv.setText(span, TextView.BufferType.SPANNABLE);
        return this;
    }

    public ViewHolder<T> addView(int viewId, View view) {
        ViewGroup tv = getView(viewId);
        tv.addView(view);
        return this;
    }

    public ViewHolder<T> removeAllViews(int viewId) {
        ViewGroup tv = getView(viewId);
        tv.removeAllViews();
        return this;
    }

    public ViewHolder<T> setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        if (view != null)
            view.setImageResource(resId);
        return this;
    }

    public ViewHolder<T> setClickable(int viewId, boolean clickable) {
        View view = getView(viewId);
        if (view != null)
            view.setClickable(clickable);
        return this;
    }

    public ViewHolder<T> setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        if (view != null)
            view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder<T> setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public ViewHolder<T> setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder<T> setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder<T> setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    @SuppressLint("NewApi")
    public ViewHolder<T> setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public ViewHolder<T> setVisible(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }


    public ViewHolder<T> linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolder<T> setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public ViewHolder<T> setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder<T> setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder<T> setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolder<T> setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolder<T> setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ViewHolder<T> setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public ViewHolder<T> setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public ViewHolder<T> setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public ViewHolder<T> setSelected(int viewId, boolean selected) {
        RadioButton view = getView(viewId);
        view.setChecked(selected);
        return this;
    }

    /**
     * 关于事件的
     */
    public ViewHolder<T> setOnClickListener(int viewId,
                                            View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder<T> setOnTouchListener(int viewId,
                                            View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public ViewHolder<T> setOnLongClickListener(int viewId,
                                                View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public ViewHolder<T> setCompoundDrawables(int viewId, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        TextView view = getView(viewId);
        if (left != null)
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
        if (top != null)
            top.setBounds(0, 0, top.getMinimumWidth(), top.getMinimumHeight());
        if (right != null)
            right.setBounds(0, 0, right.getMinimumWidth(), right.getMinimumHeight());
        if (bottom != null)
            bottom.setBounds(0, 0, bottom.getMinimumWidth(), bottom.getMinimumHeight());
        view.setCompoundDrawables(left, top, right, bottom);
        return this;
    }
}