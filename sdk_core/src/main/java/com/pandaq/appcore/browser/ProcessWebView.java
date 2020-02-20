package com.pandaq.appcore.browser;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.*;
import android.widget.ProgressBar;

import com.pandaq.appcore.BuildConfig;
import com.pandaq.appcore.R;


/**
 * Created by PandaQ on 2017/6/29.
 * 带进度条的WebView
 */

public class ProcessWebView extends WebView {

    private ProgressBar mProgressBar;

    public ProcessWebView(Context context) {
        this(context, null);
    }

    public ProcessWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.core_webview_process_state, null);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);
        setWebViewClient(new MyWebClient());
        setWebChromeClient(new ChromeClient());
        setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
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

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            post(() -> {
                view.measure(0, 0);
                getLayoutParams().height = view.getMeasuredHeight();
            });

        }
    }

}
