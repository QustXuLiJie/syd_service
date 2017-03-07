package com.mobanker.shanyidai.esb.business.log;

import com.mobanker.shanyidai.esb.model.entity.log.EsbServiceLogEntity;

import java.util.Date;

/**
 * @desc:综合服务系统service模块访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:17
 */
public interface EsbServiceLogBusiness {
    /**
     * @param logEntity
     * @return void
     * @description 综合服务系统service模块访问日志
     * @author Richard Core
     * @time 2017/2/6 16:42
     * @method saveLog
     */
    public void saveLog(EsbServiceLogEntity logEntity);

    /**
     * @param excuteClass
     * @param method
     * @param funBeginTime
     * @param result
     * @param param
     * @return void
     * @description 综合服务service模块日志成功记录
     * @author Richard Core
     * @time 2017/2/20 9:49
     * @method saveEsbServiceSuccessLog
     */
    public void saveEsbServiceSuccessLog(Class<?> excuteClass, String method, Date funBeginTime, Object result, Object param);

    /**
     * @param excuteClass
     * @param method
     * @param funBeginTime
     * @param result
     * @param param
     * @param e
     * @return void
     * @description 综合服务service模块日志异常记录
     * @author Richard Core
     * @time 2017/2/20 9:50
     * @method saveEsbServiceErrorLog
     */
    public void saveEsbServiceErrorLog(Class<?> excuteClass, String method, Date funBeginTime, Object result, Object param, Throwable e);
}
