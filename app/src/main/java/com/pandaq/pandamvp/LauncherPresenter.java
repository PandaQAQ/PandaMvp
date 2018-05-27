package com.pandaq.pandamvp;

import com.pandaq.pandamvp.framework.basemvp.BasePresenter;

import io.reactivex.disposables.Disposable;

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

    @Override
    public void addDisposable(Disposable disposable) {

    }

    @Override
    public void dispose() {

    }
}
