package com.mobanker.shanyidai.esb.business.log;

import com.mobanker.shanyidai.esb.model.entity.log.EsbDubboLogEntity;

/**
 * @desc:综合服务系统访问第三方dubbo协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:14
 */
public interface EsbDubboLogBusiness {
    /**
     * @param logEntity
     * @return void
     * @description 综合服务系统访问第三方dubbo协议访问日志
     * @author Richard Core
     * @time 2017/2/6 16:36
     * @method saveLog
     */
    public void saveLog(EsbDubboLogEntity logEntity);
}
