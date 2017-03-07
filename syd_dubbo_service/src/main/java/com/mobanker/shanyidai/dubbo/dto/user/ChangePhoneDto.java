package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ChangePhoneDto extends Entity {
    /**
     * 用户标识
     */
    private Long userId;
    /**
     * 新手机号
     */
    private String newPhone;
    /**
     * 登录密码
     */
    private String password;

}
