package com.pandaq.commonui.popupwindows;

/**
 * Created by huxinyu on 2019/3/26.
 * Email : panda.h@foxmail.com
 * Description :列表 popupwindow 的 Item 数据对象
 */
public class ItemData<T> {
    private boolean isChecked;
    private String key; // 列表显示项
    private T value; // 附属值

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
