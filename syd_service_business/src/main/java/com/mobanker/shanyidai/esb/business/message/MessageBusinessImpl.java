package com.mobanker.shanyidai.esb.business.message;

import com.mobanker.message.contract.params.CaptchaTemplateWarpper;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageDto;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageParamsDto;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.call.dubbo.CaptchaDubbo;
import com.mobanker.shanyidai.esb.call.dubbo.MessageDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.ZkConfigConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.model.entity.message.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("messageBusiness")
public class MessageBusinessImpl implements MessageBusiness {
    public static final Logger logger = LogManager.getLogger(MessageBusinessImpl.class);
    @Autowired
    private CaptchaDubbo captchaDubbo;
    @Resource
    private EsbCommonBusiness esbCommonBusiness;
    @Resource
    private MessageDubbo messageDubbo;

    @Override
    public Long insertMessageTemplate(MessageTemplate record) {
        // TODO Auto-generated method stub
//		return messageTemplateMapper.insertSelective(record);
        return null;
    }

    @Override
    public List<MessageTemplate> selectMessgeTemplate(MessageTemplate record) {
        // TODO Auto-generated method stub
//		return messageTemplateMapper.selectByAnd(record);
        return null;
    }


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
        return captchaDubbo.sendSmsCaptcha(warpper);
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
        //验证验证码参数
        checkVerifyCodeParam(warpper.getCaptcha());
        return captchaDubbo.validateSmsCaptcha(warpper);
    }
    /**
     * @param
     * @return int
     * @description 获取验证码位数
     * @author Richard Core
     * @time 2016/12/28 16:15
     * @method getSmsVerifyCodeLength
     */
    public int getSmsVerifyCodeLength() {
        String cacheSysConfig = null;
        try {
            cacheSysConfig = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.VERIFY_CODE_LENGTH.getZkValue());
        }catch (Exception e){
            throw new EsbException(ErrorConstant.CONFIG_DATA_NULL);
        }
        return Integer.parseInt(cacheSysConfig);
    }
    /**
     * @param
     * @return verifyCode
     * @description 获取验证码位数
     * @author Richard Core
     * @time 2016/12/28 16:15
     * @method getSmsVerifyCodeLength
     */
    public void checkVerifyCodeParam(String verifyCode) {
        if (verifyCode == null) {
            logger.error("验证码为空");
            throw new EsbException(ErrorConstant.WRONG_VERIFYCODE.getCode(), ErrorConstant.WRONG_VERIFYCODE.getDesc(), null);
        }
        int length = getSmsVerifyCodeLength();
        if (verifyCode == null || verifyCode.length() != length) {
            logger.error("验证码为空或者格式错误");
            throw new EsbException(ErrorConstant.WRONG_VERIFYCODE.getCode(), ErrorConstant.WRONG_VERIFYCODE.getDesc(), null);
        }
    }


    /**
     * @param paramDto
     * @return java.util.List<com.mobanker.shanyidai.dubbo.dto.message.SysMessageDto>
     * @description 查询系统消息
     * @author Richard Core
     * @time 2017/2/14 16:09
     * @method getSysMessage
     */
    @Override
    public List<SysMessageDto> getSysMessage(SysMessageParamsDto paramDto) {
        return messageDubbo.getSysMessage(paramDto);
    }

    /**
     * @param ids
     * @return void
     * @description 更新消息读取状态
     * @author Richard Core
     * @time 2017/2/14 17:18
     * @method updateReadStatus
     */
    @Override
    public void updateReadStatus(List<String> ids) {
        messageDubbo.updateReadStatus(ids);
    }

    /**
     * @param paramDto
     * @return int
     * @description 查询未读系统消息数
     * @author Richard Core
     * @time 2017/2/14 17:36
     * @method getUnreadSysMessageAmount
     */
    @Override
    public long getUnreadSysMessageAmount(SysMessageParamsDto paramDto) {
        return messageDubbo.getUnreadSysMessageAmount(paramDto);
    }
}
