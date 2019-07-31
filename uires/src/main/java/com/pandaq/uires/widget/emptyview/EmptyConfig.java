package com.pandaq.uires.widget.emptyview;

import com.pandaq.uires.R;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by huxinyu on 2019/3/21.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :公用emptyView 和公用的errorView 配置界面
 */
public class EmptyConfig {

    @DrawableRes
    public static int DEFAULT_ERROR_ICON = R.drawable.icon_default_error;
    @DrawableRes
    public static int DEFAULT_EMPTY_ICON = R.drawable.icon_default_error;

    public static float DEFAULT_MSG_SIZE = 14f;// size of msg text, typedValue is sp
    @ColorRes
    public static int ERROR_MSG_COLOR = R.color.res_colorTextError;
    @ColorRes
    public static int EMPTY_MSG_COLOR = R.color.res_colorTextEmpty;
    @StringRes
    public static int EMPTY_MSG = R.string.res_text_empty;
    @StringRes
    public static int ERROR_MSG = R.string.res_text_net_error;
}
