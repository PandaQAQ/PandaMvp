package com.pandaq.appcore.utils.system;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.pandaq.appcore.framework.app.ActivityTask;

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

    public static Context getContext() {
        return instance.appContext.getApplicationContext();
    }

    public static Resources getResource() {
        return instance.appContext.getResources();
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

    /**
     * 获取 deviceId
     *
     * @return deviceId
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceId() {
        String deviceId = "";
        TelephonyManager tm = (TelephonyManager) instance.appContext.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm) {
            if (ActivityCompat.checkSelfPermission(ActivityTask.getInstance().currentActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ActivityTask.getInstance().currentActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            } else {
                if (tm.getDeviceId() != null) {
                    deviceId = tm.getDeviceId();
                } else {
                    deviceId = Settings.Secure.getString(instance.appContext.getApplicationContext().getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                }
            }
            Log.d("deviceId--->", deviceId);
        }
        return deviceId;
    }
}
