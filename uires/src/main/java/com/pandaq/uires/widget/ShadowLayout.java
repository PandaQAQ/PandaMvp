package com.pandaq.uires.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.pandaq.uires.R;

/**
 * Created by huxinyu on 2019/4/23.
 * Email : panda.h@foxmail.com
 * Description :带阴影效果的 ConstraintLayout
 */
public class ShadowLayout extends FrameLayout {

    private Paint mPaint;
    private RectF mRectF;
    /**
     * 是否显示横向的阴影
     */
    private boolean showHorizontal = false;
    /**
     * 是否是向上投影
     */
    private boolean showTop = false;
    /**
     * 是否是向上投影
     */
    private boolean showBottom = false;
    /**
     * 阴影的颜色, 需要带透明
     */
    private int mShadowColor = Color.argb(29, 0, 0, 0);
    /**
     * 阴影的大小范围 radius越大越模糊，越小越清晰
     */
    private float mShadowRadius = 10;
    /**
     * 阴影的宽度，比如底部的阴影，那就是底部阴影的高度
     */
    private float mShadowWidth = 15;
    /**
     * 阴影 x 轴的偏移量, 计算padding时需要计算在内
     */
    private float mShadowDx = 0;
    /**
     * 阴影 y 轴的偏移量，计算padding时需要计算在内，比如想底部的阴影多一些，这个设置值就可以了
     */
    private float mShadowDy = 10;

    private ShadowLayout(Context context) {
        super(context);
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.getResources().obtainAttributes(attrs, R.styleable.ShadowLayout);
        mShadowColor = ta.getColor(R.styleable.ShadowLayout_shadowColor, mShadowColor);
        mShadowDx = ta.getDimension(R.styleable.ShadowLayout_shadowDx, mShadowDx);
        mShadowDy = ta.getDimension(R.styleable.ShadowLayout_shadowDy, mShadowDy);
        mShadowRadius = ta.getDimension(R.styleable.ShadowLayout_shadowRadius, mShadowRadius);
        mShadowWidth = ta.getDimension(R.styleable.ShadowLayout_shadowWidth, mShadowWidth);
        showHorizontal = ta.getBoolean(R.styleable.ShadowLayout_showHorizontal, false);
        showTop = ta.getBoolean(R.styleable.ShadowLayout_showTop, false);
        showBottom = ta.getBoolean(R.styleable.ShadowLayout_showBottom, true);
        ta.recycle();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        this.setWillNotDraw(false);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.TRANSPARENT);
        mRectF = new RectF();
        mPaint.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, mShadowColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRectF, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        resetShadowPadding();
    }

    /**
     * 为 ShadowLayout 设置 Padding 以为显示阴影留出空间
     */
    private void resetShadowPadding() {
        float rectLeft = 0;
        float rectTop = 0;
        float rectRight;
        float rectBottom = 0;
        int paddingLeft = 0;
        int paddingTop = 0;
        int paddingRight;
        int paddingBottom = 0;
        if (showHorizontal) {
            rectRight = this.getWidth() - mShadowWidth - mShadowDx;
            paddingRight = (int) mShadowWidth + (int) mShadowDx;
        } else {
            rectRight = this.getWidth();
            paddingRight = 0;
        }
        if (showTop) {
            rectTop = this.getHeight() - mShadowWidth - mShadowDy;
            paddingTop = (int) mShadowWidth + (int) mShadowDy;
        }
        if (showBottom) {
            rectBottom = this.getHeight() - mShadowWidth - mShadowDy;
            paddingBottom = (int) mShadowWidth + (int) mShadowDy;
        }

        mRectF.left = rectLeft;
        mRectF.top = rectTop;
        mRectF.right = rectRight;
        mRectF.bottom = rectBottom;
        this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
