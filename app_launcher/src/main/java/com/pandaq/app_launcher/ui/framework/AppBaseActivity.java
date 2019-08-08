package com.pandaq.app_launcher.ui.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.pandaq.app_launcher.R;
import com.pandaq.appcore.framework.mvp.BaseActivity;
import com.pandaq.appcore.framework.mvp.BasePresenter;
import com.pandaq.uires.guide.GuideCoverView;
import com.pandaq.uires.msgwindow.Toaster;
import com.pandaq.uires.progress.LoadingDialog;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :所有 BaseActivity 类的最基础类
 */

public abstract class AppBaseActivity<P extends BasePresenter> extends BaseActivity<P> {

    protected @Nullable
    Toolbar mToolbar;
    private FrameLayout mParentView;
    /**
     * 遮罩引导载体图层
     */
    private GuideCoverView mGuideCoverView;
    /**
     * 标识 activity 是否是向导 BaseActivity
     */
    protected boolean isGuide;

    private LoadingDialog mLoadingDialog;

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
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show(false);
    }

    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
        mLoadingDialog = null;
    }

    public void onError(long errCode, String errMsg) {
        Toaster.msg(errMsg)
                .show();
    }

    public void onLoadFinish() {
        // 加载完成肯定会调用隐藏 loading
        hideLoading();
    }


}
