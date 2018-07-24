package com.pandaq.appcore.mobilepay.wechatpay;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pandaq.appcore.R;
import com.pandaq.appcore.mobilepay.PayUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by huxinyu on 2018/7/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :微信支付，支付工具对象
 */
public class WeChatPay {

    private WeChatPayData mWeChatPayData;
    private Context mContext;

    public WeChatPay(Context context, WeChatPayData payData) {
        this.mWeChatPayData = payData;
        mContext = context;
    }

    public void start() {
        sendToWX();
    }

    private void sendToWX() {
        if (mWeChatPayData != null) {
            IWXAPI api = WXAPIFactory.createWXAPI(mContext, null);
            api.registerApp(PayUtils.WECHAT_PAY_APP_ID);
            boolean wxSupport = api.isWXAppInstalled() && api.isWXAppSupportAPI();
            if (!wxSupport) {
                Toast.makeText(mContext, mContext.getString(R.string.wechat_not_support), Toast.LENGTH_SHORT).show();
                return;
            }
            Runnable runnable = () -> {
                PayReq payReq = new PayReq();
                payReq.appId = mWeChatPayData.getAppid();
                payReq.partnerId = mWeChatPayData.getMchId();
                payReq.packageValue = mWeChatPayData.getPackageValue();
                payReq.prepayId = mWeChatPayData.getPrepayId();
                payReq.nonceStr = mWeChatPayData.getNonceStr();
                payReq.timeStamp = mWeChatPayData.getTimeStamp();
                payReq.sign = mWeChatPayData.getSign();
                api.sendReq(payReq);
            };
            Thread payThread = new Thread(runnable);
            payThread.start();
        } else {
            Log.e("WXPAY", "PayInfo is null !!! please check it !!!");
        }
    }
}
