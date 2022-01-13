package com.pandaq.appcore.framework.app.lifecycleimpl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.pandaq.appcore.framework.app.ActivityTask;
import com.pandaq.appcore.log.PLogger;

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
        ActivityTask.getInstance().addActivity(activity);
        registerFragmentCallbacks(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        PLogger.d("LifeCycle Started",activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        PLogger.d("LifeCycle Resumed",activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        PLogger.d("LifeCycle Paused",activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        PLogger.d("LifeCycle Stopped",activity.getClass().getSimpleName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        PLogger.d("LifeCycle Destroyed",activity.getClass().getSimpleName());
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
