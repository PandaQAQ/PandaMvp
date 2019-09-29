package com.pandaq.uires.msgwindow;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pandaq.appcore.utils.system.AppUtils;
import com.pandaq.appcore.utils.system.DisplayUtils;
import com.pandaq.uires.configs.ToastConfig;


/**
 * Created by huxinyu on 2018/5/21.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : Toast 工具类,封装 Toast 的显示使用
 */
public class Toaster {

    private static Toast mToast = null;//全局唯一的Toast,避免多次弹
    private static final String ICON_TAG = "ToastIconTag";
    private static final String TEXT_TAG = "ToastTextTag";

    private static final int SUCCESS = 0;
    private static final int WARNING = 1;
    private static final int ERROR = 2;

    private Toaster() {
        throw new RuntimeException("not allowed to do this");
    }

    /**
     * 取消Toast显示
     */
    private static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static ToastBuilder msg(String msg) {
        if (mToast != null) {
            cancelToast();
            mToast = null;
        }
        return new ToastBuilder(msg);
    }

    public static class ToastBuilder {
        private CharSequence msg;
        private int msgFont;
        private int msgColor;
        private Drawable backgroundDrawable;
        private Drawable icon;
        private int iconSize = DisplayUtils.dp2px(24);
        private int mIconGravity = ToastConfig.Companion.get().getIconGravity();
        private int duration = Toast.LENGTH_SHORT;

        private ToastBuilder(String msg) {
            this.msg = msg;
        }

        public ToastBuilder msgFont(@Dimension int msgFont) {
            this.msgFont = msgFont;
            return this;
        }

        public ToastBuilder backgroundDrawable(@NonNull Drawable drawable) {
            this.backgroundDrawable = drawable;
            return this;
        }

        public ToastBuilder icon(@NonNull Drawable icon, int gravity) {
            this.icon = icon;
            this.mIconGravity = gravity;
            return this;
        }

        public ToastBuilder icon(@DrawableRes int icon, int gravity) {
            this.icon = AppUtils.getResource().getDrawable(icon);
            this.mIconGravity = gravity;
            return this;
        }

        public ToastBuilder iconSize(int sizePx) {
            this.iconSize = sizePx;
            return this;
        }

        /**
         * 可使用 Toast.LENGTH_LONG Toast.LENGTH_SHORT.其他值时显示时长为对应毫秒数
         *
         * @param duration 显示时长
         * @return 构造器
         */
        public ToastBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public void showSuccess() {
            show(SUCCESS);
        }

        public void showError() {
            show(ERROR);
        }

        public void showWarning() {
            show(WARNING);
        }

        @SuppressLint("ShowToast")
        private void show(int state) {
            if (TextUtils.isEmpty(msg)) {
                return;
            }
            switch (state) {
                case SUCCESS:
                    msgColor = ToastConfig.Companion.get().getNormalTextColor();
                    if (backgroundDrawable == null) {
                        backgroundDrawable = ToastConfig.Companion.get().getNormalBackground();
                    }
                    if (icon == null) {
                        icon = ToastConfig.Companion.get().getNormalIcon();
                    }
                    break;
                case WARNING:
                    msgColor = ToastConfig.Companion.get().getWarningTextColor();
                    if (backgroundDrawable == null) {
                        backgroundDrawable = ToastConfig.Companion.get().getWarningBackground();
                    }
                    if (icon == null) {
                        icon = ToastConfig.Companion.get().getWarningIcon();
                    }
                    break;
                case ERROR:
                    msgColor = ToastConfig.Companion.get().getErrorTextColor();
                    if (backgroundDrawable == null) {
                        backgroundDrawable = ToastConfig.Companion.get().getErrorBackground();
                    }
                    if (icon == null) {
                        icon = ToastConfig.Companion.get().getErrorIcon();
                    }
                    break;
            }
            if (mToast == null) {
                mToast = Toast.makeText(AppUtils.getContext(), msg, duration);
                // 新建时为显示 ToastMsg 的 textview 设置 tag
                LinearLayout toastView = (LinearLayout) mToast.getView();
                toastView.getChildAt(0).setTag(TEXT_TAG);
            }
            TextView textView = mToast.getView().findViewWithTag(TEXT_TAG);
            if (textView != null) {
                if (msgColor != 0) {
                    textView.setTextColor(msgColor);
                }
                if (msgFont != 0) {
                    textView.setTextSize(msgFont);
                }
            }
            if (backgroundDrawable != null) {
                mToast.getView().setBackground(backgroundDrawable);
            }
            if (icon != null) {
                ImageView iconView = mToast.getView().findViewWithTag(ICON_TAG);
                LinearLayout toastView = (LinearLayout) mToast.getView();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                toastView.setLayoutParams(params);
                if (iconView != null) { //先将之前的 icon 移除
                    toastView.removeView(iconView);
                }
                LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(iconSize, iconSize);
                iconView = new ImageView(AppUtils.getContext());
                iconView.setLayoutParams(iconParams);
                iconView.setImageDrawable(icon);
                if (mIconGravity == Gravity.START) {
                    iconParams.rightMargin = DisplayUtils.dp2px(8);
                    toastView.setOrientation(LinearLayout.HORIZONTAL);
                    toastView.addView(iconView, 0);
                } else if (mIconGravity == Gravity.END) {
                    iconParams.leftMargin = DisplayUtils.dp2px(8);
                    toastView.setOrientation(LinearLayout.HORIZONTAL);
                    toastView.addView(iconView, toastView.getChildCount());
                } else if (mIconGravity == Gravity.TOP) {
                    iconParams.bottomMargin = DisplayUtils.dp2px(8);
                    iconParams.topMargin = DisplayUtils.dp2px(8);
                    toastView.setOrientation(LinearLayout.VERTICAL);
                    toastView.addView(iconView, 0);
                } else if (mIconGravity == Gravity.BOTTOM) {
                    iconParams.bottomMargin = DisplayUtils.dp2px(8);
                    iconParams.topMargin = DisplayUtils.dp2px(8);
                    toastView.setOrientation(LinearLayout.VERTICAL);
                    toastView.addView(iconView, toastView.getChildCount());
                }
            }

            mToast.setDuration(duration);
            mToast.show();
        }
    }
}
