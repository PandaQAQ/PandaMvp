package com.pandaq.appcore.api

import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.rxpanda.observer.ApiObserver

/**
 * Created by huxinyu on 2021/9/10.
 * Email : panda.h@foxmail.com
 * Description :仅用于 Sds 特定数据结构，ApiData 内部还封装了一层 "entity" 对象
 */
abstract class SdsCallback<T> : ApiObserver<T>() {

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