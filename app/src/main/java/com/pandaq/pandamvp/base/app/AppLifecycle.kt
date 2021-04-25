package com.pandaq.pandamvp.base.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.pandaq.appcore.BuildConfig
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

    }

    override fun onTerminate(application: Application) {

    }
}