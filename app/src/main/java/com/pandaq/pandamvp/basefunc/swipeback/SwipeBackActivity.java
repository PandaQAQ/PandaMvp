package com.pandaq.pandamvp.basefunc.swipeback;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.pandaq.pandamvp.R;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description : 可侧滑返回的 Activity
 */

@SuppressLint("Registered")
public class SwipeBackActivity extends AppCompatActivity{

    public SwipeBackLayout layout;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.swipeback_base, null);
        layout.attachToActivity(this);
    }

    // 提供给子类设置 ViewPager 的接口，用于 SwipeLayout 中处理滑动冲突
    public void addViewPager(ViewPager pager) {
        layout.addViewPager(pager);
    }
}
