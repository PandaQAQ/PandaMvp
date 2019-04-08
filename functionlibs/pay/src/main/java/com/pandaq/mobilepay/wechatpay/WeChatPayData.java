package com.pandaq.mobilepay.wechatpay;

/**
 * Created by huxinyu on 2018/7/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :微信支付，调起支付信息数据对象
 */
public class WeChatPayData {

    /**
     * sign : 9D28985B1A0D59A0C0D9C68744155D08
     * appid :
     * nonce_str :
     * package : Sign=WXPay
     * package_value : Sign=WXPay
     * time_stamp : 1517101692
     * prepay_id :
     * mch_id :
     */
    private String sign;
    private String appId;
    private String nonceStr;
    private String packageX;
    private String packageValue;
    private String timeStamp;
    private String prepayId;
    private String mchId;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppid() {
        return appId;
    }

    public void setAppid(String appid) {
        this.appId = appid;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
}
