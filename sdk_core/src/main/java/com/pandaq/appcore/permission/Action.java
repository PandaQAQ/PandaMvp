
package com.pandaq.appcore.permission;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :action interface for request callback
 */
public interface Action<T> {

    /**
     * An action.
     *
     * @param permissions the current action under permissions.
     */
    void onAction(T permissions);
}
