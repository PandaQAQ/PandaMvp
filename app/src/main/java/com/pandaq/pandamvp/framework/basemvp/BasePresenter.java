package com.pandaq.pandamvp.framework.basemvp;

import com.pandaq.appcore.framework.mvpbase.IBaseMvpView;
import com.pandaq.appcore.framework.mvpbase.IBasePresenter;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :Presenter 实现类基类模板,可直接 module 中继承使用模板也可完全自己自定义
 */
public abstract class BasePresenter<V> implements IBasePresenter {

    protected V mMvpView;

    public BasePresenter(V mvpView) {
        if (mvpView != null) {
            mMvpView = mvpView;
            mvpViewBand();
        } else {
            throw new NullPointerException("mvpView here must not be null !!!");
        }
    }

    /**
     * mvpView 绑定时触发
     */
    private void mvpViewBand() {

    }

    /**
     * mvpView 解除绑定时触发
     */
    private void mvpViewUnBand() {

    }

    /**
     * 添加监听
     */
    public void addSubscriber() {

    }

    /**
     * 解除监听
     */
    public void unSubscribe() {

    }

}
