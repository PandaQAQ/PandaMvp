package com.pandaq.appcore.permission.install;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.pandaq.appcore.permission.Executor;
import com.pandaq.appcore.permission.PermissionActivity;
import com.pandaq.appcore.permission.source.Source;

import java.io.File;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :request install permission for Android O+
 */
public class OInstallRequest extends BaseInstallRequest implements Executor, PermissionActivity.RequestListener {

    public OInstallRequest(Source source) {
        super.source(source);
    }


    @Override
    public final void execute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionActivity.requestInstall(mSource.getContext(), this);
        }
    }


    @Override
    public final void onRequestCallback() {
        new Handler(Looper.getMainLooper()).postDelayed(this::dispatchCallback, 100);
    }

    private void dispatchCallback() {
        if (mSource.canRequestPackageInstalls()) {
            installExecute();
        } else {
            callbackFailed();
        }
    }

    @Override
    public final void start() {
        if (mSource.canRequestPackageInstalls()) {
            installExecute();
        } else {
            showRationale(this);
        }
    }

    @NonNull
    @Override
    public final InstallRequest file(File apk) {
        mFile = apk;
        return this;
    }

    @NonNull
    @Override
    public final InstallRequest file(String path) {
        mFile = new File(path);
        return this;
    }

}
