package com.pandaq.app_jetpack.app.net

import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.rxpanda.observer.ApiObserver

/**
 * Created by huxinyu on 2019/3/8.
 * Email : panda.h@foxmail.com
 * Description :自定义的处理类
 */
abstract class AppCallBack<T> : ApiObserver<T>() {
    override fun finished(success: Boolean) {
        finish(success)
    }

    override fun onError(exception: ApiException?) {
        fail(exception)
    }

    override fun onSuccess(data: T) {
        success(data)
    }

    abstract fun success(data: T)

    abstract fun fail(exception: ApiException?)

    abstract fun finish(success: Boolean)
}