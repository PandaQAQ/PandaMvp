package com.pandaq.appcore.browser

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.pandaq.appcore.BuildConfig
import com.pandaq.appcore.R
import com.pandaq.appcore.browser.bridge.BridgeData
import com.pandaq.appcore.browser.bridge.JavaScriptApis
import com.pandaq.rxpanda.utils.GsonUtil

/**
 * Created by PandaQ on 2017/6/29.
 * 带进度条的WebView
 */
class ProcessWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : WebView(context, attrs) {

    private val mProgressBar by lazy {
        ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
    }

    init {
        mProgressBar.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0)
        val drawable = context.resources.getDrawable(R.drawable.core_webview_process_state, null)
        mProgressBar.progressDrawable = drawable
        addView(mProgressBar)
        webViewClient = MyWebClient()
        webChromeClient = ChromeClient()
        addJavascriptInterface(JavaScriptApis(), "Android")
        setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
    }

    private inner class ChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100) {
                mProgressBar.visibility = View.GONE
            } else {
                if (mProgressBar.visibility == View.GONE) {
                    mProgressBar.visibility = View.VISIBLE
                    mProgressBar.progress = newProgress
                }
            }
        }
    }

    private inner class MyWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            post {
                view.measure(0, 0)
                layoutParams.height = view.measuredHeight
            }
        }
    }

    /**
     * 向 H5 端发送数据
     *
     *
     */
    fun sendDataToH5(data: BridgeData) {
        val script = "javascript:${data.methodName}(${GsonUtil.gson().toJson(data.data)})"
        loadUrl(script)
    }

}