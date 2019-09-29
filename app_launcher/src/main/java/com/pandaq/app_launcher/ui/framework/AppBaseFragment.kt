package com.pandaq.app_launcher.ui.framework

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
        LoadingDialogUtil.show(ActivityTask.getInstance().topActivity, msg)
    }

    override fun showLoading() {
        showLoading(true)
    }

    override fun showLoading(cancelAble: Boolean) {
        LoadingDialogUtil.show(ActivityTask.getInstance().topActivity, cancelAble)
    }

    override fun hideLoading() {
        LoadingDialogUtil.hideProgressQuick()
    }

    override fun onError(errCode: Long, errMsg: String?) {
        errMsg?.let {
            Toaster.msg(errMsg).showError()
        }
    }

    override fun onLoadFinish(success: Boolean) {
        hideLoading()
    }
}