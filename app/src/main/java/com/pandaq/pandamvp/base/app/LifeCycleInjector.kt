package com.pandaq.pandamvp.base.app

import android.app.Application
import androidx.fragment.app.FragmentManager
import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle
import com.pandaq.appcore.framework.app.lifecycle.ILifecycleInjector

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
class LifeCycleInjector : ILifecycleInjector {
    override fun injectAppLifeCycle(application: Application?, appLifeCycles: MutableList<IAppLifeCycle>?) {
        appLifeCycles?.add(AppLifecycle())
    }

    override fun injectActivityLifeCycle(
            application: Application?,
            activityLifecycleCallbacks: MutableList<Application.ActivityLifecycleCallbacks>?
    ) {

    }

    override fun injectFragmentLifeCycle(
            application: Application?,
            fragmentLifecycleCallbacks: MutableList<FragmentManager.FragmentLifecycleCallbacks>?
    ) {

    }

    override fun priority(): Int = 0
}