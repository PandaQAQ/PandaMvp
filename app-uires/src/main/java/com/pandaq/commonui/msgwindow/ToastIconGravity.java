package com.pandaq.commonui.msgwindow;

/**
 * Created by huxinyu on 2018/7/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :带有小 icon 的 Toast，icon 相对文本消息的位置
 */
 public enum ToastIconGravity {

    TOP(0), START(1), BOTTOM(2), END(3);

    private int value;

    ToastIconGravity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
