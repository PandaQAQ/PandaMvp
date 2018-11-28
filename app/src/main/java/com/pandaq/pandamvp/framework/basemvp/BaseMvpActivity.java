package com.pandaq.pandamvp.framework.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pandaq.appcore.framework.mvpbase.IBaseContract;
import com.pandaq.appcore.framework.mvpbase.BasePresenter;
import com.pandaq.pandamvp.framework.base.BaseActivity;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :MVPActivity 基类
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseContract.IBaseMvpView {

    protected P mPresenter;

    protected abstract P injectPresenter();

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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(int errCode, String errMsg) {

    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁 View 接触绑定
        mPresenter.onMvpViewDetach();
    }
}
