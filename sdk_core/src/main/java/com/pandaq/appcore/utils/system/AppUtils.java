package com.pandaq.appcore.utils.system;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by huxinyu on 2019/1/7.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :Context 工具类，在任意地方获取 ApplicationContext
 */
public class AppUtils {

    private AppUtils() {
        // private constructor
    }

    public static AppUtils instance;
    private Application appContext;

    public static void init(Application appContext) {
        synchronized (AppUtils.class) {
            if (instance == null) {
                synchronized (AppUtils.class) {
                    instance = new AppUtils();
                }
            }
        }
        instance.setApplicationContext(appContext);
    }

    /**
     * release this
     */
    public static void release() {
        instance.appContext = null;
        instance = null;
    }

    private void setApplicationContext(Application applicationContext) {
        this.appContext = applicationContext;
    }

    public static Context applicationContext() {
        return instance.appContext.getApplicationContext();
    }

    /**
     * 获取应用的版本号
     *
     * @return 应用版本号
     */
    public static int versionCode() {
        try {
            PackageInfo info = instance.appContext.getPackageManager().getPackageInfo(instance.appContext.getApplicationContext().getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取应用版本名称
     *
     * @return 版本名称
     */
    public static String versionName() {
        try {
            PackageInfo info = instance.appContext.getPackageManager().getPackageInfo(instance.appContext.getApplicationContext().getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0.0";
    }
}
