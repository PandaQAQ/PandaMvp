package com.pandaq.appcore.utils.system;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.pandaq.appcore.framework.app.ActivityTask;

import java.util.List;

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

    private AppUtils(Application context) {
        preferences = AppUtils.getContext().getSharedPreferences(
                AppUtils.getContext().getPackageName(),
                Context.MODE_PRIVATE
        );
        this.appContext = context;
    }

    public static SharedPreferences preferences;
    private static AppUtils instance;
    private Application appContext;

    public static void init(Application appContext) {
        synchronized (AppUtils.class) {
            if (instance == null) {
                synchronized (AppUtils.class) {
                    instance = new AppUtils(appContext);
                }
            }
        }
    }

    /**
     * release this
     */
    public static void release() {
        instance.appContext = null;
        instance = null;
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

    /**
     * 根据包名判断某个应用是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 检查结果
     */
    public static boolean hasInstall(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }
}
