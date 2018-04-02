package com.pandaq.pandamvp.modules.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.caches.DiskCache;
import com.pandaq.pandamvp.swipeback.SwipeBackActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :所有 Activity 类的最基础类
 */

public abstract class BaseActivity extends SwipeBackActivity {

    protected Unbinder mUnbinder;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        if (bindContentRes() != 0) {
            setContentView(bindContentRes());
        }
        mUnbinder = ButterKnife.bind(this);
        initToolBar();
        initViews();
        loadData();
    }

    /**
     * 初始化ToolBar
     */
    public void initToolBar() {
        mToolbar = findViewById(R.id.titleBar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在页面失去焦点时同步缓存
        DiskCache.getDiskCache().flush();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    protected abstract void initVariable();

    protected abstract int bindContentRes();

    protected abstract void initViews();

    protected abstract void loadData();
}
