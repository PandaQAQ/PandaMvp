
package com.pandaq.appcore.permission;

import android.content.Context;

import java.util.List;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public interface Rationale<T> {

    /**
     * Show rationale of permissions to user.
     *
     * @param context     context.
     * @param permissions show rationale permissions.
     * @param executor    executor.
     */
    void showRationale(Context context,T permissions, Executor executor);
}