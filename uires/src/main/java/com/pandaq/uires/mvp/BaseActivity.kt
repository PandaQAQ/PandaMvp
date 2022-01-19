package com.pandaq.uires.mvp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.viewbinding.ViewBinding
import com.pandaq.appcore.BuildConfig
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.framework.mvp.CoreBaseActivity
import com.pandaq.appcore.utils.NetWorkUtils
import com.pandaq.uires.R
import com.pandaq.uires.loading.LoadingDialogUtil
import com.pandaq.uires.msgwindow.Toaster
import com.pandaq.uires.stateview.DefaultStateClickListener
import com.pandaq.uires.stateview.StateLayout
import com.pandaq.uires.toolbar.CNToolbar


/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
abstract class BaseActivity<P : BasePresenter<*>, VB : ViewBinding> : CoreBaseActivity<P, VB>() {

    protected var mToolbar: CNToolbar? = null

    // 状态页
    protected var mStateLayout: StateLayout? = null

    /**
     * 使用全页面添加状态 View，返回 true 的时候页面布局文件不要再添加 StateLayout
     */
    protected open fun showState(): Boolean {
        return false
    }

    fun setTitle(title: String) {
        mToolbar?.setTitle(title)
    }

    @SuppressLint("InflateParams")
    override fun getRootView(): ViewGroup? {
        if (showState()) {
            return LayoutInflater.from(this).inflate(R.layout.res_state_layout, null) as ViewGroup?
        }
        return super.getRootView()
    }

    override fun initStateLayout() {
        mStateLayout = findViewById(R.id.res_state_layout)
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

    @SuppressLint("InflateParams")
    override fun initToolbar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            val view = layoutInflater.inflate(R.layout.res_cn_title, null)
            mToolbar = view.findViewById(R.id.toolbar)
            mToolbar?.let {
                it.setOnBackPressed { onBackPressed() }
                it.setTitle(title)
                it.setLightStyle(false)
            }
            actionBar.title = ""
            actionBar.setDisplayShowHomeEnabled(false)
            actionBar.setDisplayShowCustomEnabled(true)
            val alp = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT
            )
            actionBar.setCustomView(view, alp)
        }
        initStateLayout()
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
        }

        if (showErrorPage) {
            if (NetWorkUtils.isNetworkConnected()) {
                mStateLayout?.showError()
            } else {
                mStateLayout?.showNetError()
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
        }
        mStateLayout?.showEmpty(msg)
    }

    override fun showLoading() {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick(this.javaClass.simpleName)
        }
        mStateLayout?.showLoading()
    }

    override fun showContent() {
        if (LoadingDialogUtil.isShowing()) {
            LoadingDialogUtil.hideProgressQuick(this.javaClass.simpleName)
        }
        mStateLayout?.showContent()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("android:support:fragments", null)
    }

    override fun onBackPressed() {
        finish()
    }
}