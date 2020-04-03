package com.pandaq.uires.widget.gallery;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by huxinyu on 2019/9/11.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class FixedScroller extends Scroller {

    private int mDuration = 1000;

    public FixedScroller(Context context) {
        super(context);
    }

    public FixedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public FixedScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received showLong, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received showLong, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

}
