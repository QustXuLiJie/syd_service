package com.mobanker.shanyidai.esb.common.constants;

/**
 * @description: 登录成功或失败的枚举
 * @author: xulijie
 * @create time: 11:23 2017/2/28
 */
public enum LoginEnum {
    LOGIN_SUC("suc","登录成功"),
    LOGIN_FAIL("fail","登录失败");
    private String status;
    private String desc;

    LoginEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
