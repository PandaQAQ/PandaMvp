package com.pandaq.pandamvp.template

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.pandaq.app_launcher.R
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.framework.mvp.BaseActivity
import com.pandaq.uires.loading.LoadingDialogUtil
import com.pandaq.uires.msgwindow.Toaster
import com.pandaq.uires.toolbar.CNToolbar


/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
abstract class AppBaseActivity<P : AppBasePresenter<*>> : BaseActivity<P>() {

    protected var mToolbar: CNToolbar? = null

    fun setTitle(title: String) {
        mToolbar?.setTitle(title)
    }

    override fun initToolbar() {
        val view = layoutInflater.inflate(R.layout.res_cn_title, null)
        mToolbar = view.findViewById(R.id.toolbar)
        mToolbar?.let {
            it.setOnBackPressed(View.OnClickListener { onBackPressed() })
            it.setTitle(title)
        }
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = ""
            actionBar.setDisplayShowHomeEnabled(false)
            actionBar.setDisplayShowCustomEnabled(true)
            val alp = ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT)
            actionBar.setCustomView(view, alp)
        }
    }

    override fun showLoading(msg: String?) {
        LoadingDialogUtil.show(ActivityTask.getInstance().currentActivity(), msg, true)
    }

    override fun showLoading() {
        LoadingDialogUtil.show(ActivityTask.getInstance().currentActivity(), true)
    }

    override fun hideLoading() {
        LoadingDialogUtil.hideProgressQuick()
    }

    override fun showLoading(cancelAble: Boolean) {
        LoadingDialogUtil.show(ActivityTask.getInstance().currentActivity(), cancelAble)
    }

    override fun onError(errCode: Long, errMsg: String?) {
        errMsg?.let {
            Toaster.msg(errMsg).showError()
        }
    }

    override fun onFinish(success: Boolean) {
        hideLoading()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("android:support:fragments", null)
    }
}