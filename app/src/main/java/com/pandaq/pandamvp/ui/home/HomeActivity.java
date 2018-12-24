package com.pandaq.pandamvp.ui.home;

import android.Manifest;
import android.content.Context;

import com.pandaq.appcore.eventbus.EventUtils;
import com.pandaq.appcore.permission.Action;
import com.pandaq.appcore.permission.Executor;
import com.pandaq.appcore.permission.Rationale;
import com.pandaq.appcore.permission.RtPermission;
import com.pandaq.commonui.msgwindow.ToastIconGravity;
import com.pandaq.commonui.msgwindow.Toaster;
import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.events.HomeEvent;
import com.pandaq.pandamvp.events.LaunchEvent;
import com.pandaq.pandamvp.framework.basemvp.BaseMvpActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import butterknife.OnClick;

/**
 * By Template Create
 * <p>
 * Description :
 */

// BaseMvpActivity 目录不确定，生成模板手动导包

public class HomeActivity extends BaseMvpActivity<HomePresenter> implements HomeContract.View {

    @Override
    public HomePresenter injectPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        EventUtils.getDefault().register(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int bindContentRes() {
        return R.layout.app_activity_home;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    @Override
    public void onError(int errCode, String errMsg) {
        super.onError(errCode, errMsg);
    }

    @Override
    public void onLoadFinish() {
        super.onLoadFinish();
    }

    @OnClick(R.id.tv_test)
    public void onViewClicked() {
        EventBus.getDefault().post(new HomeEvent());
        EventBus.getDefault().post(new LaunchEvent());
    }

    @Subscribe
    public void handleEvent(HomeEvent event) {
        Toaster.with(this)
                .icon(R.drawable.ic_launcher_background, ToastIconGravity.END)
                .msg("Home")
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventUtils.getDefault().unregister(this);
    }
}

