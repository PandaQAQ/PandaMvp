package com.pandaq.appcore.permission.install;

import com.pandaq.appcore.permission.source.Source;

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
        callbackSucceed();
        installExecute();
    }
}
