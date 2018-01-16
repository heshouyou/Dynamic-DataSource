package com.heshouyou.core;

import java.lang.annotation.*;

/**
 * @Author heshouyou
 * @Description  作用于类、接口或者方法上
 * @Date 2018-01-16 14:42.
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String name();
}
