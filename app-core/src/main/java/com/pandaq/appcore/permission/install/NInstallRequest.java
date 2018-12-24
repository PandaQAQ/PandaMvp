package com.pandaq.appcore.permission.install;

import com.pandaq.appcore.permission.source.Source;

import java.io.File;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :request install permission for Android O-
 */
public class NInstallRequest extends InstallRequestImp {

    public NInstallRequest(Source source) {
        super.source(source);
    }


    @Override
    public void start() {
        installExecute();
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

}
