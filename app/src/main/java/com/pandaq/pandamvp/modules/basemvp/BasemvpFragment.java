package com.pandaq.pandamvp.modules.basemvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.pandaq.pandamvp.modules.base.BaseFragment;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description : mvp Fragment 的基类，实现 Presenter 与 fragment 生命周期绑定。
 * 用 Fragment 的生命周期管理 presenter 的生命周期，及时销毁回收，避免内存泄漏。
 */
public abstract class BasemvpFragment<P extends BasePresenterImp> extends BaseFragment {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (injectPresenter() != null) {
            mPresenter = injectPresenter();
        } else {
            throw new NullPointerException("presenter here must not be null !!!");
        }
    }

    /**
     * 注入当前界面对应的 Presenter
     *
     * @return 当前界面对应的 Presenter
     */
    public abstract P injectPresenter();
}
