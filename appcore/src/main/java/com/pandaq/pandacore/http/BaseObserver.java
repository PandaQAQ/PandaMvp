package com.pandaq.pandacore.http;

import com.pandaq.pandacore.framework.mvpbase.IBasePresenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :RxJava 统一封装处理的观察者回调,取消 RxJava onNext onError onFinish 的直接调用
 * 统一处理后，无论成功失败最终都会走到 onFinish
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private IBasePresenter mBasePresenter;

    private BaseObserver() {

    }

    public BaseObserver(IBasePresenter basePresenter) {
        mBasePresenter = basePresenter;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mBasePresenter.addDisposable(d);
    }

    /**
     * 网络请求成功
     *
     * @param t 成功返回的数据对象
     */
    protected abstract void onSuccess(T t);

    /**
     * 网络请求失败
     * 包括网络请求本身的 http 请求错误和约定的错误类型
     *
     * @param e 失败异常对象
     */
    protected abstract void onFail(Throwable e);

    /**
     * 结束一场事件请求
     */
    protected abstract void onFinish();

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        // 处理不同类型的 Exception
        onFail(e);
        // 最终会走事件结束
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
