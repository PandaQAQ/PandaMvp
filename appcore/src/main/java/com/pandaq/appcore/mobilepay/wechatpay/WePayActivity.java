package com.pandaq.appcore.mobilepay.wechatpay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.pandaq.appcore.R;
import com.pandaq.appcore.eventbus.EventAction;
import com.pandaq.appcore.eventbus.EventMessage;
import com.pandaq.appcore.eventbus.EventUtils;
import com.pandaq.appcore.mobilepay.PayUtils;
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
public class WePayActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
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
            EventMessage message = new EventMessage();
            if (baseResp.errCode == 0) {
                message.setAction(EventAction.PAY_SUCCESS);
                message.setMessage(getString(R.string.text_pay_success));
                message.addTargetsName(PayUtils.CALL_BACK_ACTIVITY_NAME);
                EventUtils.getDefault().postEvent(message);
                this.finish();
            } else if (baseResp.errCode == -1) {
                message.setAction(EventAction.PAY_FAIL);
                message.setMessage(getString(R.string.text_pay_error));
                message.addTargetsName(PayUtils.CALL_BACK_ACTIVITY_NAME);
                EventUtils.getDefault().postEvent(message);
                this.finish();
            } else {
                message.setAction(EventAction.PAY_CANCEL);
                message.setMessage(getString(R.string.text_pay_cancel));
                message.addTargetsName(PayUtils.CALL_BACK_ACTIVITY_NAME);
                EventUtils.getDefault().postEvent(message);
                this.finish();
            }
        }
    }
}
