package com.pandaq.appcore.utils.system;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtil {

    private AppUtil(){
        // private constructor
    }
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
}
