package com.mobanker.shanyidai.esb.service.message;

import com.mobanker.message.contract.params.CaptchaTemplateWarpper;
import com.mobanker.shanyidai.dubbo.dto.message.SmsMesageParamDto;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageDto;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageParamsDto;
import com.mobanker.shanyidai.dubbo.dto.message.ValidateSmsCaptchaParamDto;
import com.mobanker.shanyidai.dubbo.service.message.MessageDubboService;
import com.mobanker.shanyidai.esb.business.message.MessageBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Richard Core
 * @description
 * @date 2016/12/23 15:02
 */
public class MessageServiceImpl implements MessageDubboService {
    public static final Logger logger = LogManager.getSlf4jLogger(MessageServiceImpl.class);
    @Resource
    private MessageBusiness messageBusiness;

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 发送短信验证码
     * @author Richard Core
     * @time 2016/12/26 11:15
     * @method sendSmsCaptcha
     */
    @Override
    public ResponseEntity sendSmsCaptcha(SmsMesageParamDto dto) {
        try {
            //参数验证
            MessageEsbConvert.checkSendSmsParam(dto);
            //封装模板参数
            CaptchaTemplateWarpper warpper = MessageEsbConvert.getCallCaptchaSendParam(dto);
            //发送验证码
            String result = messageBusiness.sendSmsCaptcha(warpper);
            return ResponseBuilder.normalResponse(result);
        } catch (EsbException e) {
            logger.error("发送验证码失败", e);
            return ResponseBuilder.errorResponse(ErrorConstant.SMS_SEND_FAIL,
                    e, this.getClass().getSimpleName(), "sendSmsCaptcha");
        }
    }


    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 验证验证码
     * @author Richard Core
     * @time 2016/12/23 15:02
     * @method validateSmsCaptcha
     */
    @Override
    public ResponseEntity validateSmsCaptcha(ValidateSmsCaptchaParamDto dto) {
        String result = null;
        try {
            //验证参数
            MessageEsbConvert.checkCaptchaParam(dto);
            //封装参数
            CaptchaTemplateWarpper warpper = MessageEsbConvert.getCaptchaCheckParam(dto);
            //调用服务验证
            result = messageBusiness.validateSmsCaptcha(warpper);
            return ResponseBuilder.normalResponse(result);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.WRONG_VERIFYCODE, e, this.getClass().getSimpleName(), "validateSmsCaptcha");
        }
    }


    /**
     * @param paramDto
     * @return ResponseEntity
     * @description 查询系统消息
     * @author Richard Core
     * @time 2017/2/14 16:09
     * @method getSysMessage
     */
    @Override
    public ResponseEntity getSysMessage(SysMessageParamsDto paramDto) {
        try {
            List<SysMessageDto> sysMessage = messageBusiness.getSysMessage(paramDto);
            return ResponseBuilder.normalResponse(sysMessage);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.SYS_MESSAGE_ERROR, e, this.getClass().getSimpleName(), "getSysMessage");
        }
    }

    /**
     * @param ids
     * @return ResponseEntity
     * @description 更新消息读取状态
     * @author Richard Core
     * @time 2017/2/14 17:18
     * @method updateReadStatus
     */
    @Override
    public ResponseEntity updateReadStatus(List<String> ids) {
        try {
            messageBusiness.updateReadStatus(ids);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.SYS_MESSAGE_ERROR, e, this.getClass().getSimpleName(), "getSysMessage");
        }
    }

    /**
     * @param paramDto
     * @return ResponseEntity
     * @description 查询未读系统消息数
     * @author Richard Core
     * @time 2017/2/14 17:36
     * @method getUnreadSysMessageAmount
     */
    @Override
    public ResponseEntity getUnreadSysMessageAmount(SysMessageParamsDto paramDto) {
        try {
            long unreadSysMessageAmount = messageBusiness.getUnreadSysMessageAmount(paramDto);
            return ResponseBuilder.normalResponse(unreadSysMessageAmount);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.SYS_MESSAGE_ERROR, e, this.getClass().getSimpleName(), "getSysMessage");
        }
    }
}
