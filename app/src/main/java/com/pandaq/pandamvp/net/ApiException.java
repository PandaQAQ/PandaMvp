package com.pandaq.pandamvp.net;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :自定义异常类，API 接口异常抛出类
 */
public class ApiException extends RuntimeException {

    /**
     * 错误码
     */
    private long code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 原始数据
     */
    private String data;

    public void ApiException(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public void ApiException(long code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
