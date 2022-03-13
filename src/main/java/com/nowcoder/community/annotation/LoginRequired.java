package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pumpkin
 * @date 2022/3/13
 */
//注解作为范围
@Target(ElementType.METHOD)
//注解有效范围时机
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}
