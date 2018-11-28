package com.pandaq.appcore.utils.system;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by huxinyu on 2018/5/24.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 软键盘弹出关闭工具
 */
public class SoftInputUtil {

    private SoftInputUtil() {

    }

    /**
     * 显示软键盘
     *
     * @param context 上下文
     * @param view    View
     */
    public static void showSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param context 上下文
     * @param view    View
     */
    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 判断软键盘是否弹出
     *
     * @param activity 显示软键盘的 activity
     * @return 是否弹出软键盘
     */
    private boolean isSoftInputShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return screenHeight - rect.bottom != 0;
    }

}
