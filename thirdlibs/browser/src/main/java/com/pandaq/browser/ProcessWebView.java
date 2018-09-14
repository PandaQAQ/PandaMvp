package com.pandaq.browser;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.pandaq.browser.client.BaseChromeClient;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by huxinyu on 2018/6/7.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :基于腾讯 X5 web 顶部带打开进度条的 WebView
 */
public class ProcessWebView extends WebView {

    private ProgressBar mProgressBar;
    private BaseChromeClient mChromeClient;

    public ProcessWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mChromeClient = new BaseChromeClient();
        initClient();
        init(context.getApplicationContext());
    }

    private void initClient() {
        mChromeClient.setCallback(new BaseChromeClient.ProgressCallback() {
            @Override
            public void onProgress(int progress) {
                if (mProgressBar.getVisibility() != View.VISIBLE) {
                    mProgressBar.setVisibility(VISIBLE);
                    mProgressBar.setProgress(progress);
                }
            }

            @Override
            public void done() {
                mProgressBar.setVisibility(GONE);
            }
        });
    }

    private void init(Context context) {
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10));
        Drawable drawable = context.getResources().getDrawable(R.drawable.webview_process_state);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);
        setWebChromeClient(new BaseChromeClient());
    }
}
