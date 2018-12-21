package com.pandaq.appcore.permission.install;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.pandaq.appcore.permission.Executor;
import com.pandaq.appcore.permission.PermissionActivity;
import com.pandaq.appcore.permission.source.Source;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :request install permission for Android O+
 */
public class OInstallRequest extends InstallRequestImp implements Executor, PermissionActivity.RequestListener {

    public OInstallRequest(Source source) {
        super.source(source);
    }


    @Override
    final  public void execute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionActivity.requestInstall(mSource.getContext(), this);
        }
    }


    @Override
    final  public void onRequestCallback() {
        new Handler(Looper.getMainLooper()).postDelayed(this::dispatchCallback, 100);
    }

    private void dispatchCallback() {
        if (mSource.canRequestPackageInstalls()) {
            callbackSucceed();
            installExecute();
        } else {
            callbackFailed();
        }
    }

    @Override
    final  public void start() {
        if (mSource.canRequestPackageInstalls()) {
            callbackSucceed();
            installExecute();
        } else {
            showRationale(this);
        }
    }
}
