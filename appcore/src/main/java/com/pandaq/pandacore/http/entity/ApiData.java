package com.pandaq.pandacore.http.entity;

import com.pandaq.pandacore.HttpCodes;

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
 * }
 */
public class ApiData<T> {

    /**
     * 接口请求返回码
     */
    private Integer code;
    /**
     * 消息，可为空
     */
    private String msg;
    /**
     * 数据 data 可为空
     */
    private T data;

    public Integer getCode() {
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

    /**
     * 判断服务器响应码是否为接口成功响应码
     *
     * @return 判断结果
     */
    public boolean isSuccess() {
        return this.code == HttpCodes.API.SUCCESS;
    }
}
