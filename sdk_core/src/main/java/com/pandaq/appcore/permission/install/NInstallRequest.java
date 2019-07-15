package com.pandaq.appcore.permission.install;

import com.pandaq.appcore.permission.source.Source;

import java.io.File;

import android.support.annotation.NonNull;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :request install permission for Android O-
 */
public class NInstallRequest extends BaseInstallRequest {

    public NInstallRequest(Source source) {
        super.source(source);
    }


    @Override
    public void start() {
        installExecute();
    }

    @NonNull
    @Override
    public final  InstallRequest file(File apk) {
        mFile = apk;
        return this;
    }

    @NonNull
    @Override
    public final  InstallRequest file(String path) {
        mFile = new File(path);
        return this;
    }

}
