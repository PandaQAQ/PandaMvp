package com.pandaq.pandamvp.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.pandaq.appcore.framework.base.TemplateBaseActivity;
import com.pandaq.appcore.framework.swipe.SwipeBackLayout;
import com.pandaq.commonui.guide.GuideCoverView;
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
    protected boolean swipeEnable;
    private SwipeBackLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (swipeEnable) {
            layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                    R.layout.swipeback_base, null);
            layout.attachToActivity(this);
        }
        // 如果是新手向导页则初始化向导载体图层
        if (guideActivity) {
            initGuide();
        }
        initToolBar();
        ActivityTask.getInstance().addActivity(this);
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

    // 提供给子类设置 ViewPager 的接口，用于 SwipeLayout 中处理滑动冲突
    public void addViewPager(ViewPager pager) {
        if (swipeEnable) {
            layout.addViewPager(pager);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityTask.getInstance().remove(this);
    }

    @Override
    protected int bindContentRes() {
        return 0;
    }
}
