package com.pandaq.pandacore.framework.mvpbase;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 * <p>
 * module 中让 Mvp 的 baseView 实现此接口（app 中将给出实现模板）
 *
 * @param <P> P 层对象
 */
public interface IBaseMvpView<P extends IBasePresenter> {
    /**
     * 在 View 层中注入注入 P 层
     *
     * @return P 层具体实现对象
     */
    P injectPresenter();
}
