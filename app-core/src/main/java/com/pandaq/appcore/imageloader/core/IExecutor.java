package com.pandaq.appcore.imageloader.core;

/**
 * Created by huxinyu on 2018/7/4.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :切换第三方的图片加载框架需实现的接口方法
 */
public interface IExecutor {

    /**
     * 执行加载请求
     *
     * @param request 配置对象
     */
    void execute(Request request);
}
