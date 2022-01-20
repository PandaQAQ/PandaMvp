package com.pandaq.pandamvp.template

import com.pandaq.uires.mvp.core.BasePresenter
import com.pandaq.uires.mvp.core.IView

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