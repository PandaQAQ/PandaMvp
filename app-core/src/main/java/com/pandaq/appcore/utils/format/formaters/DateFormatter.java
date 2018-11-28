package com.pandaq.appcore.utils.format.formaters;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :时间日期格式化工具类
 */
public class DateFormatter {

    private static DateFormatter sDateFormatter;

    public static synchronized DateFormatter getDefault() {
        if (sDateFormatter == null) {
            sDateFormatter = new DateFormatter();
        }
        return sDateFormatter;
    }

}
