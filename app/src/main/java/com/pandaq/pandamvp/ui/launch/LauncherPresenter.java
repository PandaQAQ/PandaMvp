package com.pandaq.pandamvp.ui.launch;

import com.pandaq.appcore.framework.mvpbase.BasePresenter;
import com.pandaq.pandamvp.app.App;
import com.pandaq.pandamvp.net.ApiService;

/**
 * Created by huxinyu on 2018/5/23.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class LauncherPresenter extends BasePresenter<LauncherContract.View> implements LauncherContract.Presenter {

    private ApiService mApiService = App.sApiManager.getApiService(ApiService.class);

    public LauncherPresenter(LauncherContract.View mvpView) {
        super(mvpView);
    }

}
