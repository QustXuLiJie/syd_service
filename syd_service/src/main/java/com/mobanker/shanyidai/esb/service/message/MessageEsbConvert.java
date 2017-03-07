package com.mobanker.shanyidai.esb.service.message;

import com.mobanker.message.contract.enums.OpeType;
import com.mobanker.message.contract.params.CaptchaTemplateWarpper;
import com.mobanker.shanyidai.dubbo.dto.message.SmsMesageParamDto;
import com.mobanker.shanyidai.dubbo.dto.message.ValidateSmsCaptchaParamDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.EsbSystemEnum;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/23 15:05
 */
public class MessageEsbConvert {
    /**
     * @param dto
     * @return void
     * @description 验证短信验证码参数
     * @author Richard Core
     * @time 2016/12/26 11:51
     * @method checkCaptchaParam
     */
    public static void checkCaptchaParam(ValidateSmsCaptchaParamDto dto) {
        //验证基本参数
        CommonUtil.checkBaseParam(dto);
        //验证手机号
        CommonUtil.checkPhone(dto.getPhone());
        //验证验证码 格式
//        CommonUtil.checkVerifyCode(dto.getCaptcha());
        //验证验证码用途
        if (StringUtils.isBlank(dto.getCaptchaUse())) {
            throw new EsbException(ErrorConstant.CAPTCHA_USE_NULL);
        }
    }

    /**
     * @param dto
     * @return com.mobanker.tkj.msg.api.wrapper.CaptchaTemplateWarpper
     * @description 封装验证码验证的参数
     * @author Richard Core
     * @time 2016/12/23 15:05
     * @method getCaptchaCheckParam
     */
    public static CaptchaTemplateWarpper getCaptchaCheckParam(ValidateSmsCaptchaParamDto dto) {
        CaptchaTemplateWarpper warpper = new CaptchaTemplateWarpper();
        warpper.setPhone(dto.getPhone());
        warpper.setCaptcha(dto.getCaptcha());
        OpeType opeType = getOpeType(dto.getCaptchaUse());
        warpper.setCaptchaUse(opeType);
        warpper.setOsType(dto.getOsType());
        warpper.setOsVer(dto.getOsVer());
        warpper.setDeviceAll(dto.getDeviceAll());
        warpper.setLat(dto.getLat());
        warpper.setLon(dto.getLon());
        warpper.setMap(dto.getMap());
//        warpper.setSign
        warpper.setAppVer(dto.getVersion());
        warpper.setRemoteIp(dto.getIp());
        warpper.setAddProduct(dto.getProduct());
        warpper.setAddChannel(dto.getChannel());
        return warpper;
    }

    /**
     * @param captchaUse
     * @return com.mobanker.message.model.enums.OpeType
     * @description 转化验证码操作类型
     * @author Richard Core
     * @time 2016/12/27 13:49
     * @method getOpeType
     */
    public static OpeType getOpeType(String captchaUse) {
        //验证码用途不能为空
        if (StringUtils.isBlank(captchaUse)) {
            throw new EsbException(ErrorConstant.CAPTCHA_USE_NULL);
        }
        //验证是否是枚举里的值
        for (OpeType opeType : OpeType.values()) {
            if (opeType.name().equals(captchaUse)) {
                return opeType;
            }
        }
        throw new EsbException(ErrorConstant.CAPTCHA_USE_INVALID);
    }

    /**
     * @param dto
     * @return com.mobanker.tkj.msg.api.wrapper.CaptchaTemplateWarpper
     * @description 发送短信模板参数
     * @author Richard Core
     * @time 2016/12/26 20:18
     * @method getCallCaptchaSendParam
     */
    public static CaptchaTemplateWarpper getCallCaptchaSendParam(SmsMesageParamDto dto) {
        CaptchaTemplateWarpper warpper = new CaptchaTemplateWarpper();
        warpper.setPhone(dto.getPhone());//手机号
        warpper.setTid(dto.getTemplateId());//模板id
        warpper.setOsType(dto.getOsType());//手机系统
        warpper.setOsVer(dto.getOsVer());//手机系统版本
        warpper.setDeviceAll(dto.getDeviceAll());//设备信息
        warpper.setLat(dto.getLat());//经度
        warpper.setLon(dto.getLon());//维度
        warpper.setMap(dto.getMap());//经纬度来自地图
        warpper.setFlagId(UUID.randomUUID().toString());//唯一编码
        warpper.setSystemCode(EsbSystemEnum.SMS_SYSTEM_CODE.getValue());//系统编码
        // TODO: 2016/12/27  测试发消息 先用U族的测试
        warpper.setAddProduct("u_zone");
//        warpper.setAddProduct(dto.getProduct());
        warpper.setAddChannel(dto.getChannel());
        warpper.setRemoteIp(dto.getIp());
        warpper.setAppVer(dto.getVersion());
        return warpper;

    }

    /**
     * @param dto
     * @return void
     * @description 发送短信验证码参数校验
     * @author Richard Core
     * @time 2016/12/26 20:21
     * @method checkSendSmsParam
     */
    public static void checkSendSmsParam(SmsMesageParamDto dto) {
        CommonUtil.checkBaseParam(dto);
        CommonUtil.checkPhone(dto.getPhone());
        if (StringUtils.isBlank(dto.getTemplateId())) {
            throw new EsbException(ErrorConstant.SMS_TEMPLATE_ID);
        }
    }
}
