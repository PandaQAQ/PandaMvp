package com.pandaq.browser.client;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by huxinyu on 2018/9/7.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :基础 ChromeClient，需要重写并且保留进度条则继承此类{@link com.pandaq.browser.ProcessWebView}
 */
public class BaseChromeClient extends WebChromeClient {

    private ProgressCallback mCallback;

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (mCallback == null) return;
        if (newProgress == 100) {
            mCallback.done();
        } else {
            mCallback.onProgress(newProgress);
        }
    }

    /**
     * 设置进度监听
     *
     * @param callback 进度回调
     */
    public void setCallback(ProgressCallback callback) {
        mCallback = callback;
    }

    /**
     * 加载进度监听接口
     */
    public interface ProgressCallback {
        void onProgress(int progress);

        void done();
    }
}
