package com.pandaq.appcore.permission.install;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.pandaq.appcore.permission.Action;
import com.pandaq.appcore.permission.Executor;
import com.pandaq.appcore.permission.source.Source;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

/**
 * Created by huxinyu on 2018/12/21.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public abstract class InstallRequestImp implements InstallRequest {

    Source mSource;
    private File mFile;
    private Action<File> mGranted;
    private Action<File> mDenied;

    @NonNull
    final public InstallRequest source(Source source) {
        mSource = source;
        return this;
    }

    @NonNull
    @Override
    final public InstallRequest file(File apk) {
        mFile = apk;
        return this;
    }

    @NonNull
    @Override
    final public InstallRequest file(String path) {
        mFile = new File(path);
        return this;
    }

    @NonNull
    @Override
    final public InstallRequest onGranted(Action<File> granted) {
        this.mGranted = granted;
        return this;
    }

    @NonNull
    @Override
    final public InstallRequest onDenied(Action<File> denied) {
        this.mDenied = denied;
        return this;
    }

    final void installExecute() {
        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = getFileUri(mSource.getContext(), mFile);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        mSource.startActivity(intent);
    }

    /**
     * Why permissions are required.
     */
    final void showRationale(Executor executor) {
        executor.execute();
    }

    /**
     * Get compatible Android 7.0 and lower versions of Uri.
     *
     * @param context {@link Context}.
     * @param file    apk file.
     * @return uri.
     */
    private Uri getFileUri(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getPackageName() + ".file.path.share", file);
        }
        return Uri.fromFile(file);
    }

    /**
     * Callback acceptance status.
     */
    final void callbackSucceed() {
        if (mGranted != null) {
            mGranted.onAction(mFile);
        }
    }

    /**
     * Callback rejected state.
     */
    final void callbackFailed() {
        if (mDenied != null) {
            mDenied.onAction(mFile);
        }
    }
}
