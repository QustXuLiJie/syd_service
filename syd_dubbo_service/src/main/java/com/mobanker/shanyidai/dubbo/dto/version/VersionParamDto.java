package com.mobanker.shanyidai.dubbo.dto.version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: app版本更新传入参数
 * @author: xulijie
 * @create time: 20:53 2017/2/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class VersionParamDto implements Serializable {
    //状态：1有效，0无效
    public static final String status = "1";
    public static final String updateForce = "FORBID";
    //类型：android、ios,默认：android
    private String type;
    //产品
    private String product;
}
