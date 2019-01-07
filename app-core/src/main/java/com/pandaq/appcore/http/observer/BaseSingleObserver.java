package com.pandaq.appcore.http.observer;

import com.pandaq.appcore.framework.base.IBaseContract;
import com.pandaq.appcore.http.ExceptionMsg;
import com.pandaq.appcore.http.HttpCodes;
import com.pandaq.appcore.http.converter.ApiException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by huxinyu on 2018/5/31.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :SingleObserver 回调类
 */
public abstract class BaseSingleObserver<T> implements SingleObserver<T> {

    private IBaseContract.IBasePresenter mBasePresenter;

    public BaseSingleObserver(IBaseContract.IBasePresenter basePresenter) {
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
    protected abstract void onFail(long errCode, String errMsg);

    /**
     * 结束回调，无论失败还是成功都会走此方法
     */
    protected abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                int code = httpException.code();
                onFail(code, ExceptionMsg.HTTP_ERROR);
            } else if (e instanceof ApiException) {
                ApiException exception = (ApiException) e;
                //处理token失效对应的逻辑
                onFail(exception.getErrorCode(), exception.getMessage());
            } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
                // 主机地址不能解析或超时
                onFail(HttpCodes.HTTP.TIME_OUT, ExceptionMsg.TIMEOUT_ERROR);
            } else {
                onFail(HttpCodes.HTTP.UNKNOWN, ExceptionMsg.UNKNOWN_ERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            e.printStackTrace();
            onFinish();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mBasePresenter != null) {
            mBasePresenter.addDisposable(d);
        }
    }

    @Override
    public void onSuccess(T t) {
        success(t);
        onFinish();
    }

}
