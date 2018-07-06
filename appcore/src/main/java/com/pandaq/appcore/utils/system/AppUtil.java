package com.pandaq.appcore.utils.system;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtil {
    /**
     * 获取客户端版本号
     *
     * @param context 上下文
     * @return int    版本号
     */
    public static int getVersionCode(Context context) {
        int versionCode;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            versionCode = 999;
        }
        return versionCode;
    }

    /**
     * 获取客户端版名称
     *
     * @param context 上下文
     * @return int    版本号
     */
    public static String getVersionName(Context context) {
        String versionCode;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            versionCode = "9.9.9";
        }
        return versionCode;
    }

    /**
     * 获取包名最后一个路径
     *
     * @param context 上下文
     * @return int    包名最后一个路径
     */
    public static String getPackageLast(Context context) {
        String packageLastPath;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            String[] pack = packageInfo.packageName.split(".");
            packageLastPath = pack[pack.length - 1];
        } catch (Exception e) {
            e.printStackTrace();
            packageLastPath = "goengine";
        }
        return packageLastPath;
    }
}
