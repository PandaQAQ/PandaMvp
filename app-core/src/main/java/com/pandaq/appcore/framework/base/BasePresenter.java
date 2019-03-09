package com.pandaq.appcore.framework.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :Presenter 实现类基类模板,可直接 module 中继承重写生命周期函数
 */
public abstract class BasePresenter<V> implements IBaseContract.IBasePresenter {

    protected V mView;
    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(V mvpView) {
        if (mvpView != null) {
            mView = mvpView;
            onMvpViewAttach();
        } else {
            throw new NullPointerException("mvpView here must not be null !!!");
        }
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) { //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void onMvpViewAttach() {
        // presenter 绑定到 MvpFragment 或 MvpActivity 时触发
    }

    @Override
    public void onMvpViewDetach() {
        dispose();
    }

}
