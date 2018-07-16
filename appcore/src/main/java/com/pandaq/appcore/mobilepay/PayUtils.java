package com.pandaq.appcore.mobilepay;

import android.app.Activity;
import android.content.Context;

import com.pandaq.appcore.mobilepay.alipay.AliPay;
import com.pandaq.appcore.mobilepay.alipay.AliPayData;
import com.pandaq.appcore.mobilepay.wechatpay.WeChatPay;
import com.pandaq.appcore.mobilepay.wechatpay.WeChatPayData;

/**
 * Created by huxinyu on 2018/7/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :微信支付/支付宝支付，统一调起工具类
 */
public class PayUtils {

    public static String CALL_BACK_ACTIVITY_NAME;
    public static String WECHAT_PAY_APP_ID = "";

    /**
     * 支付宝支付
     *
     * @param activity   发起支付页面 Activity
     * @param respTarget 支付结果通过 eventbus 通知，接收类的 className
     * @param payInfo    支付宝支付信息
     */
    public static void pay(Activity activity, String respTarget, AliPayData payInfo) {
        CALL_BACK_ACTIVITY_NAME = respTarget;
        new AliPay(activity, payInfo).start();
    }

    /**
     * 微信支付
     *
     * @param context    上下文
     * @param respTarget 支付结果通过 eventbus 通知，接收类的 className
     * @param payInfo    微信支付信息
     */
    public static void pay(Context context, String respTarget, WeChatPayData payInfo) {
        CALL_BACK_ACTIVITY_NAME = respTarget;
        WECHAT_PAY_APP_ID = payInfo.getAppid();
        new WeChatPay(context, payInfo).start();
    }
}
