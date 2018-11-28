package com.pandaq.appcore.http.converter;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :与服务端约定的接口数据异常抛出如 token 失效，未登录等异常
 */
public class ApiException extends RuntimeException {

    private long errorCode;
    private String message;
    private String data;

    private ApiException() {
    }

    public ApiException(long errorCode, String message, String data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
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
