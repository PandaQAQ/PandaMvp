package com.pandaq.appcore.permission.factory;

import com.pandaq.appcore.permission.install.InstallRequest;
import com.pandaq.appcore.permission.runtime.RuntimeRequest;
import com.pandaq.appcore.permission.source.Source;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public interface IFactory {
    RuntimeRequest runtime(Source source);

    InstallRequest install(Source source);
}
