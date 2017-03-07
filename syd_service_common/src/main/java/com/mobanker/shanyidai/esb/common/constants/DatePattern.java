package com.mobanker.shanyidai.esb.common.constants;

/**
 * @author Richard Core
 * @description 常用时间格式
 * @date 2016/12/24 18:55
 */
public enum DatePattern {
    DATE_TIME("yyyy-mm-dd HH:mm:ss"),
    DATE("yyyy-mm-dd"),
    DATE_NOSEP("yyyymmdd"),
    DATE_TIME_NOSEP("yyyymmddHHmmss"),
    DATETIME_ZONE_NOSEP("yyyymmddHHmmssZZZ"),
    ;

    DatePattern(String pattern) {
        this.pattern = pattern;
    }

    private String pattern;

    public String getPattern() {
        return pattern;
    }
}
