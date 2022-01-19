package com.pandaq.uires.widget.banner.indicator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class RoundLinesIndicator extends BaseIndicator {

    public RoundLinesIndicator(Context context) {
        this(context, null);
    }

    public RoundLinesIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundLinesIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int space = config.getIndicatorSpace();
        int count = config.getIndicatorSize();
        if (count <= 1) return;
        setMeasuredDimension((int) (config.getSelectedWidth() * count) + space * (count - 1), config.getHeight());
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = config.getIndicatorSize();
        if (count <= 1) return;
        for (int i = 0; i < count; i++) {
            mPaint.setColor(config.getNormalColor());
            int start = i * config.getSelectedWidth() + i * config.getIndicatorSpace();
            RectF oval = new RectF(start, 0, start + config.getSelectedWidth(), config.getHeight());
            canvas.drawRoundRect(oval, config.getRadius(), config.getRadius(), mPaint);
            // 绘制间距
            if (i < count - 1) {
                int spaceStart = start + config.getIndicatorSpace();
                mPaint.setColor(Color.TRANSPARENT);
                RectF space = new RectF(spaceStart, 0, spaceStart + config.getIndicatorSpace(), config.getHeight());
                canvas.drawRect(space, mPaint);
            }
        }
        mPaint.setColor(config.getSelectedColor());
        int left = config.getCurrentPosition() * (config.getSelectedWidth()+config.getIndicatorSpace());
        RectF rectF = new RectF(left, 0, left + config.getSelectedWidth(), config.getHeight());
        canvas.drawRoundRect(rectF, config.getRadius(), config.getRadius(), mPaint);
    }
}
