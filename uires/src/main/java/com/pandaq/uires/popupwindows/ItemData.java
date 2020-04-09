package com.pandaq.uires.popupwindows;

/**
 * Created by huxinyu on 2019/3/26.
 * Email : panda.h@foxmail.com
 * Description :列表 popupwindow 的 Item 数据对象
 */
public abstract class ItemData {
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public abstract String getTitleStr();

    public abstract int getDrawableRes();
}
