package com.pandaq.appcore.webview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.pandaq.appcore.R;

/**
 * Created by huxinyu on 2018/6/7.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class ProcessWebView extends WebView {

    private ProgressBar mProgressBar;

    public ProcessWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.webview_process_state);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);
        setWebViewClient(new MyWebClient());
        setWebChromeClient(new ChromeClient());
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE) {
                    mProgressBar.setVisibility(VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        }
    }

    private class MyWebClient extends WebViewClient {

        // 5.0 之上拦截请求
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // 5.0 之下拦截请求
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
