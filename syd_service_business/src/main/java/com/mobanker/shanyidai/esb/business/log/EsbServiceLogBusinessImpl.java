package com.mobanker.shanyidai.esb.business.log;

import com.alibaba.fastjson.JSON;
import com.mobanker.shanyidai.dubbo.dto.log.EsbServiceLogDto;
import com.mobanker.shanyidai.esb.common.constants.ReqTypeEnum;
import com.mobanker.shanyidai.esb.common.constants.SydConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import com.mobanker.shanyidai.esb.dao.log.EsbServiceLogMapper;
import com.mobanker.shanyidai.esb.model.entity.log.EsbServiceLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @desc:综合服务系统service模块访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:18
 */
@Service
public class EsbServiceLogBusinessImpl implements EsbServiceLogBusiness {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(EsbServiceLogBusinessImpl.class);
    @Autowired
    private EsbServiceLogMapper esbServiceLogMapper;

    /**
     * @param logEntity
     * @return void
     * @description 综合服务系统service模块访问日志
     * @author Richard Core
     * @time 2017/2/6 16:42
     * @method saveLog
     */
    @Override
    public void saveLog(EsbServiceLogEntity logEntity) {
        esbServiceLogMapper.insert(logEntity);
    }

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
    @Override
    public void saveEsbServiceSuccessLog(Class<?> excuteClass, String method, Date funBeginTime, Object result, Object param) {
        try {
            EsbServiceLogDto logDto = setEsbServLogCommonField(excuteClass, method, funBeginTime, result, param);
            logDto.setIsSuccess(SydConstant.RSP_STATUS_SUCCESS);   //请求是否成功
            EsbServiceLogEntity logEntity = BeanUtil.cloneBean(logDto, EsbServiceLogEntity.class);
            saveLog(logEntity);
        } catch (Exception e) {
            LOGGER.warn("综合服务service日志记录失败" + e);
        }
    }

    /**
     * @param excuteClass
     * @param method
     * @param funBeginTime
     * @param result
     * @param param
     * @return com.mobanker.shanyidai.dubbo.dto.log.EsbServiceLogDto
     * @description 封装日志公共字段
     * @author Richard Core
     * @time 2017/2/20 9:51
     * @method setEsbServLogCommonField
     */
    private EsbServiceLogDto setEsbServLogCommonField(Class<?> excuteClass, String method, Date funBeginTime, Object result, Object param) {
        EsbServiceLogDto logDto = new EsbServiceLogDto();
        logDto.setPackageName(excuteClass.getPackage().getName());   //包名
        logDto.setClassName(excuteClass.getSimpleName());   //类名
        logDto.setMethodName(method);   //方法名
        logDto.setReqStartTime(funBeginTime);   //请求时间
        Date funEndTime = new Date();
        logDto.setReqEndTime(funEndTime);   //请求结束时间
        logDto.setDuration(funEndTime.getTime() - funEndTime.getTime());   //请求时长
        logDto.setReqParam(JSON.toJSONString(param));   //请求参数
        logDto.setReqType(ReqTypeEnum.DUBBO.getType());   //请求类型
        logDto.setSuccessResult(JSON.toJSONString(result));   //返回结果
        return logDto;
    }

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
    @Override
    public void saveEsbServiceErrorLog(Class<?> excuteClass, String method, Date funBeginTime, Object result, Object param, Throwable e) {
        try {
            EsbServiceLogDto logDto = setEsbServLogCommonField(excuteClass, method, funBeginTime, result, param);
            if (e != null) {
                logDto.setIsSuccess(SydConstant.RSP_STATUS_FAIL);
                if (e instanceof EsbException) {
                    EsbException esb = (EsbException) e;
                    logDto.setErrorCode(esb.errCode);
                    logDto.setErrorResult(esb.message);   //异常结果
                } else {
                    logDto.setErrorCode("");   //异常编码
                    logDto.setErrorResult(CommonUtil.getStackTrace(e));   //异常结果
                }
            }
            EsbServiceLogEntity logEntity = BeanUtil.cloneBean(logDto, EsbServiceLogEntity.class);
            saveLog(logEntity);
        } catch (Exception e1) {
            LOGGER.warn("综合服务service日志记录失败" + e);
        }
    }
}
