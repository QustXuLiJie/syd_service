package com.mobanker.shanyidai.dubbo.dto.message;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public final class SmsMesageParamDto extends Entity {
    /**
     * 唯一编码
     */
    private String flagId;
    /**
     * 消息模板ID. <br>
     * 取值参考枚举类：TemplateId<br>
     */
    private String templateId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 系统ID
     */
    private String systemId;
    /**
     * 系统编码
     */
    private String systemCode;
    private String captcha;
    private String osType;// 手机系统	可为空
    private String osVer;//手机系统版本	可为空
    private String deviceAll;//设备信息	可为空
    private String lat;//纬度		可为空
    private String lon;//经度		 可为空
    private String map;//经纬度来自地图		 可为空
}

