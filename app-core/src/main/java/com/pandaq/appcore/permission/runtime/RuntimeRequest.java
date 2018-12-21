
package com.pandaq.appcore.permission.runtime;


import com.pandaq.appcore.permission.Action;
import com.pandaq.appcore.permission.Rationale;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :runtime request
 */
public interface RuntimeRequest {

    /**
     * One or more permissions.
     */
    @NonNull
    RuntimeRequest permission(String... permissions);

    /**
     * One or more permission groups.
     */
    @NonNull
    RuntimeRequest permission(String[]... groups);

    /**
     * Set request rationale.
     */
    @NonNull
    RuntimeRequest rationale(Rationale<List<String>> listener);

    /**
     * Action to be taken when all permissions are granted.
     */
    @NonNull
    RuntimeRequest onGranted(Action<List<String>> granted);

    /**
     * Action to be taken when all permissions are denied.
     */
    @NonNull
    RuntimeRequest onDenied(Action<List<String>> denied);

    /**
     * RuntimeRequest permission.
     */
    void start();

}