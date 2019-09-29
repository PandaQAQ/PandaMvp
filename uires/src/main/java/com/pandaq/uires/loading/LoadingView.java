package com.pandaq.uires.loading;

/**
 * Created by huxinyu on 2018/5/21.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 加载 dialog 中加入的 LoadingView 需要实现此接口，与加载生命周期相关联
 */
public interface LoadingView {

    void start();

    void loading();

    void finish();
}
