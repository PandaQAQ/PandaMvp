package com.pandaq.pandamvp.template

import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.framework.mvp.IView
import com.pandaq.rxpanda.exception.ApiException

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
open class AppBasePresenter<V : IView>(view: V) : BasePresenter<V>(view) {

    override fun handelError(showErrorPage: Boolean, e: ApiException?) {
        super.handelError(showErrorPage, e)
    }

}