package com.mobanker.shanyidai.esb.model.entity.version;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;

/**
 * @description: app版本更新实体
 * @author: xulijie
 * @create time: 11:45 2017/2/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_app_versions")
public class VersionEntity extends BaseEntity {
    //状态:1有效，0无效
    private String status;
    //APP版本号
    private String appVersion;
    //路径
    private String appUrl;
    //APP版本描述
    private String appDesc;
    //是否强制更新，FORCE强制更新，UNFORCE不强制更新，FORBID禁止更新
    private String updateForce;
    //类型，android、ios
    private String type;
    //渠道
    private String channel;
    //系统最低兼容手机系统版本
    private String minVersion;
    //产品
    private String product;
}
