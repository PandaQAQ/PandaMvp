package com.pandaq.appcore.framework.app.lifecycle;

import android.app.Application;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by huxinyu on 2018/12/25.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :module lifecycle inject methods
 */
public interface ILifecycleInjector {

    /**
     * inject module application calls to global application'lifecycle。
     * after all module life cycle added to appLifeCycles，they will be register to global application
     *
     * @param application   global application
     * @param appLifeCycles global application's lifecycle list。
     */
    void injectAppLifeCycle(Application application, List<IAppLifeCycle> appLifeCycles);

    /**
     * after all module life cycle added to activityLifecycle，they will be register to global application
     *
     * @param application                global application
     * @param activityLifecycleCallbacks global application's activityLifecycle list。
     */
    void injectActivityLifeCycle(Application application, List<Application.ActivityLifecycleCallbacks> activityLifecycleCallbacks);

    /**
     * after all module life cycle added to fragmentLifecycleCallbacks，they will be register to global application
     *
     * @param application                global application
     * @param fragmentLifecycleCallbacks global application's fragmentLifecycle list。
     */
    void injectFragmentLifeCycle(Application application, List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifecycleCallbacks);

    /**
     * priority for lifeCycle methods inject
     *
     * @return priority 0 for first
     */
    int priority();
}
