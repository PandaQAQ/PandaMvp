package com.pandaq.pandamvp.ui.home;

import com.pandaq.appcore.framework.mvpbase.BasePresenter;
import com.pandaq.pandamvp.app.App;
import com.pandaq.pandamvp.net.ApiService;

/**
 * By Template Create
 * <p>
 * Description :
 */

// BaseMvpActivity 目录不确定，生成模板手动导包

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private ApiService mApiService = App.sApiManager.getApiService(ApiService.class);

    public HomePresenter(HomeContract.View mvpView) {
        super(mvpView);
    }

}

