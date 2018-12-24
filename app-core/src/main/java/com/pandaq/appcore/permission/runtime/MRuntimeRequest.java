
package com.pandaq.appcore.permission.runtime;

import android.os.Build;

import com.pandaq.appcore.permission.Action;
import com.pandaq.appcore.permission.Executor;
import com.pandaq.appcore.permission.PermissionActivity;
import com.pandaq.appcore.permission.Rationale;
import com.pandaq.appcore.permission.source.Source;
import com.pandaq.appcore.permission.test.checker.DoubleChecker;
import com.pandaq.appcore.permission.test.checker.PermissionChecker;
import com.pandaq.appcore.permission.test.checker.SystemChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

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

    private String[] mDeniedPermissions;

    public MRuntimeRequest(Source source) {
        this.mSource = source;
    }

    @NonNull
    @Override
    final public RuntimeRequest permission(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    @NonNull
    @Override
    final public RuntimeRequest permission(String[]... groups) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : groups) {
            permissionList.addAll(Arrays.asList(group));
        }
        this.mPermissions = permissionList.toArray(new String[]{});
        return this;
    }


    @NonNull
    @Override
    final public RuntimeRequest rationale(Rationale<List<String>> listener) {
        this.mRationaleListener = listener;
        return this;
    }

    @NonNull
    @Override
    final public RuntimeRequest onGranted(Action<List<String>> granted) {
        this.mGranted = granted;
        return this;
    }

    @NonNull
    @Override
    final public RuntimeRequest onDenied(Action<List<String>> denied) {
        this.mDenied = denied;
        return this;
    }

    @Override
    final public void start() {
        List<String> deniedList = getDeniedPermissions(CHECKER, mSource, mPermissions);
        mDeniedPermissions = deniedList.toArray(new String[0]);
        if (mDeniedPermissions.length > 0) {
            List<String> rationaleList = getRationalePermissions(mSource, mDeniedPermissions);
            if (rationaleList.size() > 0 && mRationaleListener != null) {
                mRationaleListener.showRationale(mSource.getContext(), rationaleList, this);
            } else {
                execute();
            }
        } else {
            callbackSucceed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    final public void execute() {
        PermissionActivity.requestPermission(mSource.getContext(), mDeniedPermissions, this);
    }

    @Override
    final public void onRequestPermissionsResult(@NonNull String[] permissions) {
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
        if (mDenied != null) {
            mDenied.onAction(deniedList);
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
            if (source.isShowRationalePermission(permission)) {
                rationaleList.add(permission);
            }
        }
        return rationaleList;
    }
}