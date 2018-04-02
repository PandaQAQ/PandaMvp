package com.pandaq.pandamvp.modules.basemvp;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :Presenter 实现类基类
 */
public abstract class BasePresenterImp<V extends BaseContract.IBaseView> implements BaseContract.IBasePresenter {

    protected V mMvpView;

    public BasePresenterImp(V mvpView) {
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
