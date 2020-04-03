package com.pandaq.uires.widget.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.pandaq.uires.R;


/**
 * Created by huxinyu on 2018/5/23.
 * Email : panda.h@foxmail.com
 * <p>
 * a image view , it's width/height = ratio
 */

public class RatioImageView extends RoundImageView {

    private float ratio = 1f;

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        ratio = ta.getFloat(R.styleable.RatioImageView_ratio, 1f);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = View.MeasureSpec.makeMeasureSpec((int) (View.MeasureSpec.getSize(widthMeasureSpec) / ratio), View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, height);
    }
}
