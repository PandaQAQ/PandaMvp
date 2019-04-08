package com.pandaq.appcore.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Window;

import com.pandaq.appcore.R;

import androidx.core.content.ContextCompat;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : ues SettingDialogUtils showSetting() open default style dialog
 */
public class SettingDialogUtils {
    /**
     * 显示设置Dialog
     *
     * @param context     上下文
     * @param title       标题
     * @param msg         提示信息
     * @param positiveStr 确定文字
     * @param negativeStr 取消文字
     * @param isFinish    是否结束当前页面
     * @return AlertDialog
     */
    public static AlertDialog showSetting(final Activity context, CharSequence title,
                                          CharSequence msg, CharSequence positiveStr,
                                          CharSequence negativeStr, final boolean isFinish) {
        ISettingAction setting = RtPermission.permissionSetting(context);
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton(positiveStr, (dialog, which) -> {
                    setting.openSetting();
                })
                .setNegativeButton(negativeStr, (dialog, which) -> {
                    if (isFinish) { //结束当前页面
                        context.finish();
                    }
                })
                .create();
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.AnimationSettingDialog);
        }
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.core_colorNegative));
        return alertDialog;
    }
}
