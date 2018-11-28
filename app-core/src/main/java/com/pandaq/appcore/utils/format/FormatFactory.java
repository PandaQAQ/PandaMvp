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

    private FormatFactory() {
        // private constructor
    }

    public static BankCardFormatter BANK_CARD = BankCardFormatter.getDefault();

    public static PriceFormatter PRICE = PriceFormatter.getDefault();

    public static DateFormatter DATE = DateFormatter.getDefault();

    public static PhoneNumberFormatter PHONE_NUM = PhoneNumberFormatter.getDefault();

    public static SizeFormatter DATA_SIZE = SizeFormatter.getDefault();
}
