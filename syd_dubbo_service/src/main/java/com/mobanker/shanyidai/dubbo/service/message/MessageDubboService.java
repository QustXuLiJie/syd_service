package com.mobanker.shanyidai.dubbo.service.message;

import com.mobanker.shanyidai.dubbo.dto.message.SmsMesageParamDto;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageParamsDto;
import com.mobanker.shanyidai.dubbo.dto.message.ValidateSmsCaptchaParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.List;

/**
 * @author Richard Core
 * @description 消息相关
 * @date 2016/12/23 14:57
 */
public interface MessageDubboService {

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 发送短信验证码
     * @author Richard Core
     * @time 2016/12/26 11:15
     * @method sendSmsCaptcha
     */
    public ResponseEntity sendSmsCaptcha(SmsMesageParamDto dto);

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 验证验证码
     * @author Richard Core
     * @time 2016/12/23 15:02
     * @method validateSmsCaptcha
     */
    public ResponseEntity validateSmsCaptcha(ValidateSmsCaptchaParamDto dto);

    /**
     * @param paramDto
     * @return ResponseEntity
     * @description 查询系统消息
     * @author Richard Core
     * @time 2017/2/14 16:09
     * @method getSysMessage
     */
    public ResponseEntity getSysMessage(SysMessageParamsDto paramDto);

    /**
     * @param ids
     * @return ResponseEntity
     * @description 更新消息读取状态
     * @author Richard Core
     * @time 2017/2/14 17:18
     * @method updateReadStatus
     */
    public ResponseEntity updateReadStatus(List<String> ids);

    /**
     * @param paramDto
     * @return ResponseEntity
     * @description 查询未读系统消息数
     * @author Richard Core
     * @time 2017/2/14 17:36
     * @method getUnreadSysMessageAmount
     */
    public ResponseEntity getUnreadSysMessageAmount(SysMessageParamsDto paramDto);
}
