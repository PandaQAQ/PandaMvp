package com.pandaq.pandamvp.ui.launch;

import com.google.gson.Gson;
import com.pandaq.appcore.framework.base.BasePresenter;
import com.pandaq.appcore.http.Panda;
import com.pandaq.appcore.http.transformer.RxScheduler;
import com.pandaq.pandamvp.entites.UserInfo;
import com.pandaq.pandamvp.net.ApiService;
import com.pandaq.pandamvp.net.AppCallBack;

/**
 * Created by huxinyu on 2018/5/23.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class LauncherPresenter extends BasePresenter<LauncherContract.View> implements LauncherContract.Presenter {

    private ApiService api = Panda.retrofit().create(ApiService.class);

    public LauncherPresenter(LauncherContract.View mvpView) {
        super(mvpView);
    }

    public void showErrorMsg() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("15195867128");
        userInfo.setToken("token:1sajdaskjdkasdkjadsajkdkad1");
        userInfo.setUserName("PandaQ");
        api.test()
                .doOnSubscribe(this::addDisposable)
                .compose(RxScheduler.observableSync())
                .subscribe(new AppCallBack<String>() {
                    @Override
                    protected void success(String data) {

                    }

                    @Override
                    protected void fail(Long code, String msg) {
                        mView.onError(code, msg);
                    }

                    @Override
                    protected void finish(boolean success) {

                    }
                });
    }
}
