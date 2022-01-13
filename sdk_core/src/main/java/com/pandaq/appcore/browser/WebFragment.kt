package com.pandaq.appcore.browser

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import androidx.fragment.app.Fragment
import com.pandaq.appcore.browser.bridge.BridgeData
import com.pandaq.appcore.browser.bridge.JavaScriptApis
import com.pandaq.appcore.log.PLogger
import com.pandaq.appcore.utils.CameraUtils
import com.pandaq.appcore.utils.system.AppUtils
import com.tencent.smtt.sdk.WebSettings


/**
 * Created by huxinyu on 2019/8/29.
 * Email : panda.h@foxmail.com
 * Description :基于 X5 浏览服务封装的 webview fragment
 */
class WebFragment : Fragment() {

    private var webView: ProcessWebView? = null
    private var url: String? = null
    private var isLoadWithBaseURL = false
    private var headers: HashMap<String, String> = hashMapOf()
    private var viewCreated = false
    private var loadCallback: ProcessWebView.LoadCallback? = null

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
        webView?.settings?.domStorageEnabled = true
        webView?.settings?.mixedContentMode = MIXED_CONTENT_ALWAYS_ALLOW
        webView?.isVerticalScrollBarEnabled = false
        webView?.isHorizontalScrollBarEnabled = false
        webView?.setBackgroundColor(Color.TRANSPARENT)
        return webView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView?.loadUrl(url, headers)
        webView?.setLoadCallback(loadCallback)
        viewCreated = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webView = null
    }

    fun setLoadCallback(loadCallback: ProcessWebView.LoadCallback) {
        this.loadCallback = loadCallback
        webView?.setLoadCallback(loadCallback)
    }

    fun getWebView(): ProcessWebView? = webView

    fun loadUrl(url: String, headers: HashMap<String, String>? = null) {
        this.url = url
        headers?.let {
            this.headers.putAll(it)
        }
        if (viewCreated) {
            webView?.clearHistory()
            webView?.loadUrl(this.url)
            webView?.postDelayed({
                webView?.reload()
            }, 300)
        }
        PLogger.d("Url is : $url")
    }

    fun loadHtml(html: String) {
        isLoadWithBaseURL = true
        webView?.loadDataWithBaseURL(
            HtmlMaker.BASE_URL,
            html,
            HtmlMaker.MIME_TYPE,
            HtmlMaker.ENCODING,
            HtmlMaker.FAIL_URL
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            JavaScriptApis.CALLBACK_CODE_TAKE_PHOTO -> {
                val bridgeData = BridgeData(
                    JavaScriptApis.jsMethodsNames[JavaScriptApis.CALLBACK_CODE_TAKE_PHOTO],
                    CameraUtils.getPhotoPath()
                )
                webView?.sendDataToH5(bridgeData)
                JavaScriptApis.jsMethodsNames.remove(JavaScriptApis.CALLBACK_CODE_TAKE_PHOTO)
            }
        }
    }

    fun canGoBack(): Boolean {
        return if (webView == null) {
            false
        } else {
            webView!!.canGoBack()
        }
    }

    fun goBack() {
        webView?.goBack()
    }

    fun loadJs(js:String){
        webView?.loadUrl(js)
    }
}