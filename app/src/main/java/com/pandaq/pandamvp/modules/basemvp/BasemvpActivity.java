package com.pandaq.pandamvp.modules.basemvp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pandaq.pandamvp.modules.base.BaseActivity;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description : mvp Activity 的基类，实现 Presenter 与 Activity 生命周期绑定。
 * 用 Activity 的生命周期管理 presenter 的生命周期，及时销毁回收，避免内存泄漏。
 */
@SuppressLint("Registered")
public abstract class BasemvpActivity<P extends BasePresenterImp> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (injectPresenter() != null) {
            this.mPresenter = injectPresenter();
        } else {
            throw new NullPointerException("presenter here must not be null ！！！");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 注入 Presenter
     */
    public abstract P injectPresenter();

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
