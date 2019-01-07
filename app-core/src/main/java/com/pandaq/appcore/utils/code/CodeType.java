package com.pandaq.appcore.utils.code;

/**
 * Created by huxinyu on 2018/7/6.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :加密方式类型
 */
public enum CodeType {
    SHA("SHA-1"),
    MD5("MD5"),
    DES("DES");

    private String type;

    CodeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
