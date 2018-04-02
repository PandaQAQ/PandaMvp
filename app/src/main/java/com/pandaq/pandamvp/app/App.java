package com.pandaq.pandamvp.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by huxinyu on 2018/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class App extends Application {

    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        new Handler().post(() -> {
            while (true) {
                try {
                    Looper.getMainLooper().loop();
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
        });
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