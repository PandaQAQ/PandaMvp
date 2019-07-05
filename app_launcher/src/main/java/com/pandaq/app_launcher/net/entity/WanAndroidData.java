package com.pandaq.app_launcher.net.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :服务器数据结构框架类
 * <p>
 * service api data recommend：
 * <p>
 * *{
 * *    "code":0,
 * *    "message":"message",
 * *    "data":data Object or data Array
 * *}
 */
public class WanAndroidData<T> {

    /**
     * 接口请求返回码
     */
    @SerializedName("errorCode")
    private Long code;
    /**
     * 消息，可为空
     */
    @SerializedName("errorMsg")
    private String msg;
    /**
     * 数据 data 可为空
     */
    private T data;

    public Long getCode() {
        return code;
    }

    public void setCode(long code) {
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

    /**
     * 判断服务器响应码是否为接口成功响应码
     *
     * @return 判断结果
     */
    public boolean isSuccess() {
        return this.code == 0L;
    }
}
