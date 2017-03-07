package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.shanyidai.dubbo.dto.message.SysMessageDto;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageParamsDto;

import java.util.List;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/28 9:22
 */
public interface MessageDubbo {
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
