package com.mobanker.shanyidai.dubbo.dto.version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: app版本更新结果参数
 * @author: xulijie
 * @create time: 20:54 2017/2/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class VersionResultDto implements Serializable {
    //是否有更新
    private String update;
    //app版本号
    private String appVersion;
    //路径
    private String appUrl;
    //APP版本描述
    private String appDesc;
    //是否强制更新，FORCE强制更新，UNFORCE不强制更新，FORBID禁止更新
    private String updateForce;
    //系统最低兼容手机系统版本
    private String minVersion;
    //类型：android、ios,默认：android
    private String type;
    //产品
    private String product;
}
