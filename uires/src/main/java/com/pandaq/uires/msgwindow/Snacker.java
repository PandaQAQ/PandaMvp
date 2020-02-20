package com.pandaq.uires.msgwindow;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.pandaq.uires.configs.SnackerConfig;

/**
 * Created by huxinyu on 2018/7/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :SnackBar的工具封装
 */
public class Snacker {

    private Snacker() {
        throw new RuntimeException("not allowed to do this");
    }

    public static SnackerBuilder with(View parent) {
        return new SnackerBuilder(parent);
    }

    public static class SnackerBuilder {
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

        private SnackerBuilder() {

        }

        private SnackerBuilder(@NonNull View parentView) {
            this.parentView = parentView;
        }

        public SnackerBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public SnackerBuilder msg(@NonNull CharSequence msg) {
            this.msg = msg;
            return this;
        }

        public SnackerBuilder msgColor(@ColorInt int color) {
            this.msgColor = color;
            return this;
        }

        public SnackerBuilder msgFont(@Dimension int msgFont) {
            this.msgFontSize = msgFont;
            return this;
        }

        public SnackerBuilder action(@NonNull CharSequence action) {
            this.action = action;
            return this;
        }

        public SnackerBuilder actionColor(@ColorInt int color) {
            this.actionColor = color;
            return this;
        }

        public SnackerBuilder actionFont(@Dimension int actionFont) {
            this.actionFontSize = actionFont;
            return this;
        }

        public SnackerBuilder actionListener(@NonNull View.OnClickListener listener) {
            this.actionListener = listener;
            return this;
        }

        /**
         * 与 backgroundColor 会互相覆盖
         *
         * @param color 颜色
         * @return 构造器
         */
        public SnackerBuilder backgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            return this;
        }

        /**
         * 与 backgroundColor 会互相覆盖
         *
         * @param drawable drawable
         * @return 构造器
         */
        public SnackerBuilder backgroundDrawable(@NonNull Drawable drawable) {
            this.backgroundDrawable = drawable;
            return this;
        }

        /**
         * 添加自定义 View
         *
         * @param view 自定义 View
         * @return 构造器
         */
        public SnackerBuilder addCustomView(@NonNull View view) {
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
            } else {
                if (SnackerConfig.Companion.get().getMsgColor() != SnackerConfig.COLOR_NULL) {
                    snackText.setTextColor(SnackerConfig.Companion.get().getMsgColor());
                }
            }

            if (msgFontSize != 0) {
                snackText.setTextSize(msgFontSize);
            }

            if (actionColor != 0) {
                snackbar.setActionTextColor(actionColor);
            } else {
                if (SnackerConfig.Companion.get().getActionColor() != SnackerConfig.COLOR_NULL) {
                    snackbar.setActionTextColor(SnackerConfig.Companion.get().getActionColor());
                }
            }
            if (actionFontSize != 0) {
                snackAction.setTextSize(actionFontSize);
            }

            if (backgroundColor != 0) {
                snackRoot.setBackgroundColor(backgroundColor);
            } else {
                if (SnackerConfig.Companion.get().getBackgroundColor() != SnackerConfig.COLOR_NULL) {
                    snackRoot.setBackgroundColor(SnackerConfig.Companion.get().getBackgroundColor());
                }
            }
            if (backgroundDrawable != null) {
                snackRoot.setBackground(backgroundDrawable);
            }
            snackbar.show();
            return snackbar;
        }
    }
}
