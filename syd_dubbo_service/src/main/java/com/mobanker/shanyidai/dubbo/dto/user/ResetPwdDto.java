package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ResetPwdDto extends Entity {
    private Long userId;//用户的userId
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * md5加密后的新用户密码串
     */
    private String password;

}
