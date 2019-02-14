package com.pandaq.pandamvp.ui.home;

import com.pandaq.appcore.framework.base.BasePresenter;
import com.pandaq.appcore.http.PandaNet;
import com.pandaq.pandamvp.app.lifecycle.AppLifeCycle;
import com.pandaq.pandamvp.net.ApiService;

/**
 * By Template Create
 * <p>
 * Description :
 */

// AppBaseMvpActivity 目录不确定，生成模板手动导包

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private ApiService mApiService = AppLifeCycle.sPandaNet.getApiService(ApiService.class);

    public HomePresenter(HomeContract.View mvpView) {
        super(mvpView);
    }

}

