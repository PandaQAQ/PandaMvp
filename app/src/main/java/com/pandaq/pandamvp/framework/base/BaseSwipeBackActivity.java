package com.pandaq.pandamvp.framework.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.pandaq.pandacore.framework.niceui.SwipeBackLayout;
import com.pandaq.pandamvp.R;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description : 可侧滑返回的 Activity，需要侧滑返回的 Activity 继承此类,需配合 Theme 使用
 */

@SuppressLint("Registered")
public abstract class BaseSwipeBackActivity extends BaseActivity {

    private SwipeBackLayout layout;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.swipeback_base, null);
        layout.attachToActivity(this);
        super.onCreate(savedInstanceState);
    }

    // 提供给子类设置 ViewPager 的接口，用于 SwipeLayout 中处理滑动冲突
    public void addViewPager(ViewPager pager) {
        layout.addViewPager(pager);
    }
}
