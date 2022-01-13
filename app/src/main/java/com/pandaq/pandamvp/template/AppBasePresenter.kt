package com.pandaq.pandamvp.template

import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.framework.mvp.IView

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
open class AppBasePresenter<V : IView> : BasePresenter<V>() {

    override fun refreshApiConfig() {

    }

}