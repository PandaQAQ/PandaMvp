package com.pandaq.pandamvp.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.pandaq.pandamvp.modules.base.BaseActivity;

import java.util.Stack;

/**
 * Created by huxinyu on 2018/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class App extends Application {

    private static App sApp;
    public static Stack<BaseActivity> sActivityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        sActivityStack = new Stack<>();
    }

    public static App getGlobalApp() {
        return sApp;
    }

    /**
     * 获取应用的版本号
     *
     * @return 应用版本号
     */
    public int getAppVersion() {
        try {
            PackageInfo info = sApp.getPackageManager().getPackageInfo(sApp.getApplicationContext().getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
