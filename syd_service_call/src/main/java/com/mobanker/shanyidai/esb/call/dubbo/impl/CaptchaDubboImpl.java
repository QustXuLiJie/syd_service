package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.mobanker.framework.contract.dto.ResponseEntityDto;
import com.mobanker.message.contract.manager.DubboCaptchaContract;
import com.mobanker.message.contract.params.CaptchaTemplateWarpper;
import com.mobanker.shanyidai.esb.call.dubbo.CaptchaDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/28 9:22
 */
@Service
public class CaptchaDubboImpl implements CaptchaDubbo{

    public static final Logger logger = LogManager.getSlf4jLogger(CaptchaDubboImpl.class);

    @Autowired
    DubboCaptchaContract dubboCaptchaContract;
    /**
     * @param warpper
     * @return java.lang.String
     * @description 发送验证码
     * @author Richard Core
     * @time 2016/12/26 20:28
     * @method sendSmsCaptcha
     */
    @Override
    public String sendSmsCaptcha(CaptchaTemplateWarpper warpper) {
        ResponseEntityDto responseEntity = null;
        try {
            responseEntity = dubboCaptchaContract.sendCaptcha(warpper);
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), e.getMessage());
        }
        if (responseEntity == null) {
            throw new EsbException(ErrorConstant.SERVICE_FAIL);
        }
        com.mobanker.shanyidai.esb.common.packet.ResponseEntity resultEntity = BeanUtil.cloneBean(responseEntity, com.mobanker.shanyidai.esb.common.packet.ResponseEntity.class);
        if (!ResponseBuilder.isSuccess(resultEntity)) {
            logger.warn("调用消息系统服务-发送验证码", responseEntity);
            throw new EsbException(responseEntity.getError(), responseEntity.getMsg());
        }

        Map data = (Map)responseEntity.getData();
        return String.valueOf(data.get("sendStatus"));
    }
    /**
     * @param warpper
     * @return java.lang.String
     * @description 验证短信验证码
     * @author Richard Core
     * @time 2016/12/26 14:34
     * @method validateSmsCaptcha
     */
    @Override
    public String validateSmsCaptcha(CaptchaTemplateWarpper warpper) {
        //TODO
        warpper.setAddProduct("u_zone");
        ResponseEntityDto responseEntity = null;
        try {
            responseEntity = dubboCaptchaContract.validCaptcha(warpper);
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (responseEntity == null) {
            throw new EsbException(ErrorConstant.SERVICE_FAIL);
        }
        com.mobanker.shanyidai.esb.common.packet.ResponseEntity resultEntity = BeanUtil.cloneBean(responseEntity, com.mobanker.shanyidai.esb.common.packet.ResponseEntity.class);
        if (!ResponseBuilder.isSuccess(resultEntity)) {
            logger.warn("调用消息系统服务-验证短信验证码", responseEntity);
            throw new EsbException(responseEntity.getError(), responseEntity.getMsg());
        }
        Map data = (Map)responseEntity.getData();
        return String.valueOf(data.get("validStatus"));
    }
}
