package com.pandaq.appcore.framework.annotation;

import com.pandaq.appcore.utils.format.formaters.DateFormatter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;

/**
 * Created by huxinyu on 2018/12/24.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */

@Retention(RetentionPolicy.CLASS)
@StringDef({DateFormatter.FORMAT_DAY, DateFormatter.FORMAT_DHM, DateFormatter.FORMAT_DHMS,
        DateFormatter.FORMAT_HM, DateFormatter.FORMAT_HMS, DateFormatter.FORMAT_WD,
        DateFormatter.FORMAT_WDHM, DateFormatter.FORMAT_WDHMS})
@Target({ElementType.PARAMETER})
public @interface DateFormatStr {
}
