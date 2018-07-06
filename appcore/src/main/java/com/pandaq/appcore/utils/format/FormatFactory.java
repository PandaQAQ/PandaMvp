package com.pandaq.appcore.utils.format;

import com.pandaq.appcore.utils.format.formaters.BankCardFormatter;
import com.pandaq.appcore.utils.format.formaters.DateFormatter;
import com.pandaq.appcore.utils.format.formaters.PhoneNumberFormatter;
import com.pandaq.appcore.utils.format.formaters.PriceFormatter;
import com.pandaq.appcore.utils.format.formaters.SizeFormatter;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :Format格式化工具入口类
 */
public class FormatFactory {

    public static BankCardFormatter bankCardFormatter() {
        return BankCardFormatter.getDefault();
    }

    public static PriceFormatter priceFormatter() {
        return PriceFormatter.getDefault();
    }

    public static DateFormatter dateFormatter() {
        return DateFormatter.getDefault();
    }

    public static PhoneNumberFormatter phoneNumberFormatter() {
        return PhoneNumberFormatter.getDefault();
    }

    public static SizeFormatter sizeFormatter() {
        return SizeFormatter.getDefault();
    }
}
