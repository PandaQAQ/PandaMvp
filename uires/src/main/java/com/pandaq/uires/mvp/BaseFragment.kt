package com.pandaq.uires.mvp

import androidx.annotation.LayoutRes
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.framework.mvp.CoreBaseFragment
import com.pandaq.appcore.utils.NetWorkUtils
import com.pandaq.uires.BuildConfig
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
abstract class BaseFragment<P : BasePresenter<*>> : CoreBaseFragment<P>() {

    // 状态页
    protected var mStateLayout: StateLayout? = null

    protected open fun showState(): Boolean {
        return false
    }

    @LayoutRes
    abstract fun bindContentRes(): Int


    override fun getContentRes(): Int {
        return if (showState()) {
            R.layout.res_state_layout
        } else {
            bindContentRes()
        }
    }

    override fun initStateLayout() {
        mStateLayout = contentView?.findViewById(R.id.res_state_layout)
        mStateLayout?.let {
            it.removeAllViews()
            it.addView(layoutInflater.inflate(bindContentRes(), null, false))
            initStateLayout(it)
        }
    }

    protected fun initStateLayout(stateLayout: StateLayout) {
        if (mStateLayout == null) {
            mStateLayout = stateLayout
        } else {
            if (BuildConfig.DEBUG) {
                throw IllegalStateException("StateLayout init twice")
            }
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

    override fun dialogLoading(msg: String?) {
        LoadingDialogUtil.show(ActivityTask.getInstance().currentActivity(), msg, true)
    }

    override fun dialogLoading(cancelAble: Boolean) {
        LoadingDialogUtil.show(ActivityTask.getInstance().currentActivity(), cancelAble)
    }

    override fun showError(showErrorPage: Boolean, errMsg: String?) {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick()
        }
        if (showErrorPage) {
            if (NetWorkUtils.isNetworkConnected()){
                mStateLayout?.showNetError()
            }else{
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
            LoadingDialogUtil.hideProgressQuick()
        }
        mStateLayout?.showEmpty(msg)
    }

    override fun showLoading() {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick()
        }
        mStateLayout?.showLoading()
    }

    override fun showContent() {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick()
        }
        mStateLayout?.showContent()
    }
}