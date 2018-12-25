package com.pandaq.pandamvp.framework;

import com.pandaq.appcore.framework.base.BaseFragment;
import com.pandaq.appcore.framework.base.BasePresenter;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :Fragment 基类
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
}
