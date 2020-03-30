package com.pandaq.pandamvp.template

import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.framework.mvp.BaseFragment
import com.pandaq.uires.loading.LoadingDialogUtil
import com.pandaq.uires.msgwindow.Toaster

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
abstract class AppBaseFragment<P : AppBasePresenter<*>> : BaseFragment<P>() {
    override fun showLoading(msg: String?) {
        LoadingDialogUtil.show(ActivityTask.getInstance().currentActivity(), msg)
    }

    override fun showLoading() {
        showLoading(true)
    }

    override fun showLoading(cancelAble: Boolean) {
        LoadingDialogUtil.show(ActivityTask.getInstance().currentActivity(), cancelAble)
    }

    open fun hideLoading() {
        LoadingDialogUtil.hideProgressQuick()
    }

    override fun onError(errCode: Long, errMsg: String?) {
        errMsg?.let {
            Toaster.showError(errMsg)
        }
    }

    override fun onFinish(success: Boolean) {
        hideLoading()
    }
}