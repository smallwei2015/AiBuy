package com.vode.aibuy.userview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vode.aibuy.R;

/**
 * Created by cj on 2018/3/14.
 */

@SuppressLint("AppCompatCustomView")
public class NotificationView extends TextView {
    private int width;
    private int height;

    public NotificationView(Context context) {
        super(context);
    }

    public NotificationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NotificationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NotificationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

                mySize = size;
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



        width = getMySize(40, widthMeasureSpec);
        height = getMySize(40, heightMeasureSpec);

        if (width>height){
            height=width;
        }
        setMeasuredDimension(height,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.red));
        paint.setStyle(Paint.Style.FILL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawOval(0,0,width,height, paint);
        }

        float textSize = getTextSize();


        float textsizePX = getResources().getDisplayMetrics().scaledDensity * textSize + 0.5f;
        TextPaint textPaint = getPaint();
        textPaint.setColor(getResources().getColor(R.color.bgwhite));
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        canvas.drawText(getText().toString(),width/2,height/2+(fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom, textPaint);


        //super.onDraw(canvas);


    }
}
