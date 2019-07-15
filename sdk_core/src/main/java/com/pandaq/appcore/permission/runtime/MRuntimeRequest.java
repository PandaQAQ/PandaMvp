
package com.pandaq.appcore.permission.runtime;

import android.os.Build;

import com.pandaq.appcore.permission.Action;
import com.pandaq.appcore.permission.Executor;
import com.pandaq.appcore.permission.PermissionActivity;
import com.pandaq.appcore.permission.Rationale;
import com.pandaq.appcore.permission.RtPermission;
import com.pandaq.appcore.permission.source.Source;
import com.pandaq.appcore.permission.test.checker.DoubleChecker;
import com.pandaq.appcore.permission.test.checker.PermissionChecker;
import com.pandaq.appcore.permission.test.checker.SystemChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import static java.util.Arrays.asList;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :request for Android6.0+
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class MRuntimeRequest extends BaseRuntimeRequest implements RuntimeRequest, Executor, PermissionActivity.PermissionListener {

    private static final PermissionChecker CHECKER = new SystemChecker();
    private static final PermissionChecker DOUBLE_CHECKER = new DoubleChecker();

    private Source mSource;

    private String[] mPermissions;
    private Rationale<List<String>> mRationaleListener;
    private Action<List<String>> mGranted;
    private Action<List<String>> mDenied;
    private Action<List<String>> mAlwaysDenied;

    private String[] mDeniedPermissions;

    public MRuntimeRequest(Source source) {
        this.mSource = source;
    }

    @NonNull
    @Override
    public final RuntimeRequest permission(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    @NonNull
    @Override
    public final RuntimeRequest permission(String[]... groups) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : groups) {
            permissionList.addAll(Arrays.asList(group));
        }
        this.mPermissions = permissionList.toArray(new String[]{});
        return this;
    }


    @NonNull
    @Override
    public final RuntimeRequest rationale(Rationale<List<String>> listener) {
        this.mRationaleListener = listener;
        return this;
    }

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

    @NonNull
    @Override
    public RuntimeRequest onAlwaysDenied(Action<List<String>> denied) {
        this.mAlwaysDenied = denied;
        return this;
    }

    @Override
    public final void start() {
        List<String> deniedList = getDeniedPermissions(CHECKER, mSource, mPermissions);
        mDeniedPermissions = deniedList.toArray(new String[0]);
        if (mDeniedPermissions.length > 0) { // 如果被拒绝权限不为空
            List<String> rationaleList = getRationalePermissions(mSource, mDeniedPermissions);
            // 如果全选拒绝且未勾选不再询问则回调 rationale
            if (rationaleList.size() > 0 && mRationaleListener != null) {
                mRationaleListener.showRationale(mSource.getContext(), rationaleList, this);
            } else { // 如果权限拒绝并且勾选了不再询问则直接重新请求权限
                execute();
            }
        } else {
            callbackSucceed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public final void execute() {
        PermissionActivity.requestPermission(mSource.getContext(), mDeniedPermissions, this);
    }

    @Override
    public final void onRequestPermissionsResult(@NonNull String[] permissions) {
        List<String> deniedList = getDeniedPermissions(DOUBLE_CHECKER, mSource, permissions);
        if (deniedList.isEmpty()) {
            callbackSucceed();
        } else {
            callbackFailed(deniedList);
        }
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
    private static List<String> getDeniedPermissions(PermissionChecker checker, @NonNull Source source, @NonNull String... permissions) {
        List<String> deniedList = new ArrayList<>(1);
        for (String permission : permissions) {
            if (!checker.hasPermission(source.getContext(), permission)) {
                deniedList.add(permission);
            }
        }
        return deniedList;
    }

    /**
     * Get permissions to show rationale.
     */
    private static List<String> getRationalePermissions(@NonNull Source source, @NonNull String... permissions) {
        List<String> rationaleList = new ArrayList<>(1);
        for (String permission : permissions) {
            // has requested before and user refused permission
            if (source.isShowRationalePermission(permission)) {
                rationaleList.add(permission);
            }
        }
        return rationaleList;
    }
}