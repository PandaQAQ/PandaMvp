package com.pandaq.appcore.http.observer;


import com.pandaq.appcore.framework.base.IBaseContract;
import com.pandaq.appcore.http.ExceptionMsg;
import com.pandaq.appcore.http.HttpCodes;
import com.pandaq.appcore.http.converter.ApiException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by huxinyu on 2018/5/16.
 * Email : panda.h@foxmail.com
 * Description :统一的 Observer 监听回调处理
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private IBaseContract.IBasePresenter mBasePresenter;

    public BaseObserver(IBaseContract.IBasePresenter basePresenter) {
        mBasePresenter = basePresenter;
    }

    /**
     * 成功回调
     * <p>
     *
     * @param t 数据内容
     */
    protected abstract void success(T t);

    /**
     * 失败回调
     */
    protected abstract void fail(long errCode, String errMsg);

    /**
     * 结束回调，无论失败还是成功都会走此方法
     */
    protected abstract void finish();

    @Override
    public void onSubscribe(Disposable d) {
        if (mBasePresenter != null) {
            mBasePresenter.addDisposable(d);
        }
    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                int code = httpException.code();
                fail(code, ExceptionMsg.HTTP_ERROR);
            } else if (e instanceof ApiException) {
                ApiException exception = (ApiException) e;
                //处理token失效对应的逻辑
                fail(exception.getErrorCode(), exception.getMessage());
            } else if (e instanceof UnknownHostException
                    || e instanceof SocketTimeoutException) {
                // 主机地址不能解析或超时
                fail(HttpCodes.HTTP.TIME_OUT, ExceptionMsg.TIMEOUT_ERROR);
            } else {
                fail(HttpCodes.HTTP.UNKNOWN, ExceptionMsg.UNKNOWN_ERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            e.printStackTrace();
            finish();
        }
    }

    @Override
    public void onComplete() {
        finish();
    }
}
