package com.pandaq.commonui.widget.emptyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pandaq.commonui.R;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by huxinyu on 2019/3/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :公用的数据加载出错或数据为空页面
 */
public class EmptyErrorView extends View {
    enum Mode {
        // show emptyView
        EMPTY,
        // show errorView
        ERROR
    }

    // 默认以空数据 View 显示
    private Mode showMode = Mode.EMPTY;

    private ConstraintLayout mContainer;
    private ImageView mEmptyIcon;
    private TextView mEmptyMsg;

    private void setShowMode(Mode mode) {
        this.showMode = mode;
    }

    public EmptyErrorView(Context context) {
        this(context, Mode.EMPTY);
    }

    public EmptyErrorView(Context context, @NonNull Mode showMode) {
        this(context, null, showMode);
    }

    public EmptyErrorView(Context context, @Nullable AttributeSet attrs, @NonNull Mode showMode) {
        super(context, attrs);
        this.showMode = showMode;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        inflate(getContext(), R.layout.res_empty_view, null);
        mEmptyIcon = findViewById(R.id.iv_icon);
        mEmptyMsg = findViewById(R.id.tv_message);
        mContainer = findViewById(R.id.cl_empty_view);
    }

    /**
     * 当前显示模式
     *
     * @return 当前显示模式 空页面、错误页面
     */
    public Mode getShowMode() {
        return showMode;
    }

    /**
     * 获取 emptyView
     *
     * @return emptyView 根布局
     */
    public ConstraintLayout getContainer() {
        return mContainer;
    }

    /**
     * emptyView 图标
     *
     * @return 图标ImageView
     */
    public ImageView getEmptyIcon() {
        return mEmptyIcon;
    }

    /**
     * 获取 emptyView 消息 TextView
     *
     * @return msg TextView
     */
    public TextView getEmptyMsg() {
        return mEmptyMsg;
    }

    public void setIcon(@DrawableRes int icon) {
        mEmptyIcon.setImageDrawable(getContext().getResources().getDrawable(icon));
    }

    /**
     * 设置界面显示的提示文本
     *
     * @param msgRes 文本内容 res
     */
    public void setMsg(@StringRes int msgRes) {
        mEmptyMsg.setText(msgRes);
    }

    /**
     * 设置界面显示的提示文本
     *
     * @param msg 文本内容
     */
    public void setMsg(String msg) {
        mEmptyMsg.setText(msg);
    }

    /**
     * 设置颜色资源
     *
     * @param color 颜色资源
     */
    public void setTextColorRes(@ColorRes int color) {
        mEmptyMsg.setTextColor(getContext().getResources().getColor(color));
    }

    /**
     * 设置颜色
     *
     * @param color 颜色值
     */
    public void setTextColor(@ColorInt int color) {
        mEmptyMsg.setTextColor(color);
    }

    /**
     * 设置文本字体大小
     * @param textSp 字体大小
     */
    public void setTextSize(float textSp) {
        mEmptyMsg.setTextSize(textSp);
    }

//    /**
//     * 设置文本字体大小
//     * @param textSp 字体大小
//     */
//    public void setTextSize(float textSp,) {
//        mEmptyMsg.setTextSize(textSp);
//    }

    /**
     * 设置当前页面的点击事件
     *
     * @param listener 点击事件回调
     */
    public void setOnPageClickListener(OnClickListener listener) {
        mContainer.setOnClickListener(listener);
    }

    /**
     * icon 点击事件
     *
     * @param listener 点击事件回调
     */
    public void setOnIconClickListener(OnClickListener listener) {
        mEmptyIcon.setOnClickListener(listener);
    }
}