package com.pandaq.app_launcher.ui.framework;

import com.pandaq.appcore.framework.mvp.BaseFragment;
import com.pandaq.appcore.framework.mvp.BasePresenter;
import com.pandaq.uires.progress.LoadingDialog;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :BaseFragment 基类
 */

public abstract class AppBaseFragment<P extends BasePresenter> extends BaseFragment<P> {

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading(String msg) {
        if (getActivity() instanceof AppBaseActivity) {
            ((AppBaseActivity) getActivity()).showLoading(msg);
        }
    }

    @Override
    public void hideLoading() {
        if (getActivity() instanceof AppBaseActivity) {
            ((AppBaseActivity) getActivity()).hideLoading();
        }
    }

    @Override
    public void onError(long errCode, String errMsg) {

    }

    @Override
    public void onLoadFinish() {

    }
}
