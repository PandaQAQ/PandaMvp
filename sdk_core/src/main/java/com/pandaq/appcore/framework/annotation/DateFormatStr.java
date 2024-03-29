package com.pandaq.appcore.framework.annotation;

import androidx.annotation.StringDef;

import com.pandaq.appcore.utils.format.formaters.DateFormatter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by huxinyu on 2018/12/24.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : MakeDateFormatString Rule
 */

@Retention(RetentionPolicy.SOURCE)
@StringDef({DateFormatter.FORMAT_MONTH, DateFormatter.FORMAT_MONTH_NO_SPLIT, DateFormatter.FORMAT_DAY_CN, DateFormatter.FORMAT_DAY, DateFormatter.FORMAT_DHM, DateFormatter.FORMAT_DHMS,
        DateFormatter.FORMAT_HM, DateFormatter.FORMAT_HMS, DateFormatter.FORMAT_WD,
        DateFormatter.FORMAT_WDHM, DateFormatter.FORMAT_WDHMS})
@Target({ElementType.PARAMETER})
public @interface DateFormatStr {

}
