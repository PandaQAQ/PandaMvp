package com.pandaq.mobilepay.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.pandaq.mobilepay.PayType;
import com.pandaq.mobilepay.PayUtils;
import com.pandaq.mobilepay.R;

import java.util.Map;

/**
 * Created by huxinyu on 2018/7/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :支付宝支付工具对象
 */
public class AliPay {

    private Activity activity;
    private AliPayData mPayInfo;
    private static final int SDK_PAY_FLAG = 1;

    public AliPay(Activity activity,
                  AliPayData payInfo) {
        this.activity = activity;
        mPayInfo = payInfo;
    }

    public void start() {
        startPay();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /*
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        if (PayUtils.sPayCallBack == null) {
                            Log.d("WxPayResult", "payCallback must not be null !");
                        } else {
                            PayUtils.sPayCallBack.paySuccess(PayType.ALIPAY,
                                    activity.getString(R.string.text_pay_success));
                        }
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        //返回取消支付
                        if (PayUtils.sPayCallBack == null) {
                            Log.d("WxPayResult", "payCallback must not be null !");
                        } else {
                            PayUtils.sPayCallBack.payFail(PayType.ALIPAY,Integer.parseInt(resultStatus),
                                    activity.getString(R.string.text_pay_cancel));
                        }
                    } else {
                        if (PayUtils.sPayCallBack == null) {
                            Log.d("WxPayResult", "payCallback must not be null !");
                        } else {
                            PayUtils.sPayCallBack.payFail(PayType.ALIPAY,Integer.parseInt(resultStatus),
                                    activity.getString(R.string.text_pay_error));
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void startPay() {
        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(activity);
            Map<String, String> result = alipay.payV2(mPayInfo.getPayString(), true);
            Log.i("msp", result.toString());
            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
