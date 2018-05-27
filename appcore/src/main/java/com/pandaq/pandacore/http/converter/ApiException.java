package com.pandaq.pandacore.http.converter;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :与服务端约定的接口数据异常抛出如 token 失效，未登录等异常
 */
public class ApiException extends RuntimeException {

    private int errorCode;
    private String message;

}
