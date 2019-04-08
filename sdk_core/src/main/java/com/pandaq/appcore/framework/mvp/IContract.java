package com.pandaq.appcore.framework.mvp;

import io.reactivex.disposables.Disposable;

/**
 * Created by huxinyu on 2018/9/14.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :基础中介接口类
 */
public interface IContract {

    /**
     * <p>
     * Description :V 层基础接口实现
     * <p>
     * module 中让 Mvp 的 baseView 实现此接口（app 中将给出实现模板）
     */
    interface IMvpView {
        /**
         * 显示加载 Loading
         */
        void showLoading(String msg);

        /**
         * 隐藏加载 Loading
         */
        void hideLoading();

        /**
         * 出错回调
         *
         * @param errCode 错误码
         * @param errMsg  错误信息
         */
        void onError(long errCode, String errMsg);

        /**
         * 结束回调
         * 一次请求完成回调不管是错误还是成都将调用
         */
        void onLoadFinish();
    }

    /**
     * <p>
     * Description :    /**
     * module 中让 Mvp 的 basePresenter 实现此接口（app 中将给出实现模板）
     */
    interface IPresenter {
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
}
