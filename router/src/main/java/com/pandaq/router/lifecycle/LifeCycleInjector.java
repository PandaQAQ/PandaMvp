package com.pandaq.router.lifecycle;

import android.app.Application;

import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle;
import com.pandaq.appcore.framework.app.lifecycle.ILifecycleInjector;

import java.util.List;

import androidx.fragment.app.FragmentManager;

/**
 * Created by huxinyu on 2018/12/25.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : this module's app lifecycle injector
 */
public class LifeCycleInjector implements ILifecycleInjector {
    @Override
    public void injectAppLifeCycle(Application application, List<IAppLifeCycle> appLifeCycles) {
        appLifeCycles.add(new AppLifeCycle());
    }

    @Override
    public void injectActivityLifeCycle(Application application, List<Application.ActivityLifecycleCallbacks> activityLifecycleCallbacks) {
        // add it if you have do something in ActivityLifeCycle
    }

    @Override
    public void injectFragmentLifeCycle(Application application, List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifecycleCallbacks) {
        // add it if you have do something in FragmentLifeCycle
    }

    @Override
    public int priority() {
        return 0;
    }
}
