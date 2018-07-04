package com.pandaq.pandamvp.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.pandaq.appcore.framework.base.TemplateBaseActivity;
import com.pandaq.commonui.GuideCoverView;
import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.app.ActivityTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :所有 Activity 类的最基础类
 */

public abstract class BaseActivity extends TemplateBaseActivity {

    protected Unbinder mUnbinder;
    protected Toolbar mToolbar;
    private FrameLayout mParentView;
    /**
     * 遮罩引导载体图层
     */
    private GuideCoverView mGuideCoverView;
    /**
     * 标识 activity 是否是向导 Activity
     */
    protected boolean guideActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果是新手向导页则初始化向导载体图层
        if (guideActivity) {
            initGuide();
        }
        initToolBar();
        ActivityTask.getInstance().addActivity(this);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /**
     * 初始化向导图层
     */
    private void initGuide() {
        mParentView = (FrameLayout) getWindow().getDecorView();
        if (mGuideCoverView == null) {
            mGuideCoverView = new GuideCoverView(mParentView);
        }
    }

    /**
     * 显示 GuideCoverV
     */
    protected void showGuideView() {
        if (mGuideCoverView != null) {
            mParentView.addView(mGuideCoverView);
        }
    }

    @Override
    public void bindButterKnife() {
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        ActivityTask.getInstance().remove(this);
    }
}
