package com.pandaq.appcore.framework.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle;
import com.pandaq.appcore.framework.app.lifecycleimpl.AppProxy;

/**
 * Created by huxinyu on 2018/9/7.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :基础 Application
 */
public class BaseApp extends Application {

    private static BaseApp globalApp;
    private IAppLifeCycle appProxy;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (appProxy == null) {
            appProxy = new AppProxy(this);
        }
        appProxy.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        globalApp = this;
        appProxy.onCreate(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        appProxy.onTerminate(this);
    }

    public static BaseApp getGlobalApp() {
        return globalApp;
    }

    /**
     * 获取应用的版本号
     *
     * @return 应用版本号
     */
    public int getAppVersion() {
        try {
            PackageInfo info = globalApp.getPackageManager().getPackageInfo(globalApp.getApplicationContext().getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
