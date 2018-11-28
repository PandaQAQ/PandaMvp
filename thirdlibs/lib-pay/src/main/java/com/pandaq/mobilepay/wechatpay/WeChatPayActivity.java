package com.pandaq.mobilepay.wechatpay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.pandaq.mobilepay.PayType;
import com.pandaq.mobilepay.PayUtils;
import com.pandaq.mobilepay.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by huxinyu on 2018/7/4.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :支付回调 Activity，因微信支付回调限定了类路径，需在主 module 创建 wxapi 在包内创建
 * WXPayEntryActivity 空继承此 Activity
 */
public class WeChatPayActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);// SDK21
        }
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, PayUtils.WECHAT_PAY_APP_ID, true);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.d("wepay:===", baseReq.openId);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            if (baseResp.errCode == 0) {
                if (PayUtils.sPayCallBack == null) {
                    Log.d("WxPayResult", "payCallback must not be null !");
                } else {
                    PayUtils.sPayCallBack.paySuccess(PayType.WXPAY,
                            getString(R.string.pay_text_success));
                }
                this.finish();
            } else if (baseResp.errCode == -1) {
                if (PayUtils.sPayCallBack == null) {
                    Log.d("WxPayResult", "payCallback must not be null !");
                } else {
                    PayUtils.sPayCallBack.payFail(PayType.WXPAY, baseResp.errCode,
                            getString(R.string.pay_text_error));
                }
                this.finish();
            } else {
                if (PayUtils.sPayCallBack == null) {
                    Log.d("WxPayResult", "payCallback must not be null !");
                } else {
                    PayUtils.sPayCallBack.payFail(PayType.WXPAY, baseResp.errCode,
                            getString(R.string.pay_text_cancel));
                }
                this.finish();
            }
        }
    }
}
