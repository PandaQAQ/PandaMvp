package com.pandaq.pandamvp.ui.launch;

import com.pandaq.pandamvp.framework.basemvp.BasePresenter;

/**
 * Created by huxinyu on 2018/5/23.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class LauncherPresenter extends BasePresenter<LauncherContract.View> implements LauncherContract.Presenter {

    public LauncherPresenter(LauncherContract.View mvpView) {
        super(mvpView);
    }

    public void doRequest() {
//        getApiService().
    }
}
