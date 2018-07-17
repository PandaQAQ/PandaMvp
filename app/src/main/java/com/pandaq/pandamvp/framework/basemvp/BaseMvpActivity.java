package com.pandaq.pandamvp.framework.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pandaq.appcore.framework.mvpbase.IBaseMvpView;
import com.pandaq.pandamvp.framework.base.BaseActivity;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :无自定义需求时 MVP 界面 Activity 直接继承使用此 Activity
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseMvpView<P> {

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
    protected void onDestroy() {
        super.onDestroy();
        // 销毁 View 接触绑定
        mPresenter.onMvpViewDetach();
    }
}
