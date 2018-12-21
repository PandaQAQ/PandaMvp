package com.pandaq.appcore.permission.factory;

import android.os.Build;

import com.pandaq.appcore.permission.install.InstallRequest;
import com.pandaq.appcore.permission.install.NInstallRequest;
import com.pandaq.appcore.permission.install.OInstallRequest;
import com.pandaq.appcore.permission.runtime.LRuntimeRequest;
import com.pandaq.appcore.permission.runtime.MRuntimeRequest;
import com.pandaq.appcore.permission.runtime.RuntimeRequest;
import com.pandaq.appcore.permission.source.Source;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class RequestFactory implements IFactory {

    @Override
    public RuntimeRequest runtime(Source source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return new MRuntimeRequest(source);
        }
        return new LRuntimeRequest(source);
    }

    @Override
    public InstallRequest install(Source source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new OInstallRequest(source);
        }
        return new NInstallRequest(source);
    }


}
