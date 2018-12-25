package com.pandaq.appcore.utils.format.formaters;

import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : for
 * phone number: 123 4567 2210/ +86 123 4567 2210
 * bank card number: 1111 1111 1111 1111 1111 123
 */
public class NumInputFormatter {

    private static NumInputFormatter sPhoneNumberFormatter;

    public static synchronized NumInputFormatter getDefault() {
        if (sPhoneNumberFormatter == null) {
            sPhoneNumberFormatter = new NumInputFormatter();
        }
        return sPhoneNumberFormatter;
    }

    /**
     * 格式化电话号码
     *
     * @param number 输入的电话号码
     * @param area   国家区域号
     * @return 格式化后的号码
     */
    public String formatPhoneNum(String number, String area) {
        char[] array = number.toCharArray();
        StringBuilder builder = new StringBuilder();
        if (!TextUtils.isEmpty(area)) {
            builder.append(area).append(" ");
        }
        for (int i = 0; i < array.length; i++) {
            if (i == 2 || i == 6) {
                builder.append(array[i]).append(" ");
            } else {
                builder.append(array[i]);
            }
        }
        return builder.toString();
    }

    /**
     * 4 位隔开格式化银行卡号
     *
     * @param cardNumber 银行卡号
     * @return 格式化后结果
     */
    public String formatBankCard(String cardNumber) {
        char[] array = cardNumber.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i % 4 == 0) {
                builder.append(" ").append(array[i]);
            } else {
                builder.append(array[i]);
            }
        }
        return builder.toString();
    }
}
