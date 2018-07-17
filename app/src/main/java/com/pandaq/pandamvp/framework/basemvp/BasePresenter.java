package com.pandaq.pandamvp.framework.basemvp;

import com.pandaq.appcore.framework.mvpbase.TemplatePresenter;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :继承模板 Presenter 可重写实现自己的逻辑
 */
public abstract class BasePresenter<V> extends TemplatePresenter<V> {

    public BasePresenter(V mvpView) {
        super(mvpView);
    }

    @Override
    public void onMvpViewAttach() {
        super.onMvpViewAttach();
    }

    @Override
    public void onMvpViewDetach() {
        super.onMvpViewDetach();
    }
}
