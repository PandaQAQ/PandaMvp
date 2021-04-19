package com.pandaq.appcore.framework.app.lifecycleimpl

import com.pandaq.appcore.utils.log.PLogger
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by huxinyu on 4/19/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class TestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        PLogger.d("TestInterceptor","拦截器执行！")
        val request = chain.request()
        return chain.proceed(request)
    }
}