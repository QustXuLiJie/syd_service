package com.mobanker.shanyidai.esb.business.log;

import com.mobanker.shanyidai.esb.model.entity.log.EsbHttpLogEntity;

/**
 * @desc:综合服务系统访问第三方HTTP协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:16
 */
public interface EsbHttpLogBusiness {
    /**
     * @param logEntity
     * @return void
     * @description 综合服务系统访问第三方HTTP协议访问日志
     * @author Richard Core
     * @time 2017/2/6 16:38
     * @method saveLog
     */
    public void saveLog(EsbHttpLogEntity logEntity);
}
