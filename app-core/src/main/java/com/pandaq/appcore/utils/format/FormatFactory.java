package com.pandaq.appcore.utils.format;

import com.pandaq.appcore.utils.format.formaters.DateFormatter;
import com.pandaq.appcore.utils.format.formaters.NumInputFormatter;
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

    public static PriceFormatter PRICE = PriceFormatter.getDefault();

    public static DateFormatter DATE = DateFormatter.getDefault();

    public static NumInputFormatter NUMBER = NumInputFormatter.getDefault();

    public static SizeFormatter SIZE = SizeFormatter.getDefault();
}
