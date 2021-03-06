package com.pandaq.app_launcher.net;

import com.pandaq.appcore.utils.log.PLogger;
import com.pandaq.rxpanda.exception.ApiException;
import com.pandaq.rxpanda.observer.ApiObserver;

import org.jetbrains.annotations.NotNull;

/**
 * Created by huxinyu on 2019/3/8.
 * Email : panda.h@foxmail.com
 * Description :自定义的处理类
 */
public abstract class AppCallBack<T> extends ApiObserver<T> {

    @Override
    protected void onSuccess(@NotNull T data) {
        success(data);
    }

    @Override
    protected void onError(ApiException e) {
        handleException(e);
        fail(e);
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

    protected abstract void success(@NotNull T data);

    protected abstract void fail(ApiException e);

    protected abstract void finish(boolean success);
}
