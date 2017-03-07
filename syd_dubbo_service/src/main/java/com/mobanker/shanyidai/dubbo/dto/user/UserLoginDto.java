package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserLoginDto extends Entity {

    /**
     * 手机号
     */
    private String phone;
    /**
     * md加密后的密码
     */
    private String password;



}
