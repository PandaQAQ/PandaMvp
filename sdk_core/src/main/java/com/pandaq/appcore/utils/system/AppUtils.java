package com.pandaq.appcore.utils.system;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.pandaq.appcore.framework.app.ActivityTask;
import com.pandaq.appcore.utils.sharepreference.PreferenceUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import androidx.core.app.ActivityCompat;

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
        preferenceUtil = new PreferenceUtil(context);
        this.appContext = context;
    }

    public static PreferenceUtil preferenceUtil;
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
    public static long versionCode() {
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

    public static String getMacAddress() {
        String macAddress = null;
        String str = "";
        try {
            Process process = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader isr = new InputStreamReader(process.getInputStream());
            LineNumberReader lnr = new LineNumberReader(isr);
            while (str != null) {
                str = lnr.readLine();
                if (str != null) {
                    macAddress = str.trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (macAddress + "").toLowerCase();
    }

    /**
     * 根据包名判断某个应用是否安装
     *
     * @param packageName 包名
     * @return 检查结果
     */
    public static boolean hasInstall(String packageName) {
        final PackageManager packageManager = getContext().getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    public static String getProcessName(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
