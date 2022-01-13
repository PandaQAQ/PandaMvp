package com.pandaq.app_launcher.framework

import com.pandaq.app_launcher.net.ApiService
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.framework.mvp.IView
import com.pandaq.rxpanda.RxPanda

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
open class AppBasePresenter<V : IView> : BasePresenter<V>() {

    protected var api: ApiService = RxPanda.retrofit().create(ApiService::class.java)

    override fun refreshApiConfig() {
        api = RxPanda.retrofit().create(ApiService::class.java)
    }
}