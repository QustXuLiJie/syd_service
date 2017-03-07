package com.mobanker.shanyidai.esb.common.constants;

/**
 * @author hantongyang
 * @description 请求类型枚举
 * @time 2017/2/7 10:55
 */
public enum ReqTypeEnum {
    DUBBO("dubbo"),
    HTTP("http"),
    REST("rest"),
    WEBSERVICE("webservice"),

    ;
    private String type;

    ReqTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
