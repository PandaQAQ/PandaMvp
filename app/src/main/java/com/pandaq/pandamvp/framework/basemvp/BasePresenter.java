package com.pandaq.pandamvp.framework.basemvp;

import com.pandaq.appcore.framework.mvpbase.TemplatePresenter;
import com.pandaq.pandamvp.app.App;
import com.pandaq.pandamvp.net.ApiService;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :继承模板 Presenter 可重写实现自己的逻辑
 */
public abstract class BasePresenter<V> extends TemplatePresenter<V> {

    public BasePresenter(V mvpView) {
        super(mvpView);
    }

    // 子类 Presenter1 中通过此方法获取请求服务

    /**
     * 获取网路请求接口服务
     *
     * @return ApiService 对象
     */
    protected ApiService getApiService() {
        return App.sApiManager.getApiService(ApiService.class);
    }

    @Override
    public void onMvpViewAttach() {
        super.onMvpViewAttach();
    }

    @Override
    public void onMvpViewDetach() {
        super.onMvpViewDetach();
    }
}
