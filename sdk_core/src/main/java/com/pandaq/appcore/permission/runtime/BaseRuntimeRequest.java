package com.pandaq.appcore.permission.runtime;

/**
 * Created by huxinyu on 2018/12/24.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
abstract class BaseRuntimeRequest implements RuntimeRequest {
    /**
     * One or more permissions.
     */
    abstract RuntimeRequest permission(String... permissions);

    /**
     * One or more permission groups.
     */
    abstract RuntimeRequest permission(String[]... groups);
}
