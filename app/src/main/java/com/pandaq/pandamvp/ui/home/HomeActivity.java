package com.pandaq.pandamvp.ui.home;

import android.content.Intent;
import android.os.Bundle;

import com.pandaq.appcore.eventbus.EventUtils;
import com.pandaq.appcore.utils.logutils.PLogger;
import com.pandaq.commonui.msgwindow.ToastIconGravity;
import com.pandaq.commonui.msgwindow.Toaster;
import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.events.HomeEvent;
import com.pandaq.pandamvp.framework.basemvp.BaseMvpActivity;
import com.pandaq.pandamvp.ui.launch.LauncherActivity;

import org.greenrobot.eventbus.Subscribe;

import androidx.annotation.Nullable;
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
        Intent intent = new Intent();
        intent.setClass(this, LauncherActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void handleEvent(HomeEvent event) {
        Toaster.with(this)
                .icon(R.drawable.ic_launcher_background, ToastIconGravity.END)
                .msg("Home")
                .show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PLogger.d("onCreate--->Home");
    }

    @Override
    protected void onStart() {
        super.onStart();
        PLogger.d("onStart-->Home");
    }

    @Override
    protected void onResume() {
        super.onResume();
        PLogger.d("onResume--->Home");
    }

    @Override
    protected void onPause() {
        super.onPause();
        PLogger.d("onPause--->Home");
    }

    @Override
    protected void onStop() {
        super.onStop();
        PLogger.d("onStop--->Home");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PLogger.d("onDestroy-->Home");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        PLogger.d("onReStart-->Home");
    }
}

