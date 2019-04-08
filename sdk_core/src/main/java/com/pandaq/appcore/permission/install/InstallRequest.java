package com.pandaq.appcore.permission.install;

import com.pandaq.appcore.permission.Action;
import com.pandaq.appcore.permission.Rationale;
import com.pandaq.appcore.permission.source.Source;

import java.io.File;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :install request
 */
public interface InstallRequest {

    /**
     * Action to be taken when all permissions are denied.
     */
    @NonNull
    InstallRequest onDenied(Action<File> denied);

    void start();
}
