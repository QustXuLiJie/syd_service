package com.mobanker.shanyidai.esb.model.entity.device;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;

/**
 * @description: app激活实体
 * @author: xulijie
 * @create time: 10:53 2017/2/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_advert_activation_callback")
public class ActivationEntity extends BaseEntity{
    private String status;//状态:1有效，0无效
    private String appId;//APP ID
    private String udid;//IOS设备号
    private String idfa;//IOS广告标示符
    private String openudid;//OpenUDID
    private String ip;//用户终端IP
    private String ua;//用户终端设备标识：user_age
    private String uaKey;//用户终端设备标识匹配关键字
    private String message;//
    private String source;//推广商
    private String nodeType;//激活节点位置
    private String addProduct;//产品来源default：shanyidai
}
