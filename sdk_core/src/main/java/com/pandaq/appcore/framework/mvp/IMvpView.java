package com.pandaq.appcore.framework.mvp;

/**
 * Created by huxinyu on 2018/9/14.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :V 层基础接口实现
 * <p>
 * module 中让 Mvp 的 baseView 实现此接口（app 中将给出实现模板）
 */
public interface IMvpView {
    /**
     * 显示加载 Loading
     */
    void showLoading(String msg);

    /**
     * 显示加载 Loading
     */
    void showLoading();

    /**
     * 显示加载 Loading.是否可取消
     */
    void showLoading(boolean cancelAble);

    /**
     * 出错回调
     *
     * @param errCode 错误码
     * @param errMsg  错误信息
     */
    void onError(long errCode, String errMsg);

    /**
     * 结束回调 会自动调用 hideLoading
     * 一次请求完成回调不管是错误还是成都将调用
     */
    void onFinish(boolean success);
}
