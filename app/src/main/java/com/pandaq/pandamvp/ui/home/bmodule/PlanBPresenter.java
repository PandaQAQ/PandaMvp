package com.pandaq.pandamvp.ui.home.bmodule;

import com.pandaq.appcore.framework.app.BaseApp;
import com.pandaq.appcore.framework.mvpbase.BasePresenter;
import com.pandaq.pandamvp.app.lifecycle.AppLifeCycle;
import com.pandaq.pandamvp.net.ApiService;


/**
 * By Template Create
 * <p>
 * Description :
 */

// BaseMvpActivity 目录不确定，生成模板手动导包

public class PlanBPresenter extends BasePresenter<PlanBContract.View> implements PlanBContract.Presenter {

    private ApiService mApiService = AppLifeCycle.sApiManager.getApiService(ApiService.class);

    public PlanBPresenter(PlanBContract.View mvpView) {
        super(mvpView);
    }

}
