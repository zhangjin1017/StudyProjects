package com.zj.springboottest.aop;

import java.lang.annotation.*;

/**
 * @Author zhangjin
 * @Date 2024/1/18 15:35
 * @description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordOperate {
    String desc() default "";
}
