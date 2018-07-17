package com.pandaq.appcore.framework.mvpbase;

import io.reactivex.disposables.Disposable;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :    /**
 * module 中让 Mvp 的 basePresenter 实现此接口（app 中将给出实现模板）
 */
public interface IBasePresenter {
    /**
     * 统一管理订阅的 Disposable
     *
     * @param disposable 订阅时获取的 Disposable 对象
     */
    void addDisposable(Disposable disposable);

    /**
     * 统一解除绑定
     */
    void dispose();

    /**
     * presenter 与 view 层关联时调用
     */
    void onMvpViewAttach();

    /**
     * presenter 与 view 层解除关联时调用（view 的 onDestroyView / onDestroy 中调用）
     */
    void onMvpViewDetach();
}
