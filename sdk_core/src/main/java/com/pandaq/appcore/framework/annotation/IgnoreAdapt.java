package com.pandaq.appcore.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by huxinyu on 2019/8/27.
 * Email : panda.h@foxmail.com
 * Description :UI 适配方案，忽略适配注解。用于标识一个 Activity 忽略注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface IgnoreAdapt {
}
