package com.pandaq.pandamvp.framework.basemvp;

import android.os.Bundle;

import com.pandaq.appcore.framework.mvpbase.BasePresenter;
import com.pandaq.appcore.framework.mvpbase.IBaseContract;
import com.pandaq.pandamvp.framework.base.BaseFragment;

import androidx.annotation.Nullable;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :MVPFragment基类
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements IBaseContract.IBaseMvpView {

    protected P mPresenter;

    protected abstract P injectPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (injectPresenter() != null) {
            this.mPresenter = injectPresenter();
        } else {
            throw new NullPointerException("presenter here must not be null ！！！");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 销毁 View 接触绑定
        mPresenter.onMvpViewDetach();
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
}
