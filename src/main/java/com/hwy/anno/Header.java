package com.hwy.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类属性，用做excel表头标记
 * @author huangweiyu
 * @date 2018/3/13 10:23
 **/
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Header {

    String name() default "";

    int sort() default 0;

    String method() default "";

}
