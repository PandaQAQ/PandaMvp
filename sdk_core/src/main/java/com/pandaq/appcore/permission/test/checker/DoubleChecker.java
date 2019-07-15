
package com.pandaq.appcore.permission.test.checker;

import android.content.Context;

import java.util.List;

import android.support.annotation.NonNull;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :checker for permissions it will use both system check and real try
 */
public final class DoubleChecker implements PermissionChecker {

    private static final PermissionChecker STANDARD_CHECKER = new SystemChecker();
    private static final PermissionChecker STRICT_CHECKER = new RealChecker();

    @Override
    public boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        return STANDARD_CHECKER.hasPermission(context, permissions)
                && STRICT_CHECKER.hasPermission(context, permissions);
    }

    @Override
    public boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        return STANDARD_CHECKER.hasPermission(context, permissions)
                && STRICT_CHECKER.hasPermission(context, permissions);
    }
}