package com.pandaq.uires.widget.emptyview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pandaq.uires.R;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * Created by huxinyu on 2019/3/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :公共的数据状态页面
 */
public class StateLayout extends LinearLayout {
    public enum Mode {
        // showInit emptyView
        EMPTY,
        // showInit errorView
        ERROR,
        // showInit noInterNet
        NO_INTERNET,
        // showInit normal
        NORMAL
    }

    // 默认以空数据 View 显示
    private Mode showMode = Mode.EMPTY;
    private LinearLayout mContainer;
    private ImageView mEmptyIcon;
    private TextView mEmptyMsg;

    public StateLayout(Context context) {
        this(context, Mode.EMPTY);
    }

    public StateLayout(Context context, @NonNull Mode showMode) {
        this(context, null, showMode);
    }

    public StateLayout(Context context, @Nullable AttributeSet attrs, @NonNull Mode showMode) {
        super(context, attrs);
        this.showMode = showMode;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        inflate(getContext(), R.layout.res_empty_view, this);
        mEmptyIcon = this.findViewById(R.id.iv_icon);
        mEmptyMsg = this.findViewById(R.id.tv_message);
        mContainer = this.findViewById(R.id.cl_empty_view);
        mEmptyMsg.setTextSize(EmptyConfig.get().getMsgSize());
        resetByMode();
    }

    public void setShowMode(Mode mode) {
        this.showMode = mode;
        resetByMode();
    }

    private void resetByMode() {
        switch (this.showMode) {
            case EMPTY:
                mEmptyIcon.setImageResource(EmptyConfig.get().getEmptyIconRes());
                mEmptyMsg.setTextColor(getResources().getColor(EmptyConfig.get().getEmptyMsgColor()));
                mEmptyMsg.setText(EmptyConfig.get().getEmptyMsg());
                setVisibility(View.VISIBLE);
                break;
            case ERROR:
                mEmptyIcon.setImageResource(EmptyConfig.get().getErrorIconRes());
                mEmptyMsg.setTextColor(getResources().getColor(EmptyConfig.get().getErrorMsgColor()));
                mEmptyMsg.setText(EmptyConfig.get().getErrorMsg());
                setVisibility(View.VISIBLE);
                break;
            case NO_INTERNET:
                mEmptyIcon.setImageResource(EmptyConfig.get().getNetErrorIconRes());
                mEmptyMsg.setTextColor(getResources().getColor(EmptyConfig.get().getNetErrorMsgColor()));
                mEmptyMsg.setText(EmptyConfig.get().getNetErrorMsg());
                setVisibility(View.VISIBLE);
                break;
            case NORMAL:
                setVisibility(View.GONE);
                break;
        }
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
    public LinearLayout getContainer() {
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


    /**
     * 设置错误的 Icon
     *
     * @param icon
     */
    public void setEmptyIcon(@DrawableRes int icon) {
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
     *
     * @param textSp 字体大小
     */
    public void setTextSize(float textSp) {
        mEmptyMsg.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSp);
    }

    /**
     * 设置文本字体大小
     *
     * @param textSp 字体大小
     */
    public void setTextSize(float textSp, int unit) {
        mEmptyMsg.setTextSize(unit, textSp);
    }

    /**
     * icon 点击事件
     *
     * @param listener 点击事件回调
     */
    public void setOnIconClickListener(OnStateClickListener listener) {
        setOnClickListener(v -> listener.onClick(showMode));
    }

}
