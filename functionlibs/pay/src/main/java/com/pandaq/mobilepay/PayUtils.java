package com.pandaq.mobilepay;

import android.app.Activity;
import android.content.Context;

import com.pandaq.mobilepay.alipay.AliPay;
import com.pandaq.mobilepay.alipay.AliPayData;
import com.pandaq.mobilepay.wechatpay.WeChatPay;
import com.pandaq.mobilepay.wechatpay.WeChatPayData;

import androidx.annotation.NonNull;


/**
 * Created by huxinyu on 2018/7/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :微信支付/支付宝支付，统一调起工具类
 */
public class PayUtils {

    public static String WECHAT_PAY_APP_ID = "";
    public static PayCallBack sPayCallBack;

    /**
     * 支付宝付款
     *
     * @param activity 发起支付页面 Activity
     * @param payInfo  支付宝支付信息
     */
    public static void pay(@NonNull Activity activity, @NonNull AliPayData payInfo, @NonNull PayCallBack callBack) {
        sPayCallBack = callBack;
        new AliPay(activity, payInfo).start();
    }

    /**
     * 微信支付付款
     *
     * @param context 上下文
     * @param payInfo 微信支付信息
     */
    public static void pay(@NonNull Context context, @NonNull WeChatPayData payInfo, @NonNull PayCallBack callBack) {
        WECHAT_PAY_APP_ID = payInfo.getAppid();
        sPayCallBack = callBack;
        new WeChatPay(context, payInfo).start();
    }
}
