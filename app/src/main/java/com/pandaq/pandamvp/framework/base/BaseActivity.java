package com.pandaq.pandamvp.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.pandaq.appcore.framework.base.TemplateBaseActivity;
import com.pandaq.commonui.guide.GuideCoverView;
import com.pandaq.pandamvp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :所有 Activity 类的最基础类
 */

public abstract class BaseActivity extends TemplateBaseActivity {

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
    /**
     * if you do not want swipeBack ,make swipeEnable = false this class for App
     * <p>
     * or in Activity Override initVariable() make swipeEnable = false after super
     */
    protected boolean swipeEnable = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果是新手向导页则初始化向导载体图层
        if (guideActivity) {
            initGuide();
        }
        initToolBar();
    }

    @Override
    protected void initVariable() {
        swipeEnable = true;
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

}
