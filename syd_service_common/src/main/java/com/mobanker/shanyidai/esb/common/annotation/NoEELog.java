package com.mobanker.shanyidai.esb.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hantongyang
 * @description 标识返回值是否需要转化为dubboResult对象
 * @time 2017/2/4 14:14
 */
@Target({ElementType.METHOD, ElementType.TYPE})//修饰方法的
@Retention(RetentionPolicy.RUNTIME)//运行期有效
public @interface NoEELog {
}
