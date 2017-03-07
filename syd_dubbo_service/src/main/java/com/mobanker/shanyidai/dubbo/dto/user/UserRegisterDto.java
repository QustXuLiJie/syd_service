package com.mobanker.shanyidai.dubbo.dto.user;


import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserRegisterDto extends Entity {

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
