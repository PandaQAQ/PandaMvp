package com.pandaq.appcore.framework.app;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle;
import com.pandaq.appcore.framework.app.lifecycleimpl.AppProxy;
import com.pandaq.appcore.utils.system.AppUtils;

/**
 * Created by huxinyu on 2018/9/7.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :基础 Application
 */
public class BaseApp extends MultiDexApplication {

    private IAppLifeCycle appProxy;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        boolean isSelfProcess = base.getPackageName().equals(AppUtils.getProcessName(base, android.os.Process.myPid()));
        if (isSelfProcess) {
            if (appProxy == null) {
                appProxy = new AppProxy(this);
            }
            appProxy.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        boolean isSelfProcess = getPackageName().equals(AppUtils.getProcessName(this, android.os.Process.myPid()));
        if (isSelfProcess) {
            appProxy.onCreate(this);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        boolean isSelfProcess = getPackageName().equals(AppUtils.getProcessName(this, android.os.Process.myPid()));
        if (isSelfProcess) {
            appProxy.onTerminate(this);
        }
    }
}
