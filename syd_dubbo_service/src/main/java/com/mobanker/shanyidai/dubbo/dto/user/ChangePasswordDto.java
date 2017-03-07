package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ChangePasswordDto extends Entity {
    /**
     * 用户标识
     */
    private long userId;
    /**
     * md5加密后的旧密码串
     */
    private String oldPasswd;
    /**
     * md5加密后的新密码串
     */
    private String newPasswd;
    /**
     * md5加密后的新密码串(二次输入)
     */
    private String newPasswdAgain;

}
