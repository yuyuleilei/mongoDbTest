package com.yulei.my.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Inherited;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/7/3
 * @description
 */
@Component
@Inherited
public @interface Business {
    String value() default "";
}
