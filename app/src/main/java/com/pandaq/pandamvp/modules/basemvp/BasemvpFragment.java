package com.pandaq.pandamvp.modules.basemvp;

import com.pandaq.pandamvp.modules.base.BaseFragment;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description : mvp Fragment 的基类，实现 Presenter 与 fragment 生命周期绑定。
 * 用 Fragment 的生命周期管理 presenter 的生命周期，及时销毁回收，避免内存泄漏。
 */
public class BasemvpFragment extends BaseFragment {
    @Override
    public void initVariable() {

    }

    @Override
    public int bindContentRes() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }
}
