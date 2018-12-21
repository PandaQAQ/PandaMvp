
package com.pandaq.appcore.permission.source;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :context source
 */
public abstract class Source {

    private static final String NAME = "checkOpNoThrow";
    private static final int OP_REQUEST_INSTALL_PACKAGES = 66;

    private PackageManager mPackageManager;

    private AppOpsManager mAppOpsManager;

    public abstract Context getContext();

    public abstract void startActivity(Intent intent);

    private PackageManager getPackageManager() {
        if (mPackageManager == null) {
            mPackageManager = getContext().getPackageManager();
        }
        return mPackageManager;
    }

    private AppOpsManager getAppOpsManager() {
        if (mAppOpsManager == null) {
            mAppOpsManager = (AppOpsManager) getContext().getSystemService(Context.APP_OPS_SERVICE);
        }
        return mAppOpsManager;
    }

    /**
     * Show permissions rationale?
     */
    public final boolean isShowRationalePermission(String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;

        PackageManager packageManager = getContext().getPackageManager();
        Class<?> pkManagerClass = packageManager.getClass();
        try {
            Method method = pkManagerClass.getMethod("shouldShowRequestPermissionRationale", String.class);
            if (!method.isAccessible()) method.setAccessible(true);
            return (boolean) method.invoke(packageManager, permission);
        } catch (Exception ignored) {
            return false;
        }
    }

    public final boolean canRequestPackageInstalls() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Class<AppOpsManager> clazz = AppOpsManager.class;
            try {
                Method method = clazz.getDeclaredMethod(NAME, int.class, int.class, String.class);
                int result = (int) method.invoke(getAppOpsManager(), OP_REQUEST_INSTALL_PACKAGES, android.os.Process.myUid(), getContext().getPackageName());
                return result == AppOpsManager.MODE_ALLOWED;
            } catch (Exception ignored) {
                // Android P does not allow reflections.
                return true;
            }
        }
        return true;
    }

}
