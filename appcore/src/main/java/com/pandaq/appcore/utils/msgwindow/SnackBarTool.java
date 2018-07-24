package com.pandaq.appcore.utils.msgwindow;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.NonNull;
import android.support.design.internal.SnackbarContentLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by huxinyu on 2018/7/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :SnackBar的工具封装
 */
public class SnackBarTool {

    private SnackBarTool() {
        throw new RuntimeException("not allowed to do this");
    }

    public static SnackbarBuilder with(View parent) {
        return new SnackbarBuilder(parent);
    }

    public static class SnackbarBuilder {
        private View parentView;

        private int duration = Snackbar.LENGTH_SHORT;

        private CharSequence msg = "Snackbar msg !";
        private int msgColor;
        private int msgFontSize;

        private CharSequence action = "";
        private int actionColor;
        private int actionFontSize;

        private View.OnClickListener actionListener;

        private int backgroundColor;

        private Drawable backgroundDrawable;

        private View customView;

        private SnackbarBuilder() {

        }

        public SnackbarBuilder(@NonNull View parentView) {
            this.parentView = parentView;
        }

        public SnackbarBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public SnackbarBuilder msg(@NonNull CharSequence msg) {
            this.msg = msg;
            return this;
        }

        public SnackbarBuilder msgColor(@ColorInt int color) {
            this.msgColor = color;
            return this;
        }

        public SnackbarBuilder msgFont(@Dimension int msgFont) {
            this.msgFontSize = msgFont;
            return this;
        }

        public SnackbarBuilder action(@NonNull CharSequence action) {
            this.action = action;
            return this;
        }

        public SnackbarBuilder actionColor(@ColorInt int color) {
            this.actionColor = color;
            return this;
        }

        public SnackbarBuilder actionFont(@Dimension int actionFont) {
            this.actionFontSize = actionFont;
            return this;
        }

        public SnackbarBuilder actionListener(@NonNull View.OnClickListener listener) {
            this.actionListener = listener;
            return this;
        }

        /**
         * 与 backgroundColor 会互相覆盖
         *
         * @param color 颜色
         * @return 构造器
         */
        public SnackbarBuilder backgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            return this;
        }

        /**
         * 与 backgroundColor 会互相覆盖
         *
         * @param drawable drawable
         * @return 构造器
         */
        public SnackbarBuilder backgroundDrawable(@NonNull Drawable drawable) {
            this.backgroundDrawable = drawable;
            return this;
        }

        /**
         * 添加自定义 View
         *
         * @param view 自定义 View
         * @return 构造器
         */
        public SnackbarBuilder addCustomView(@NonNull View view) {
            this.customView = view;
            return this;
        }

        public Snackbar show() {
            Snackbar snackbar = Snackbar.make(parentView, msg, duration);
            FrameLayout snackRoot = (FrameLayout) snackbar.getView();
            SnackbarContentLayout snackView = (SnackbarContentLayout) snackRoot.getChildAt(0);
            if (customView != null) {
                snackRoot.setBackgroundColor(Color.TRANSPARENT);
                snackRoot.removeAllViews();
                snackRoot.addView(customView);
                snackbar.show();
                return snackbar;
            }
            AppCompatTextView snackText = (AppCompatTextView) snackView.getChildAt(0);
            AppCompatButton snackAction = (AppCompatButton) snackView.getChildAt(1);
            if (!TextUtils.isEmpty(action)) {
                snackbar.setAction(action, actionListener);
            }

            if (msgColor != 0) {
                snackText.setTextColor(msgColor);
            }
            if (msgFontSize != 0) {
                snackText.setTextSize(msgFontSize);
            }

            if (actionColor != 0) {
                snackbar.setActionTextColor(actionColor);
            }
            if (actionFontSize != 0) {
                snackAction.setTextSize(actionFontSize);
            }

            if (backgroundColor != 0) {
                snackRoot.setBackgroundColor(backgroundColor);
            }
            if (backgroundDrawable != null) {
                snackRoot.setBackground(backgroundDrawable);
            }
            snackbar.show();
            return snackbar;
        }
    }
}
