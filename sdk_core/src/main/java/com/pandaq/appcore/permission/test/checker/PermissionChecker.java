
package com.pandaq.appcore.permission.test.checker;

import android.content.Context;

import java.util.List;

import android.support.annotation.NonNull;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :permission checker
 */
public interface PermissionChecker {

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    boolean hasPermission(@NonNull Context context, @NonNull String... permissions);
    
    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions);

}