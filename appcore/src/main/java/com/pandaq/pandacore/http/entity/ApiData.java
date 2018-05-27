package com.pandaq.pandacore.http.entity;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :服务器数据结构框架类
 * <p>
 * service api data recommend：
 * <p>
 * {
 * code:0,
 * message:message,
 * data:data Object or data Array
 */
public class ApiData<T> {

    /**
     * 接口请求返回码
     */
    private int code;
    /**
     * 消息，可为空
     */
    private String msg;
    /**
     * 数据 data 可为空
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
