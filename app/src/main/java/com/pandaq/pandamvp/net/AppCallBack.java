package com.pandaq.pandamvp.net;

import com.pandaq.appcore.network.exception.ApiException;
import com.pandaq.appcore.network.observer.ApiObserver;
import com.pandaq.appcore.utils.log.PLogger;

/**
 * Created by huxinyu on 2019/3/8.
 * Email : panda.h@foxmail.com
 * Description :自定义的处理类
 */
public abstract class AppCallBack<T> extends ApiObserver<T> {
    @Override
    protected void onSuccess(T data) {
        success(data);
    }

    @Override
    protected void onError(ApiException e) {
        handleException(e);
        fail(e.getCode(), e.getMessage());
    }

    @Override
    protected void finished(boolean success) {
        finish(success);
    }

    private void handleException(ApiException e) {
        if (e.getCode() == ExceptionCode.TOKEN_INVALID) {
            PLogger.e("TOKEN 已过期");
        }
    }

    protected abstract void success(T data);

    protected abstract void fail(Long code, String msg);

    protected abstract void finish(boolean success);
}
