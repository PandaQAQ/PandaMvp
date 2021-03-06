
package com.pandaq.appcore.permission.runtime;

import androidx.annotation.NonNull;

import com.pandaq.appcore.permission.Action;
import com.pandaq.appcore.permission.Rationale;
import com.pandaq.appcore.permission.RtPermission;
import com.pandaq.appcore.permission.source.Source;
import com.pandaq.appcore.permission.test.checker.PermissionChecker;
import com.pandaq.appcore.permission.test.checker.RealChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import static java.util.Arrays.asList;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :request for Android5.0+
 */

public class LRuntimeRequest extends BaseRuntimeRequest implements RuntimeRequest {

    private static final PermissionChecker CHECKER = new RealChecker();

    private Source mSource;

    private String[] mPermissions;
    private Action<List<String>> mGranted;
    private Action<List<String>> mDenied;
    private Action<List<String>> mAlwaysDenied;

    public LRuntimeRequest(Source source) {
        this.mSource = source;
    }

    public RuntimeRequest permission(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    @NonNull
    public RuntimeRequest permission(String[]... groups) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : groups) {
            permissionList.addAll(Arrays.asList(group));
        }
        this.mPermissions = permissionList.toArray(new String[0]);
        return this;
    }

    @NonNull
    @Override
    public final RuntimeRequest rationale(Rationale listener) {
        return this;
    }

    @NotNull
    @NonNull
    @Override
    public final RuntimeRequest onGranted(Action<List<String>> granted) {
        this.mGranted = granted;
        return this;
    }

    @NonNull
    @Override
    public final RuntimeRequest onDenied(Action<List<String>> denied) {
        this.mDenied = denied;
        return this;
    }

    @NotNull
    @NonNull
    @Override
    public RuntimeRequest onAlwaysDenied(Action<List<String>> denied) {
        this.mAlwaysDenied = denied;
        return this;
    }

    @Override
    public final void start() {
        List<String> deniedList = getDeniedPermissions(mSource, mPermissions);
        if (deniedList.isEmpty())
            callbackSucceed();
        else
            callbackFailed(deniedList);
    }

    /**
     * Callback acceptance status.
     */
    private void callbackSucceed() {
        if (mGranted != null) {
            List<String> permissionList = asList(mPermissions);
            try {
                mGranted.onAction(permissionList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Callback rejected state.
     */
    private void callbackFailed(@NonNull List<String> deniedList) {
        if (RtPermission.hasAlwaysDeniedPermission(mSource.getContext(), deniedList)) { // 权限被拒绝并且不会再询问
            // 包含拒绝后不再提醒项
            if (mAlwaysDenied != null) {
                mAlwaysDenied.onAction(RtPermission.getAlwaysDeniedPermission(mSource,
                        deniedList));
            }
        } else { // 不包含拒绝后不再提醒项
            if (mDenied != null) {
                mDenied.onAction(deniedList);
            }
        }
    }

    /**
     * Get denied permissions.
     */
    private static List<String> getDeniedPermissions(@NonNull Source source, @NonNull String... permissions) {
        List<String> deniedList = new ArrayList<>(1);
        for (String permission : permissions) {
            if (!CHECKER.hasPermission(source.getContext(), permission)) {
                deniedList.add(permission);
            }
        }
        return deniedList;
    }
}