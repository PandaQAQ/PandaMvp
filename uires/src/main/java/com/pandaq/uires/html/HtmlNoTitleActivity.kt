package com.pandaq.uires.html

import android.content.Intent
import android.view.View
import com.pandaq.appcore.browser.WebFragment
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.uires.BuildConfig
import com.pandaq.uires.R
import com.pandaq.uires.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_html.*

/**
 * Created by huxinyu on 2020/4/1.
 * Email : panda.h@foxmail.com
 * Description :一个基本的 H5 页面
 */
class HtmlNoTitleActivity : BaseActivity<BasePresenter<*>>() {

    companion object {
        fun start(url: String?) {
            if (url.isNullOrEmpty()) {
                return
            }
            val current = ActivityTask.getInstance().currentActivity()
            val intent = Intent(current, HtmlNoTitleActivity::class.java)
            intent.putExtra("URL", url)
            current.startActivity(intent)
        }
    }

    private lateinit var url: String

    private val webFragment by lazy {
        WebFragment()
    }


    override fun injectPresenter(): BasePresenter<*>? = null

    override fun bindContentRes(): Int = R.layout.activity_html

    override fun initVariable() {
        url = intent.getStringExtra("URL")
    }


    override fun initView() {
        if (BuildConfig.DEBUG) {
            cl_debug.visibility = View.VISIBLE
        } else {
            cl_debug.visibility = View.GONE
        }
        tv_load.setOnClickListener {
            webFragment.loadUrl(et_url_debug.text.toString())
        }
        switchFragment(R.id.fl_container, null, webFragment)
    }

    override fun loadData() {
        webFragment.loadUrl(url)
    }

    override fun onFinish(success: Boolean) {

    }

    override fun showLoading(msg: String?) {

    }

    override fun showLoading() {

    }

    override fun showLoading(cancelAble: Boolean) {

    }

    override fun onError(errCode: Long, errMsg: String?) {

    }

    override fun onBackPressed() {
        if (webFragment.canGoBack()) {
            webFragment.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun initToolbar() {

    }
}