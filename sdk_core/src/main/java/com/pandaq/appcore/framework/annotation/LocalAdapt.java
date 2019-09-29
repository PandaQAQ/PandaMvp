package com.pandaq.appcore.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by huxinyu on 2019/8/27.
 * Email : panda.h@foxmail.com
 * Description :特殊页面 Activity 设计稿尺寸与通用尺寸不一致使用注解重新定义设计稿尺寸
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LocalAdapt {
    float designWidth();
}
