package com.pandaq.uires.loading;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import com.pandaq.uires.R;

import java.lang.ref.WeakReference;

/**
 * Created by reiserx on 2017/8/15.
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
    public static void show(Activity context) {
        showProgress(context.getString(R.string.loading), true, context);
    }

    /**
     * 显示设置取消的加载框
     *
     * @param context    上下文
     * @param cancelable 是否可以取消
     */
    public static void show(Activity context, boolean cancelable) {
        showProgress(context.getString(R.string.loading), cancelable, context);
    }

    /**
     * 显示设置message的加载框
     *
     * @param context 上下文
     * @param message 文本内容
     */
    public static void show(Activity context, CharSequence message) {
        showProgress(message, true, context);
    }

    /**
     * 显示设置 取消和文本 的加载框
     *
     * @param context    上下文
     * @param message    文本
     * @param cancelable 取消
     */
    public static void show(Activity context, CharSequence message, boolean cancelable) {
        showProgress(message, cancelable, context);
    }

    /**
     * 显示设置 取消和文本 的加载框
     *
     * @param message    文本
     * @param cancelable 取消
     * @param context    上下文
     */
    public static void showProgress(CharSequence message, boolean cancelable, Activity context) {
        showProgress(message, cancelable, context, null);
    }


    /**
     * 显示设置 取消和文本 的加载框
     *
     * @param message    文本
     * @param cancelable 取消
     * @param activity   上下文
     */
    public static void showProgress(CharSequence message, boolean cancelable, Activity activity, DialogInterface.OnCancelListener listener) {
        WeakReference<Activity> reference = new WeakReference<>(activity);
        Activity context = reference.get();
        //如果时当前页面正在显示
        if (isShowing() && contextIsSame(sLoadingDialog, context)) {
            if (sLoadingDialog != null) {
                sLoadingDialog.setMessage(message);
            }
            return;
        }
        if (!contextIsSame(sLoadingDialog, context)) {
            releaseDialog();
        }
        if (sLoadingDialog == null) {
            sLoadingDialog = new LoadingDialog(context);
        }
        if (sLoadingView != null) {
            sLoadingDialog.setLoadingView(sLoadingView);
        }else {
            sLoadingDialog.hideLodingView();
        }
        sLoadingDialog.show(message, cancelable, listener);
        if (!context.isFinishing()) {
            try {
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
                ((View) logoView).postDelayed(LoadingDialogUtil::dissmissDialog, 600);
            } else {
                dissmissDialog();
            }


        } catch (Exception e) {
            Log.e("LoadingDialogUtil", "异常" + e.getMessage());
        }

    }

    /**
     * 隐藏加载框
     *
     * @param success 是否成功
     */
    public static void hideProgress(boolean success) {
        try {
            if (sLoadingDialog == null) {
                return;
            }
            LoadingView logoView = sLoadingDialog.getLoadingView();
            if (logoView != null) {
                if (success) {
                    logoView.finish();
                } else {
                    logoView.finish();
                }
                dissmissDialog();
            } else {
                dissmissDialog();
            }
        } catch (Exception e) {
            Log.e("LoadingDialogUtil", "异常" + e.getMessage());
        }

    }


    /**
     * 关闭dialog
     */
    private static void dissmissDialog() {
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
            dissmissDialog();
        }
    }

    /**
     * 置空，避免引用activity导致内存泄露
     */
    public static void releaseDialog() {

        if (sLoadingDialog != null) {
            Activity ownerActivity = sLoadingDialog.getOwnerActivity();
            if (ownerActivity!=null){
                if (sLoadingDialog.isShowing()&&!ownerActivity.isDestroyed()) {
                    sLoadingDialog.dismiss();
                }
                sLoadingDialog.removeLoadingView();
                sLoadingDialog = null;
            }
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
