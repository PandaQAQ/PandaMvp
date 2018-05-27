package com.pandaq.pandamvp.net;

import com.pandaq.pandacore.framework.mvpbase.IBasePresenter;
import com.pandaq.pandacore.http.BaseObserver;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class MyBaseObserver<T> extends BaseObserver<T> {

    public MyBaseObserver(IBasePresenter basePresenter) {
        super(basePresenter);
    }

    @Override
    protected void onSuccess(T t) {

    }

    @Override
    protected void onFail(Throwable e) {

    }

    @Override
    protected void onFinish() {

    }
}
