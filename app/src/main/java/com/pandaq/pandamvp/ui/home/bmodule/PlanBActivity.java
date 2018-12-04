package com.pandaq.pandamvp.ui.home.bmodule;


import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.framework.basemvp.BaseMvpActivity;


/**
 * By Template Create
 * <p>
 * Description :
 */

// BaseMvpActivity 目录不确定，生成模板手动导包

public class PlanBActivity extends BaseMvpActivity<PlanBPresenter> implements PlanBContract.View {

    @Override
    public PlanBPresenter injectPresenter() {
        return new PlanBPresenter(this);
    }

    @Override
    protected void initVariable() {
        super.initVariable();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int bindContentRes() {
        return R.layout.app_activity_plan_b;
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
}

