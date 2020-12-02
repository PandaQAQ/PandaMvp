package com.pandaq.appcore.framework.app.lifecycleimpl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.pandaq.appcore.framework.annotation.IgnoreAdapt;
import com.pandaq.appcore.framework.annotation.LocalAdapt;
import com.pandaq.appcore.framework.app.ActivityTask;
import com.pandaq.appcore.utils.log.PLogger;
import com.pandaq.appcore.utils.system.DisplayUtils;

import java.util.List;

/**
 * Created by huxinyu on 2018/9/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class DefaultActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private List<FragmentManager.FragmentLifecycleCallbacks> mFragmentLifeCycles;

    DefaultActivityLifecycle(List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifeCycles) {
        mFragmentLifeCycles = fragmentLifeCycles;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // 未添加忽略注解的页面默认都进行适配
        IgnoreAdapt ignoreAdapt = activity.getClass().getAnnotation(IgnoreAdapt.class);
        LocalAdapt localAdapt = activity.getClass().getAnnotation(LocalAdapt.class);
        if (ignoreAdapt == null) {
            if (localAdapt != null) {
                DisplayUtils.adaptDensity(activity, localAdapt.designWidth());
            } else {
                DisplayUtils.adaptDensity(activity, 360f);
            }
        }
        ActivityTask.getInstance().addActivity(activity);
        registerFragmentCallbacks(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        PLogger.d(activity.getLocalClassName()+" is onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityTask.getInstance().remove(activity);
    }

    private void registerFragmentCallbacks(Activity activity) {
        //注册框架外部, 开发者扩展的 BaseFragment 生命周期逻辑
        for (FragmentManager.FragmentLifecycleCallbacks fragmentLifecycle : mFragmentLifeCycles) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentLifecycle, true);
            }
        }
    }
}
