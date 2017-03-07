package com.mobanker.shanyidai.dubbo.service.log;

import com.mobanker.shanyidai.dubbo.dto.log.EsbHttpLogDto;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;

/**
 * @desc:综合服务系统访问第三方HTTP协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:16
 */
@NoEELog
public interface EsbHttpLogDubboService {
    /**
     * @param logDto
     * @return void
     * @description 综合服务系统访问第三方HTTP协议访问日志
     * @author Richard Core
     * @time 2017/2/6 17:41
     * @method saveLog
     */
    public void saveLog(EsbHttpLogDto logDto);
}
