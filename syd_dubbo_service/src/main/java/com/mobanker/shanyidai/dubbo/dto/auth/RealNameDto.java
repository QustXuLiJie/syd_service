package com.mobanker.shanyidai.dubbo.dto.auth;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author hantongyang
 * @version 1.0
 * @description 实名认证DTO
 * @date 创建时间：2016/12/26 10:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class RealNameDto extends Entity {
    private String idCard; //身份证号
    private String realName; //姓名
}
