package com.vode.aibuy.adapter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vode.aibuy.R;

/**
 * Created by cj on 2018/3/14.
 */

public class ItemDecoration2 extends RecyclerView.ItemDecoration {

    private int dividerHeight = 5;
    private Paint dividerPaint;

    public ItemDecoration2() {
    }

    public ItemDecoration2(int dividerHeight) {
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


        outRect.bottom = dividerHeight;
        int pos = parent.getChildAdapterPosition(view);
        if (pos % 2 == 0) {
            outRect.right = dividerHeight;
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        dividerPaint = new Paint();
        dividerPaint.setColor(parent.getContext().getResources().getColor(R.color.bglight));

        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }


    }
}
