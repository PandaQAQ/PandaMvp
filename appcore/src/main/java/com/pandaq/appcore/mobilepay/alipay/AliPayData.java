package com.pandaq.appcore.mobilepay.alipay;

/**
 * Created by huxinyu on 2018/7/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :支付宝调起支付支付信息数据
 */
public class AliPayData {

    /**
     * pay_string : alipay_sdk=alipay-sdk-php-20161101&app_id=2018012502072684&biz_content=%7B%22body%22%3A%22APP%5Cu652f%5Cu4ed8%22%2C%22subject%22%3A%22APP%5Cu652f%5Cu4ed8%22%2C%22out_trade_no%22%3A%22151703974914842%22%2C%22timeout_express%22%3A%2260m%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F7hd.txunda.com%2Findex.php%2FApi%2FPay%2FalipayNotify%2Ftype%2F1&sign_type=RSA2×tamp=2018-01-28+09%3A06%3A47&version=1.0&sign=aR0eQercHsb6x3bco7LUjQB7JKSseMxAmcLRUjfBpYQHlRRpcDxARFAkHCG4x6q655mCNkzXVBzWkIpWWTQCalLFLq8FwSB2hGly7HQbRkuLmwtwoqNAcSo51LODyolDGttVyc0CBUhQAiaZW8%2FvCh1ZV5jp7rn8gh%2B4E1RqVmHaQQzES6eNdZ8rjzb6wNLRJAF156JkJwQsXg02hePCklj8lRy2JxlAf2YG3RkytHFwvhzVy55mIoTGt1dqeMMqJRKediwLAkWJJOdo3yhOW9cSmioP8WuBvZiOswzHgyEfjpUuoBahrl3kIEHQt1sdQsfLLQRjac%2BGFa0L8EySSA%3D%3D
     */
    private String payString;

    public String getPayString() {
        return payString;
    }

    public void setPayString(String payString) {
        this.payString = payString;
    }

}
