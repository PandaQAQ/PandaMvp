package com.pandaq.appcore.utils.format.formaters;

import android.annotation.SuppressLint;

import com.pandaq.appcore.framework.annotation.DateFormatStr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :时间日期格式化工具类
 */
public class DateFormatter {

    private static DateFormatter sDateFormatter;

    public final static String FORMAT_DAY = "yyyy-MM-dd"; // 2018、
    public final static String FORMAT_DHMS = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_DHM = "yyyy-MM-dd HH:mm";
    public final static String FORMAT_HMS = "HH:mm:ss";
    public final static String FORMAT_HM = "HH:mm";
    public final static String FORMAT_WDHMS = "EEEE yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_WDHM = "EEEE yyyy-MM-dd HH:mm";
    public final static String FORMAT_WD = "EEEE yyyy-MM-dd";

    public static synchronized DateFormatter getDefault() {
        if (sDateFormatter == null) {
            sDateFormatter = new DateFormatter();
        }
        return sDateFormatter;
    }

    /**
     * 获取日期
     * 格式 2018年06月12日 12时12分
     *
     * @param timeMap 时间戳
     * @return 格式化后的时间
     */
    public String formatToNaturalDate(long timeMap) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMap);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return String.format("%s年%s月%s日 %s时%s分", year, month, day, hour, minute);
    }

    /**
     * 获取日期
     * 格式 2018年06月12日
     *
     * @param timeMap 时间戳
     * @return 格式化后的日期
     */
    public String formatToNaturalDay(long timeMap) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMap);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%s年%s月%s日", year, month, day);
    }

    /**
     * 根据指定格式格式化时间戳
     *
     * @param timeMap   时间戳
     * @param formatStr 格式化规则
     * @return 格式化后的时间戳
     */
    public String formatTime(long timeMap, @DateFormatStr String formatStr) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(timeMap);
    }

    /**
     * 指定格式时间转时间戳
     *
     * @param timeFormat 格式化的事件
     * @param formatStr  格式化规则
     * @return 时间戳
     */
    public long format2TimeMap(String timeFormat, @DateFormatStr String formatStr) throws ParseException {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.parse(timeFormat).getTime();
    }

}
