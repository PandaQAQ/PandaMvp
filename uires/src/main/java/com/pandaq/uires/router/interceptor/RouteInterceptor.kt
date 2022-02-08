package com.pandaq.uires.router.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.pandaq.appcore.log.PLogger

/**
 * Created by huxinyu on 4/23/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Interceptor(priority = 1, name = "基础拦截器")
class RouteInterceptor : IInterceptor {

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        callback?.onContinue(postcard)
    }

    override fun init(context: Context?) {
        PLogger.d("RouteInterceptor init finished")
    }
}