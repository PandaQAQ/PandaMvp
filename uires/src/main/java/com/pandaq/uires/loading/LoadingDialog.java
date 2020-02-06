package com.pandaq.uires.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pandaq.uires.R;


/**
 * <p>
 * desc :Loading 弹窗 View
 */

public class LoadingDialog extends Dialog {

    private TextView textView;
    private View rootView;
    private Context mContext;
    private LoadingView mLoadingView;
    private LinearLayout mLoadingLayout;

    /**
     * 构造器
     *
     * @param context 上下文
     */
    public LoadingDialog(@NonNull Activity context) {
        super(context, R.style.loading_dialog);
        this.mContext = context;
        setOwnerActivity(context);
        rootView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        textView = rootView.findViewById(R.id.tv_msg);
        mLoadingLayout = rootView.findViewById(R.id.fl_main);
    }


    /**
     * 弹出Dialog
     *
     * @param message    文本信息
     * @param cancelable 是可以点击返回键
     * @param listener   取消监听
     * @return Dialog
     */
    public Dialog show(CharSequence message, boolean cancelable, OnCancelListener listener) {
        textView.setText(message);
        setCancelable(cancelable);
        setCanceledOnTouchOutside(cancelable);
        setOnCancelListener(listener);
        setContentView(rootView);
        return this;
    }

    /**
     * @param message 显示文本信息
     */
    public void setMessage(CharSequence message) {
        textView.setText(message);
        textView.invalidate();
    }

    public Context getMyContext() {
        return mContext;
    }

    public void setLoadingView(@Nullable LoadingView loadingView) {
        mLoadingLayout.setVisibility(View.VISIBLE);
        if (loadingView instanceof View) {
            if (mLoadingView != loadingView) {
                mLoadingLayout.addView((View) loadingView);
            } else {
                mLoadingLayout.removeView((View) mLoadingView);
                mLoadingLayout.addView((View) loadingView);
            }
            mLoadingView = loadingView;
        } else {
            throw new RuntimeException("loadingView must extends View");
        }
    }

    public void removeLoadingView() {
        mLoadingLayout.removeView((View) mLoadingView);
    }

    public LoadingView getLoadingView() {
        return mLoadingView;
    }

    public void hideLodingView() {
        mLoadingLayout.setVisibility(View.GONE);
    }
}
