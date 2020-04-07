package com.pandaq.uires.widget.recyclerview.managers;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * Created by huxinyu on 2019/7/24.
 * Email : panda.h@foxmail.com
 * Description :可禁止滚动的 layoutManager
 */

public class NoScrollScrollGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;
    public NoScrollScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
