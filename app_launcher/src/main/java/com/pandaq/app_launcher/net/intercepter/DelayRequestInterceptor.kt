package com.pandaq.app_launcher.net.intercepter

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by huxinyu on 2019/6/13.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
class DelayRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //请求头好了之后等待 2000 毫秒再真实请求
        Thread.sleep(2000)
        return chain.proceed(chain.request())
    }
}