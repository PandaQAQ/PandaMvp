package com.pandaq.pandamvp.modules.module1;

import com.pandaq.pandamvp.modules.basemvp.BaseContract;
import com.pandaq.pandamvp.modules.basemvp.BasemvpActivity;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class Moudle1Activity extends BasemvpActivity<Module1Presenter> implements BaseContract.IBaseView {
    @Override
    public Module1Presenter injectPresenter() {
        return new Module1Presenter();
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected int bindContentRes() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }
}
