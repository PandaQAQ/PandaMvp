package com.pandaq.appcore.browser

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import com.pandaq.appcore.utils.system.AppUtils


/**
 * Created by huxinyu on 2019/8/29.
 * Email : panda.h@foxmail.com
 * Description :基于 X5 浏览服务封装的 webview fragment
 */
class WebFragment : Fragment() {

    private var webView: ProcessWebView? = null
    private var url: String? = null
    private var headers: HashMap<String, String> = hashMapOf()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        webView = ProcessWebView(AppUtils.getContext(), null)
        webView?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView?.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        webView?.settings?.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webView?.settings?.useWideViewPort = true
        webView?.settings?.loadWithOverviewMode = true
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView?.isVerticalScrollBarEnabled = false
        webView?.isHorizontalScrollBarEnabled = false
        return webView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView?.loadUrl(url, headers)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webView = null
    }

    fun getWebView(): ProcessWebView? = webView

    fun loadUrl(url: String, headers: HashMap<String, String>? = null) {
        this.url = url
        headers?.let {
            this.headers.putAll(it)
        }
    }

}