package com.pandaq.uires.msgwindow;

/**
 * Created by huxinyu on 2018/12/3.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :default config for Snacker & Toaster
 */
public class SnackerConfig {

    public static int COLOR_NULL = -1;
    private static SnackerConfig sDefault;
    private int msgColor = COLOR_NULL;
    private int actionColor = COLOR_NULL;
    private int backgroundColor = COLOR_NULL;

    public static SnackerConfig getDefault() {
        if (sDefault == null) {
            synchronized (SnackerConfig.class) {
                sDefault = new SnackerConfig();
            }
        }
        return sDefault;
    }

    public int getMsgColor() {
        return msgColor;
    }

    public SnackerConfig setMsgColor(int msgColor) {
        this.msgColor = msgColor;
        return this;
    }

    public int getActionColor() {
        return actionColor;
    }

    public SnackerConfig setActionColor(int actionColor) {
        this.actionColor = actionColor;
        return this;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public SnackerConfig setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
}
