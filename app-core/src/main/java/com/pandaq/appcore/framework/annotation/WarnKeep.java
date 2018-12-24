package com.pandaq.appcore.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by huxinyu on 2018/9/26.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :混淆保持类，通过使用注解标识混淆时过滤此类或类属性
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface WarnKeep {

}
