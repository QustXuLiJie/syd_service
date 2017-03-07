package com.mobanker.shanyidai.esb.model.dto.user;


import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserRegisterBean extends Entity {

    /**
     * 手机号
     */
    private String phone;
    /**
     * md5加密后的密码串
     */
    private String password;
    /**
     * 验证码
     */
    private String validCode;
    /**
     * 邀请码
     */
    private String inviteCode;



}
