package com.mobanker.shanyidai.dubbo.dto.message;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public final class ValidateSmsCaptchaParamDto extends Entity {

    /**
     * 验证码用途.<br>
     * 注册：register<br>
     * 忘记密码：forgetPwd<br>
     * 修改手机号：modifyPhone<br>
     * 借款：borrow<br>
     * 微信绑定手机号：bindWchat<br>
     * 活动: activity<br>
     * 找回支付密码：findPayPwd<br>
     */
    private String captchaUse;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 唯一编码
     */
    private String captcha;
    private String osType;// 手机系统	可为空
    private String osVer;//手机系统版本	可为空
    private String deviceAll;//设备信息	可为空
    private String lat;//纬度		可为空
    private String lon;//经度		 可为空
    private String map;//经纬度来自地图		 可为空


}
