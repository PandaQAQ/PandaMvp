package com.pandaq.pandamvp.base.app

import android.app.Application
import android.content.Context
import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
class AppLifecycle : IAppLifeCycle {
    override fun attachBaseContext(base: Context) {

    }

    override fun onCreate(application: Application) {
//        EmptyConfig.DEFAULT_EMPTY_ICON = R.drawable.icon_empty
//        PicLoader.PLACEHOLDER = R.color.holder_gray
//        RxPanda.globalConfig()
//            .baseUrl("https://hgyqdj.safely365.com/mobile/app/")
//            .apiSuccessCode(0L)
//            .retryCount(3)
//            .apiDataClazz(ApiData::class.java)
//            .netInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .debug(BuildConfig.DEBUG)
//        if (UserUtils.getUser() != null) {
//            RxPanda.globalConfig().addGlobalHeader("token", UserUtils.getUser()?.token)
//        }

    }

    override fun onTerminate(application: Application) {

    }
}