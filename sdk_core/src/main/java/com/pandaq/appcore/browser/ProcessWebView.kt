package com.pandaq.appcore.browser

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import com.pandaq.appcore.BuildConfig
import com.pandaq.appcore.browser.bridge.BridgeData
import com.pandaq.appcore.browser.bridge.JavaScriptApis
import com.pandaq.rxpanda.utils.GsonUtil
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * Created by PandaQ on 2017/6/29.
 * 带进度条的WebView
 */
class ProcessWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    WebView(context, attrs) {

    private var loadCallback: LoadCallback? = null

    init {
        webViewClient = MyWebClient()
        webChromeClient = ChromeClient()
        addJavascriptInterface(JavaScriptApis(), "Android")
        setWebContentsDebuggingEnabled(BuildConfig.IN_DEBUG)
    }

    private inner class ChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }
    }

    private inner class MyWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return if (Build.VERSION.SDK_INT < 26) {
                view.loadUrl(request.url.toString())
                true
            } else {
                false
            }
        }

        override fun onLoadResource(p0: WebView?, p1: String?) {
            super.onLoadResource(p0, p1)
            loadCallback?.onLoadResource()
        }

        override fun onPageCommitVisible(p0: WebView?, p1: String?) {
            super.onPageCommitVisible(p0, p1)
        }

        override fun onPageStarted(view: WebView?, url: String?, p2: Bitmap?) {
            super.onPageStarted(view, url, p2)
            loadCallback?.onStart()
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            loadCallback?.onFinish()
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

    override fun canGoBack(): Boolean {
        return super.canGoBack() && !url.contains("homepage")
    }

    fun setLoadCallback(loadCallback: LoadCallback?) {
        this.loadCallback = loadCallback
    }

    interface LoadCallback {
        fun onStart()
        fun onLoadResource()
        fun onFinish()
    }
}