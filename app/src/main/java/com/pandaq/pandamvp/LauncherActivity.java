package com.pandaq.pandamvp;

import com.pandaq.pandamvp.framework.basemvp.BaseMvpActivity;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description : 启动页面 Activity
 */
public class LauncherActivity extends BaseMvpActivity<LauncherPresenter> implements LauncherContract.View {

    @Override
    public LauncherPresenter injectPresenter() {
        return new LauncherPresenter(this);
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int bindContentRes() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(int errCode, String errMsg) {

    }

    @Override
    public void onLoadFinish() {

    }
}
