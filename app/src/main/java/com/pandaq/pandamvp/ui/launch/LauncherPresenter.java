package com.pandaq.pandamvp.ui.launch;

import com.pandaq.appcore.framework.base.BasePresenter;
import com.pandaq.pandamvp.app.lifecycle.AppLifeCycle;
import com.pandaq.pandamvp.net.ApiService;

/**
 * Created by huxinyu on 2018/5/23.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class LauncherPresenter extends BasePresenter<LauncherContract.View> implements LauncherContract.Presenter {

    private ApiService mApiService = AppLifeCycle.sApiManager.getApiService(ApiService.class);

    public LauncherPresenter(LauncherContract.View mvpView) {
        super(mvpView);
    }

    public void showErrorMsg() {
        mMvpView.onError(-100, "msg????");
    }
}
