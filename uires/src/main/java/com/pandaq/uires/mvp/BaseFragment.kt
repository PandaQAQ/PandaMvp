package com.pandaq.uires.mvp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.pandaq.appcore.BuildConfig
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.framework.mvp.CoreBaseFragment
import com.pandaq.appcore.utils.NetWorkUtils
import com.pandaq.uires.R
import com.pandaq.uires.loading.LoadingDialogUtil
import com.pandaq.uires.msgwindow.Toaster
import com.pandaq.uires.stateview.DefaultStateClickListener
import com.pandaq.uires.stateview.StateLayout

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
abstract class BaseFragment<P : BasePresenter<*>, VB : ViewBinding> : CoreBaseFragment<P, VB>() {

    // 状态页
    protected var mStateLayout: StateLayout? = null

    protected open fun showState(): Boolean {
        return false
    }

    @SuppressLint("InflateParams")
    override fun getRootView(): ViewGroup? {
        if (showState()) {
            return LayoutInflater.from(context)
                .inflate(R.layout.res_state_layout, null) as ViewGroup?
        }
        return super.getRootView()
    }

    override fun initStateLayout() {
        mStateLayout = contentView?.findViewById(R.id.res_state_layout)
        mStateLayout?.setOnStateClickListener(object : DefaultStateClickListener {
            override fun onEmptyClick() {
                refreshWhenError()
            }

            override fun onErrorClick() {
                refreshWhenError()
            }

            override fun onNetErrorClick() {
                refreshWhenError()
            }
        })
    }

    protected fun setStateLayout(stateLayout: StateLayout) {
        if (mStateLayout != null && stateLayout != mStateLayout) {
            if (BuildConfig.IN_DEBUG) {
                throw IllegalStateException("StateLayout 初始化多次，检查是否布局文件使用 StateLayout 且，showState() 返回 true")
            }
        } else {
            mStateLayout = stateLayout
        }
        mStateLayout?.setOnStateClickListener(object : DefaultStateClickListener {
            override fun onEmptyClick() {
                refreshWhenError()
            }

            override fun onErrorClick() {
                refreshWhenError()
            }

            override fun onNetErrorClick() {
                refreshWhenError()
            }
        })
    }

    /**
     * 错误、无数据点击重试，默认实现为重新 load 界面数据
     */
    open fun refreshWhenError() {
        showLoading()
        loadData()
    }

    override fun dialogLoading(cancelAble: Boolean, msg: String?) {
        LoadingDialogUtil.show(ActivityTask.getInstance().currentActivity(), msg, cancelAble)
    }

    override fun dialogLoadingWithCover(msg: String?) {
        LoadingDialogUtil.showWithCover(ActivityTask.getInstance().currentActivity(), msg, false)
    }

    override fun showError(showErrorPage: Boolean, errMsg: String?) {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick(this.javaClass.simpleName)
            LoadingDialogUtil.hideProgressQuick(this.activity?.javaClass?.simpleName)
        }
        if (showErrorPage) {
            if (NetWorkUtils.isNetworkConnected()) {
                mStateLayout?.showNetError()
            } else {
                mStateLayout?.showError()
            }
        } else {
            errMsg?.let {
                Toaster.showError(errMsg)
            }
        }
    }

    override fun showEmpty(msg: String?) {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick(this.javaClass.simpleName)
            LoadingDialogUtil.hideProgressQuick(this.activity?.javaClass?.simpleName)
        }
        mStateLayout?.showEmpty(msg)
    }

    override fun showLoading() {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick(this.javaClass.simpleName)
            LoadingDialogUtil.hideProgressQuick(this.activity?.javaClass?.simpleName)
        }
        mStateLayout?.showLoading()
    }

    override fun showContent() {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick(this.javaClass.simpleName)
            LoadingDialogUtil.hideProgressQuick(this.activity?.javaClass?.simpleName)
        }
        mStateLayout?.showContent()
    }
}