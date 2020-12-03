package com.pandaq.uires.widget.emptyview;


import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.pandaq.uires.R;

/**
 * Created by huxinyu on 2019/3/21.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :公用emptyView 和公用的errorView 配置界面
 */
public class EmptyConfig {

    private static EmptyConfig sEmptyConfig;

    public static EmptyConfig get() {
        if (sEmptyConfig == null) {
            sEmptyConfig = new EmptyConfig();
        }
        return sEmptyConfig;
    }

    @DrawableRes
    private int errorIconRes = R.drawable.icon_default_error;
    @DrawableRes
    private int emptyIconRes = R.drawable.res_icon_empty;
    @DrawableRes
    private int netErrorIconRes = R.drawable.icon_default_disable_internet;

    @ColorRes
    private int errorMsgColor = R.color.res_colorTextError;
    @ColorRes
    private int netErrorMsgColor = R.color.res_colorTextError;
    @ColorRes
    private int emptyMsgColor = R.color.res_colorTextEmpty;
    @StringRes
    private int emptyMsg = R.string.res_text_empty;
    @StringRes
    private int errorMsg = R.string.res_text_error;
    @StringRes
    private int netErrorMsg = R.string.res_text_net_error;

    private float msgSize = 14f;// size of msg text, typedValue is sp

    public int getErrorIconRes() {
        return errorIconRes;
    }

    public void setErrorIconRes(int errorIconRes) {
        this.errorIconRes = errorIconRes;
    }

    public int getEmptyIconRes() {
        return emptyIconRes;
    }

    public void setEmptyIconRes(int emptyIconRes) {
        this.emptyIconRes = emptyIconRes;
    }

    public float getMsgSize() {
        return msgSize;
    }

    public void setMsgSize(float msgSize) {
        this.msgSize = msgSize;
    }

    public int getErrorMsgColor() {
        return errorMsgColor;
    }

    public void setErrorMsgColor(int errorMsgColor) {
        this.errorMsgColor = errorMsgColor;
    }

    public int getEmptyMsgColor() {
        return emptyMsgColor;
    }

    public void setEmptyMsgColor(int emptyMsgColor) {
        this.emptyMsgColor = emptyMsgColor;
    }

    public int getEmptyMsg() {
        return emptyMsg;
    }

    public void setEmptyMsg(int emptyMsg) {
        this.emptyMsg = emptyMsg;
    }

    public int getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(int errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getNetErrorIconRes() {
        return netErrorIconRes;
    }

    public void setNetErrorIconRes(int netErrorIconRes) {
        this.netErrorIconRes = netErrorIconRes;
    }

    public int getNetErrorMsg() {
        return netErrorMsg;
    }

    public void setNetErrorMsg(int netErrorMsg) {
        this.netErrorMsg = netErrorMsg;
    }

    public int getNetErrorMsgColor() {
        return netErrorMsgColor;
    }

    public void setNetErrorMsgColor(int netErrorMsgColor) {
        this.netErrorMsgColor = netErrorMsgColor;
    }
}
