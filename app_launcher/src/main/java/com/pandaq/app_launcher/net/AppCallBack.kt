package com.pandaq.app_launcher.net

import com.pandaq.rxpanda.observer.ApiObserver
import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.appcore.log.PLogger

/**
 * Created by huxinyu on 2019/3/8.
 * Email : panda.h@foxmail.com
 * Description :自定义的处理类
 */
abstract class AppCallBack<T> : ApiObserver<T>() {
    override fun onSuccess(data: T) {
        success(data)
    }

    override fun onError(e: ApiException) {
        handleException(e)
        fail(e)
    }

    override fun finished(success: Boolean) {
        finish(success)
    }

    private fun handleException(e: ApiException) {
        if (e.code === ExceptionCode.TOKEN_INVALID) {
            PLogger.e("TOKEN 已过期")
        }
    }

    protected abstract fun success(data: T)
    protected abstract fun fail(e: ApiException?)
    protected abstract fun finish(success: Boolean)
}