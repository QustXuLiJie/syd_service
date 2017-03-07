package com.mobanker.shanyidai.esb.business.message;

import com.mobanker.message.contract.params.CaptchaTemplateWarpper;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageDto;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageParamsDto;
import com.mobanker.shanyidai.esb.model.entity.message.MessageTemplate;

import java.util.List;

public interface MessageBusiness {
    /**
     * 插入消息模版记录.<br>
     *
     * @param record
     * @return
     */
    public Long insertMessageTemplate(MessageTemplate record);

    /**
     * 查询消息模版列表.<br>
     *
     * @param record
     * @return
     */
    List<MessageTemplate> selectMessgeTemplate(MessageTemplate record);

    /**
     * @param warpper
     * @return java.lang.String
     * @description 发送验证码
     * @author Richard Core
     * @time 2016/12/26 20:28
     * @method sendSmsCaptcha
     */
    String sendSmsCaptcha(CaptchaTemplateWarpper warpper);

    /**
     * @param warpper
     * @return java.lang.String
     * @description 验证短信验证码
     * @author Richard Core
     * @time 2016/12/26 14:34
     * @method validateSmsCaptcha
     */
    String validateSmsCaptcha(CaptchaTemplateWarpper warpper);
    /**
     * @param paramDto
     * @return java.util.List<com.mobanker.shanyidai.dubbo.dto.message.SysMessageDto>
     * @description 查询系统消息
     * @author Richard Core
     * @time 2017/2/14 16:09
     * @method getSysMessage
     */
    public List<SysMessageDto> getSysMessage(SysMessageParamsDto paramDto);

    /**
     * @param ids
     * @return void
     * @description 更新消息读取状态
     * @author Richard Core
     * @time 2017/2/14 17:18
     * @method updateReadStatus
     */
    public void updateReadStatus(List<String> ids);

    /**
     * @param paramDto
     * @return int
     * @description 查询未读系统消息数
     * @author Richard Core
     * @time 2017/2/14 17:36
     * @method getUnreadSysMessageAmount
     */
    public long getUnreadSysMessageAmount(SysMessageParamsDto paramDto);
}