package com.mobanker.shanyidai.esb.business.log;

import com.mobanker.shanyidai.esb.model.entity.log.ApiServiceLogEntity;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/6 16:13
 */
public interface ApiServiceLogBusiness {
    /**
     * @param logEntity
     * @return void
     * @description API系统service模块业务日志
     * @author Richard Core
     * @time 2017/2/6 16:34
     * @method saveLog
     */
    void saveLog(ApiServiceLogEntity logEntity);

    /**
     * @description API系统保存业务流水
     * @author hantongyang
     * @time 2017/2/28 11:34
     * @method saveBusinessFlow
     * @param entity
     * @return void
     */
    void saveBusinessFlow(ApiServiceLogEntity entity);
}
