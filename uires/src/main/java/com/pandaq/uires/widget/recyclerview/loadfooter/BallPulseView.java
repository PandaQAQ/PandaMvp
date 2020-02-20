package com.pandaq.uires.widget.recyclerview.loadfooter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;

import com.pandaq.uires.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 脉冲球动画
 * from https://github.com/lcodecorex/TwinklingRefreshLayout
 */
public class BallPulseView extends View {

    public static final int DEFAULT_SIZE = 40; //dp

    private Paint mPaint;

    private int normalColor = getResources().getColor(R.color.res_color_load_footer1);
    private int animatingColor = getResources().getColor(R.color.res_color_load_footer2);

    private float circleSpacing;
    private float[] scaleFloats = new float[]{1f, 1f, 1f};


    private boolean mIsStarted = false;
    private ArrayList<ValueAnimator> mAnimators;
    private Map<ValueAnimator, ValueAnimator.AnimatorUpdateListener> mUpdateListeners = new HashMap<>();

    //<editor-fold desc="View">
    public BallPulseView(Context context) {
        this(context, null);
    }

    public BallPulseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BallPulseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        circleSpacing = DensityUtil.dp2px(2);

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int default_size = DensityUtil.dp2px(DEFAULT_SIZE);
        setMeasuredDimension(resolveSize(default_size, widthMeasureSpec),
                resolveSize(default_size, heightMeasureSpec));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAnimators != null) for (int i = 0; i < mAnimators.size(); i++) {
            mAnimators.get(i).cancel();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius = (Math.min(getWidth(), getHeight()) - circleSpacing * 2) / 6;
        float x = (getWidth() >> 1) - (radius * 2 + circleSpacing);
        float y = getHeight() >> 1;
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX = x + (radius * 2) * i + circleSpacing * i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            canvas.drawCircle(0, 0, radius, mPaint);
            canvas.restore();
        }
    }
    //</editor-fold>

    //<editor-fold desc="private">
    private boolean isStarted() {
        return mIsStarted;
    }

    private void createAnimators() {
        mAnimators = new ArrayList<>();
        int[] delays = new int[]{120, 240, 360};
        for (int i = 0; i < 3; i++) {
            final int index = i;

            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.3f, 1);

            scaleAnim.setDuration(750);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);

            mUpdateListeners.put(scaleAnim, animation -> {
                scaleFloats[index] = (float) animation.getAnimatedValue();
                postInvalidate();
            });
            mAnimators.add(scaleAnim);
        }
    }
    //</editor-fold>

    //<editor-fold desc="API">
    public void setIndicatorColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    public void setNormalColor(@ColorInt int color) {
        normalColor = color;
        if (!isStarted()) {
            setIndicatorColor(normalColor);
        }
    }

    public void setAnimatingColor(@ColorInt int color) {
        animatingColor = color;
        if (isStarted()) {
            setIndicatorColor(animatingColor);
        }
    }

    public void startAnim() {
        if (mAnimators == null) createAnimators();
        if (mAnimators == null) return;
        if (isStarted()) return;

        for (int i = 0; i < mAnimators.size(); i++) {
            ValueAnimator animator = mAnimators.get(i);

            //when the animator restart , add the updateListener again because they was removed by animator stop .
            ValueAnimator.AnimatorUpdateListener updateListener = mUpdateListeners.get(animator);
            if (updateListener != null) {
                animator.addUpdateListener(updateListener);
            }
            animator.start();
        }
        mIsStarted = true;
        setIndicatorColor(animatingColor);
    }

    public void stopAnim() {
        if (mAnimators != null && mIsStarted) {
            mIsStarted = false;
            for (ValueAnimator animator : mAnimators) {
                if (animator != null /*&& animator.isStarted()*/) {
                    animator.removeAllUpdateListeners();
                    animator.end();
                }
            }
            scaleFloats = new float[]{1f, 1f, 1f};
        }
        setIndicatorColor(normalColor);
    }
    //</editor-fold>

}
