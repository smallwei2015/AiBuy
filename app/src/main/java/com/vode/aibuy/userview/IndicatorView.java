package com.vode.aibuy.userview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cj on 2018/3/13.
 */

public class IndicatorView extends View implements ViewPager.OnPageChangeListener {
    private List<Indicator> mIndicators;
    private int mCount;
    private float mRadius;
    private float mStrokeWidth;
    private float mSpace;
    private int mSelectPosition = 0;
    private Paint mCirclePaint;
    private int mSelectColor = Color.GRAY;
    private int mDotNormalColor = Color.WHITE;
    private int mFillMode;
    private ViewPager mViewPager;
    public int viewheight;
    public int viewWidth;

    public IndicatorView(Context context) {
        super(context);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    private void init() {
        mIndicators = new ArrayList<>();
        mCount = 4;
        mRadius = 8;
        mStrokeWidth = 1;
        mSpace = 10;


        viewheight = (int) (mRadius * 2);
        viewWidth = (int) (mCount * 2 * (mRadius + mStrokeWidth) + (mCount - 1) * mSpace);

        mCirclePaint = new Paint();
        mCirclePaint.setStrokeWidth(mStrokeWidth);
        mCirclePaint.setAntiAlias(true);

        measureIndicator();
    }


    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size //我们将大小取最大值,你也可以取其他值

                //mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它

                mySize = size;
                break;
            }
        }
        return mySize;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        init();

        int width = getMySize(viewWidth, widthMeasureSpec);
        int height = getMySize(viewheight, heightMeasureSpec);

        setMeasuredDimension(width, height);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mIndicators.size(); i++) {
            Indicator indicator = mIndicators.get(i);
            float x = indicator.cx;
            float y = indicator.cy;
            if (mSelectPosition == i) {
                mCirclePaint.setStyle(Paint.Style.FILL);
                mCirclePaint.setColor(mSelectColor);
            } else {
                mCirclePaint.setColor(mDotNormalColor);
                if (mFillMode != 0) {
                    mCirclePaint.setStyle(Paint.Style.STROKE);
                } else {
                    mCirclePaint.setStyle(Paint.Style.FILL);
                }
            }
            canvas.drawCircle(x, y, mRadius, mCirclePaint); // 绘制小圆点中的内容
            /*if (mFillMode != 0) {
                String text = "";

                Rect bound = new Rect();
                mTextPaint.getTextBounds(text, 0, text.length(), bound);
                int textWidth = bound.width();
                int textHeight = bound.height();
                float textStartX = x - textWidth / 2;
                float textStartY = y + textHeight / 2;
                canvas.drawText(text, textStartX, textStartY, mTextPaint);
            }*/
        }
    }


    /**
     * 与ViewPager 关联 * @param viewPager
     */
    public void setUpWithViewPager(ViewPager viewPager) {
        //releaseViewPager();
        if (viewPager == null) {
            return;
        }
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
        int count = mViewPager.getAdapter().getCount();
        setCount(count);

    }

    private void setCount(int count) {
        mCount = count;
        invalidate();
    }


    /**
     * 测量每个圆点的位置
     */
    private void measureIndicator() {
        mIndicators.clear();
        float cx = 0;
        for (int i = 0; i < mCount; i++) {
            Indicator indicator = new Indicator();
            if (i == 0) {
                cx = mRadius + mStrokeWidth;
            } else {
                cx += (mRadius + mStrokeWidth) * 2 + mSpace;
            }
            indicator.cx = cx;
            indicator.cy = viewheight / 2;
            mIndicators.add(indicator);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mSelectPosition = position;
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class Indicator {
        private float cx;
        private float cy;

        public float getCy() {
            return cy;
        }

        public void setCy(float cy) {
            this.cy = cy;
        }

        public float getCx() {

            return cx;
        }

        public void setCx(float cx) {
            this.cx = cx;
        }
    }
}
