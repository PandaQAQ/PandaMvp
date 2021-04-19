package com.pandaq.pandamvp.template

import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.framework.mvp.IMvpView
import com.pandaq.rxpanda.exception.ApiException

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
open class AppBasePresenter<V : IMvpView>(view: V) : BasePresenter<V>(view) {

    protected fun handelError(e: ApiException?) {
        e?.let {
            mView?.onError(e.code, e.message)
        }
    }

}