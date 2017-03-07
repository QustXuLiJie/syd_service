package com.mobanker.shanyidai.esb.model.dto.user;

import com.mobanker.shanyidai.esb.common.constants.LoginEnum;
import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserLoginBean extends Entity {
    /**
     * 手机号
     */
    private String phone;
    /**
     * md加密后的密码
     */
    private String password;
    /**
     * 应用版本号
     */
    private String version;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 登录结果 suc/fail
     */
    private String loginResult = LoginEnum.LOGIN_FAIL.getStatus();
    /**
     * 登录方式 普通登录 normal /微信登录 weChat /免密登录 noPwd
     */
    private String loginType;
}
