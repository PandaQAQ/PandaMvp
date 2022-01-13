package com.pandaq.uires.loading;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.pandaq.uires.R;

import java.lang.ref.WeakReference;

/**
 * Created by huxinyu on 2018/8/15.
 * desc :loading
 */
public class LoadingDialogUtil {

    @SuppressLint("StaticFieldLeak")
    private static LoadingDialog sLoadingDialog;
    private static LoadingView sLoadingView;

    /**
     * private
     */
    private LoadingDialogUtil() {
    }

    /**
     * 为 loading dialog 设置加载 logo
     *
     * @param loadingView 加载 logo
     */
    public static void setLoadingView(LoadingView loadingView) {
        sLoadingView = loadingView;
    }

    /**
     * 获取加载 logo
     *
     * @return logoView
     */
    public static LoadingView getLoadingView() {
        return sLoadingView;
    }

    /**
     * 显示加载框
     *
     * @param context 上下文
     */
    public static void show(Activity context, String message) {
        String str = TextUtils.isEmpty(message) ? context.getString(R.string.loading) : message;
        showProgress(context, str, true, true);
    }

    public static void showWithCover(Activity context, String message) {
        String str = TextUtils.isEmpty(message) ? context.getString(R.string.loading) : message;
        showProgress(context, str, true, false);
    }

    /**
     * 显示设置取消的加载框
     *
     * @param context    上下文
     * @param cancelable 是否可以取消
     */
    public static void show(Activity context, String message, boolean cancelable) {
        String str = TextUtils.isEmpty(message) ? context.getString(R.string.loading) : message;
        showProgress(context, str, cancelable, true);
    }

    public static void showWithCover(Activity context, String message, boolean cancelable) {
        String str = TextUtils.isEmpty(message) ? context.getString(R.string.loading) : message;
        showProgress(context, str, cancelable, false);
    }

    /**
     * 显示设置 取消和文本 的加载框
     *
     * @param message    文本
     * @param cancelable 取消
     * @param activity   上下文
     */
    private static void showProgress(Activity activity, CharSequence message, boolean cancelable, boolean isTrans) {
        WeakReference<Activity> reference = new WeakReference<>(activity);
        Activity ownerActivity = reference.get();
        //如果时当前页面正在显示
        if (isShowing() && contextIsSame(sLoadingDialog, ownerActivity)) {
            if (sLoadingDialog != null) {
                sLoadingDialog.setMessage(message);
            }
            return;
        }
        if (!contextIsSame(sLoadingDialog, ownerActivity)) {
            releaseDialog();
        }
        if (sLoadingDialog == null) {
            sLoadingDialog = new LoadingDialog(ownerActivity, isTrans);
        }
        if (sLoadingView != null) {
            sLoadingDialog.setLoadingView(sLoadingView);
        } else {
            sLoadingDialog.hideLoadingView();
        }
        sLoadingDialog.showInit(message, cancelable);
        if (!ownerActivity.isDestroyed() && !ownerActivity.isFinishing()) {
            try {
                Window dialogWindow = sLoadingDialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 560;
                dialogWindow.setAttributes(lp);
                sLoadingDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 加载框是否显示
     *
     * @return true 显示 false 未显示
     */
    public static boolean isShowing() {
        return sLoadingDialog != null && sLoadingDialog.isShowing();
    }

    /**
     * 隐藏加载框,延迟600ms
     */
    public static void hideProgress() {
        try {
            if (sLoadingDialog == null) {
                return;
            }
            LoadingView logoView = sLoadingDialog.getLoadingView();
            if (logoView != null) {
                logoView.finish();
                ((View) logoView).postDelayed(LoadingDialogUtil::dismissDialog, 600);
            } else {
                dismissDialog();
            }


        } catch (Exception e) {
            Log.e("LoadingDialogUtil", "异常" + e.getMessage());
        }

    }

    /**
     * 关闭dialog
     */
    private static void dismissDialog() {
        try {
            releaseDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 直接消失
     */
    public static void hideProgressQuick() {
        if (sLoadingDialog != null) {
            dismissDialog();
        }
    }

    /**
     * 置空，避免引用activity导致内存泄露
     */
    public static void releaseDialog() {
        if (sLoadingDialog != null) {
            Activity ownerActivity = sLoadingDialog.getOwnerActivity();
            if (ownerActivity != null) {
                if (sLoadingDialog.isShowing() && !ownerActivity.isDestroyed()) {
                    sLoadingDialog.dismiss();
                }
            }
            sLoadingDialog.removeLoadingView();
            sLoadingDialog = null;
        }
    }

    /**
     * 是否在同一个页面
     *
     * @param dialog  LoadingDialog
     * @param context 待判断的contxt
     * @return true 同一个页面
     */
    private static boolean contextIsSame(LoadingDialog dialog, Context context) {
        try {
            return dialog.getMyContext().equals(context);
        } catch (Exception e) {
            return false;
        }
    }
}
