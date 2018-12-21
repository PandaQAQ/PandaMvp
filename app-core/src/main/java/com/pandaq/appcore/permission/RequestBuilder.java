package com.pandaq.appcore.permission;

import android.os.Build;

import com.pandaq.appcore.permission.install.InstallRequest;
import com.pandaq.appcore.permission.install.NInstallRequest;
import com.pandaq.appcore.permission.install.OInstallRequest;
import com.pandaq.appcore.permission.runtime.LRuntimeRequest;
import com.pandaq.appcore.permission.runtime.MRuntimeRequest;
import com.pandaq.appcore.permission.runtime.RuntimeRequest;
import com.pandaq.appcore.permission.source.Source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class RequestBuilder {

    private Source mSource;

    protected RequestBuilder source(Source source) {
        mSource = source;
        return this;
    }

    /**
     * request permission s
     *
     * @param permissions the permissions request
     * @return request
     */
    public RuntimeRequest runtime(String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return new MRuntimeRequest(mSource).permission(permissions);
        } else {
            return new LRuntimeRequest(mSource).permission(permissions);
        }
    }

    /**
     * request groups of permissions
     *
     * @param groups permission group
     * @return request
     */
    public RuntimeRequest runtime(String[]... groups) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : groups) {
            permissionList.addAll(Arrays.asList(group));
        }
        String[] permissions = permissionList.toArray(new String[0]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return new MRuntimeRequest(mSource).permission(permissions);
        } else {
            return new LRuntimeRequest(mSource).permission(permissions);
        }
    }

    public InstallRequest install() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new OInstallRequest(mSource);
        } else {
            return new NInstallRequest(mSource);
        }
    }
}
