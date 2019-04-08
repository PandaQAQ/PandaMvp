
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
     * Set request rationale.
     * <p>
     * called when permission has requested before,and user refused it
     */
    @NonNull
    RuntimeRequest rationale(Rationale<List<String>> listener);

    /**
     * Action to be taken when all permissions are granted.
     * <p>
     * called when user accept the requested permission
     */
    @NonNull
    RuntimeRequest onGranted(Action<List<String>> granted);

    /**
     * Action to be taken when all permissions are denied.
     * <p>
     * called when user refused the permission
     */
    @NonNull
    RuntimeRequest onDenied(Action<List<String>> denied);

    /**
     * * Action to be taken when  permissions are denied and never ask again.
     * * <p>
     *
     * @param denied always denied Permission
     */
    @NonNull
    RuntimeRequest onAlwaysDenied(Action<List<String>> denied);

    /**
     * RuntimeRequest permission.
     */
    void start();

}