package com.pandaq.appcore.utils.format.formaters;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :银行卡号格式化工具类
 */
public class BankCardFormatter {

    private static BankCardFormatter sBankCardFormatter;

    public static synchronized BankCardFormatter getDefault() {
        if (sBankCardFormatter == null) {
            sBankCardFormatter = new BankCardFormatter();
        }
        return sBankCardFormatter;
    }

}
