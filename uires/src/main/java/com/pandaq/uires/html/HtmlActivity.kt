package com.pandaq.uires.html

import android.content.Intent
import android.view.View
import androidx.appcompat.app.ActionBar
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
class HtmlActivity : BaseActivity<BasePresenter<*>>() {

    companion object {
        fun start(title: String? = null, url: String?) {
            if (url.isNullOrEmpty()) {
                return
            }
            val current = ActivityTask.getInstance().currentActivity()
            val intent = Intent(current, HtmlActivity::class.java)
            intent.putExtra("URL", url)
            intent.putExtra("title", title)
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
        title = intent.getStringExtra("title")
        url = intent.getStringExtra("URL")
    }

    override fun initToolbar() {
        val view = layoutInflater.inflate(R.layout.res_cn_title, null)
        mToolbar = view.findViewById(R.id.toolbar)
        mToolbar?.let {
            it.setOnBackPressed(View.OnClickListener { onBackPressed() })
            it.setTitle(title)
            it.setLightStyle(true)
        }
        val actionBar = supportActionBar
        actionBar?.elevation = 0f
        if (actionBar != null) {
            actionBar.title = ""
            actionBar.setDisplayShowHomeEnabled(false)
            actionBar.setDisplayShowCustomEnabled(true)
            val alp = ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT)
            actionBar.setCustomView(view, alp)
        }
    }

    override fun initView() {
        title?.let {
            mToolbar?.setTitle(it)
        }
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

    override fun onBackPressed() {
        if (webFragment.canGoBack()) {
            webFragment.goBack()
        } else {
            super.onBackPressed()
        }
    }
}