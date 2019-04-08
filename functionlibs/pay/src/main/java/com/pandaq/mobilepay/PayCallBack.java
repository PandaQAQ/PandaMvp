package com.pandaq.mobilepay;

/**
 * Created by huxinyu on 2018/9/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :支付结果回调，根据回调结果去请求服务器查询支付结果。判断标准以服务器查询结果为准
 */
public interface PayCallBack {

    /**
     * 支付成功
     *
     * @param payType 支付类型（微信支付，支付宝）{@link PayType}
     * @param msg     支付成功信息
     */
    void paySuccess(int payType, String msg);

    /**
     * 支付失败
     *
     * @param payType   支付类型（微信支付，支付宝） {@link PayType}
     * @param errorCode 错误码
     * @param msg       错误消息
     */
    void payFail(int payType, int errorCode, String msg);
}
