package com.pandaq.pandamvp.framework;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.pandaq.appcore.framework.base.BaseActivity;
import com.pandaq.appcore.framework.base.BasePresenter;
import com.pandaq.commonui.guide.GuideCoverView;
import com.pandaq.commonui.msgwindow.Toaster;
import com.pandaq.pandamvp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :所有 Activity 类的最基础类
 */

public abstract class AppBaseActivity<P extends BasePresenter> extends BaseActivity<P> {

    protected @Nullable Toolbar mToolbar;
    private FrameLayout mParentView;
    /**
     * 遮罩引导载体图层
     */
    private GuideCoverView mGuideCoverView;
    /**
     * 标识 activity 是否是向导 Activity
     */
    protected boolean isGuide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果是新手向导页则初始化向导载体图层
        if (isGuide) {
            initGuide();
        }
        initToolBar();
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


    public void showLoading(String msg) {
        Toaster.with(this)
                .msg("showLoading")
                .show();
    }

    public void hideLoading() {
        Toaster.with(this)
                .msg("hideLoading")
                .show();
    }

    public void onError(int errCode, String errMsg) {
        Toaster.with(this)
                .msg(errMsg)
                .show();
    }

    public void onLoadFinish() {
        Toaster.with(this)
                .msg("finishLoading")
                .show();
    }

}
