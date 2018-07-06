package com.pandaq.appcore.utils.format.formaters;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :电话号码格式化工具类
 */
public class PhoneNumberFormatter {

    private static PhoneNumberFormatter sPhoneNumberFormatter;

    public static synchronized PhoneNumberFormatter getDefault() {
        if (sPhoneNumberFormatter == null) {
            sPhoneNumberFormatter = new PhoneNumberFormatter();
        }
        return sPhoneNumberFormatter;
    }

}
