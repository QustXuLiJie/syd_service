package com.mobanker.shanyidai.dubbo.dto.auth;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xulijie
 * @version 1.0
 * @description 芝麻认证入参DTO
 * @date 创建时间：2017/1/22 14:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ZhimaAuthParamDto extends Entity{
    private  String code;
    private  String name;
    private  String certNo;
    private  String sences;//取entity中product字段
    private  String pageType;
//    private  String channel = "app";取entity中channel字段
}
