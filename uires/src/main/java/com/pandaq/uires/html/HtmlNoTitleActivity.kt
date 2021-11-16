package com.pandaq.uires.html

import android.content.Intent
import android.view.View
import com.pandaq.appcore.BuildConfig
import com.pandaq.appcore.browser.WebFragment
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.uires.R
import com.pandaq.uires.databinding.ActivityHtmlBinding
import com.pandaq.uires.mvp.BaseActivity

/**
 * Created by huxinyu on 2020/4/1.
 * Email : panda.h@foxmail.com
 * Description :一个基本的 H5 页面
 */
class HtmlNoTitleActivity : BaseActivity<BasePresenter<*>, ActivityHtmlBinding>() {

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

    override fun initVariable() {
        url = intent.getStringExtra("URL").toString()
    }

    override fun initView() {
        if (BuildConfig.SHOW_LOG) {
            binding.clDebug.visibility = View.VISIBLE
        } else {
            binding.clDebug.visibility = View.GONE
        }
        binding.tvLoad.setOnClickListener {
            webFragment.loadUrl(binding.etUrlDebug.text.toString())
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