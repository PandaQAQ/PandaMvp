package com.pandaq.uires.loading;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.pandaq.uires.R;

import java.lang.ref.WeakReference;

import androidx.fragment.app.Fragment;

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
     * @param activity 上下文
     */
    public static void show(Activity activity, String message) {
        String str = TextUtils.isEmpty(message) ? activity.getString(R.string.loading) : message;
        showProgress(activity, str, true, true);
    }

    public static void showWithCover(Activity activity, String message) {
        String str = TextUtils.isEmpty(message) ? activity.getString(R.string.loading) : message;
        showProgress(activity, str, true, false);
    }

    public static void show(Fragment fragment, String message) {
        String str = TextUtils.isEmpty(message) ? fragment.getString(R.string.loading) : message;
        showProgress(fragment, str, true, true);
    }

    public static void showWithCover(Fragment fragment, String message) {
        String str = TextUtils.isEmpty(message) ? fragment.getString(R.string.loading) : message;
        showProgress(fragment, str, true, false);
    }

    /**
     * 显示设置取消的加载框
     *
     * @param activity   上下文
     * @param cancelable 是否可以取消
     */
    public static void show(Activity activity, String message, boolean cancelable) {
        String str = TextUtils.isEmpty(message) ? activity.getString(R.string.loading) : message;
        showProgress(activity, str, cancelable, true);
    }

    public static void showWithCover(Activity activity, String message, boolean cancelable) {
        String str = TextUtils.isEmpty(message) ? activity.getString(R.string.loading) : message;
        showProgress(activity, str, cancelable, false);
    }

    /**
     * 显示设置取消的加载框
     *
     * @param fragment   上下文
     * @param cancelable 是否可以取消
     */
    public static void show(Fragment fragment, String message, boolean cancelable) {
        String str = TextUtils.isEmpty(message) ? fragment.getString(R.string.loading) : message;
        showProgress(fragment, str, cancelable, true);
    }

    public static void showWithCover(Fragment fragment, String message, boolean cancelable) {
        String str = TextUtils.isEmpty(message) ? fragment.getString(R.string.loading) : message;
        showProgress(fragment, str, cancelable, false);
    }

    private static void showProgress(Fragment fragment, CharSequence message, boolean cancelable, boolean isTrans) {
        showProgress(fragment.getActivity(), fragment.getClass().getSimpleName(), message, cancelable, isTrans);
    }

    private static void showProgress(Activity activity, CharSequence message, boolean cancelable, boolean isTrans) {
        showProgress(activity, activity.getClass().getSimpleName(), message, cancelable, isTrans);
    }

    /**
     * 显示设置 取消和文本 的加载框
     *
     * @param message    文本
     * @param cancelable 取消
     * @param activity   上下文
     */
    private static void showProgress(Activity activity, String showTag, CharSequence message, boolean cancelable, boolean isTrans) {
        WeakReference<Activity> reference = new WeakReference<>(activity);
        Activity ownerActivity = reference.get();
        //如果时当前页面正在显示
        if (isShowing()) {
            if (sLoadingDialog != null) {
                sLoadingDialog.setMessage(message);
                sLoadingDialog.setStyle(isTrans);
                sLoadingDialog.setShowTag(showTag);
            }
            return;
        }
        if (!isSame(showTag)) {
            releaseDialog();
        }
        if (sLoadingDialog == null) {
            sLoadingDialog = new LoadingDialog(ownerActivity);
            sLoadingDialog.setShowTag(showTag);
        }
        if (sLoadingView != null) {
            sLoadingDialog.setLoadingView(sLoadingView);
        } else {
            sLoadingDialog.hideLoadingView();
        }
        sLoadingDialog.showInit(message, cancelable);
        if (!ownerActivity.isDestroyed() && !ownerActivity.isFinishing()) {
            try {
                sLoadingDialog.setStyle(isTrans);
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
     * 直接消失
     */
    public static void hideProgressQuick() {
        if (sLoadingDialog != null) {
            dismissDialog();
        }
    }

    /**
     * 隐藏加载框,延迟600ms
     */
    public static void hideProgress(String showTag) {
        try {
            if (sLoadingDialog == null || !isSame(showTag)) {
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
     * 直接消失
     */
    public static void hideProgressQuick(String showTag) {
        if (sLoadingDialog != null && isSame(showTag)) {
            dismissDialog();
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
     * 置空，避免引用activity导致内存泄露
     */
    private static void releaseDialog() {
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
     * @param showTag 待判断的 showTag
     * @return true 同一个页面
     */
    private static boolean isSame(String showTag) {
        if (sLoadingDialog == null) {
            return false;
        }
        return sLoadingDialog.getShowTag().equals(showTag);
    }
}
