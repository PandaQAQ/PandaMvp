package com.pandaq.uires.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pandaq.uires.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * <p>
 * desc :Loading 弹窗 View
 */

public class LoadingDialog extends Dialog {

    /**
     * 显示 tag ，用于标识是否由同一个页面控制显示
     */
    private String showTag;
    private final TextView textView;
    private final View rootView;
    private final Context mContext;
    private LoadingView mLoadingView;
    private final LinearLayout mLoadingLayout;

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

    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    public String getShowTag() {
        return showTag;
    }

    /**
     * 弹出Dialog
     *
     * @param message    文本信息
     * @param cancelable 是可以点击返回键
     * @return Dialog
     */
    public void showInit(CharSequence message, boolean cancelable) {
        textView.setText(message);
        setCancelable(cancelable);
        setCanceledOnTouchOutside(cancelable);
        setContentView(rootView);
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
            if (mLoadingView == loadingView) {
                mLoadingLayout.removeView((View) mLoadingView);
            }
            mLoadingLayout.addView((View) loadingView);
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

    public void hideLoadingView() {
        mLoadingLayout.setVisibility(View.GONE);
    }

    public void setStyle(boolean isTrans) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        if (isTrans) {
            lp.dimAmount = 0.0f;
        } else {
            lp.dimAmount = 0.3f;
        }
        lp.y = 560;
        dialogWindow.setAttributes(lp);
        dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
