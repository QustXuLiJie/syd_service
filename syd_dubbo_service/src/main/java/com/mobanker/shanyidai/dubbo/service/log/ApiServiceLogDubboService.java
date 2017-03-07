package com.mobanker.shanyidai.dubbo.service.log;

import com.mobanker.shanyidai.dubbo.dto.log.ApiServiceLogDto;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @desc:API系统service模块业务日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:13
 */
@NoEELog
public interface ApiServiceLogDubboService {
    /**
     * @param logDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description API系统service模块业务日志
     * @author Richard Core
     * @time 2017/2/6 17:18
     * @method saveLog
     */
    ResponseEntity saveLog(ApiServiceLogDto logDto);

    /**
     * @description 保存API业务流水
     * @author hantongyang
     * @time 2017/2/28 11:22
     * @method saveBusinessFlow
     * @param logDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity saveBusinessFlow(ApiServiceLogDto logDto);
}
