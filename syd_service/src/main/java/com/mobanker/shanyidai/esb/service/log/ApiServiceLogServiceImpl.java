package com.mobanker.shanyidai.esb.service.log;

import com.mobanker.shanyidai.dubbo.dto.log.ApiServiceLogDto;
import com.mobanker.shanyidai.dubbo.service.log.ApiServiceLogDubboService;
import com.mobanker.shanyidai.esb.business.log.ApiServiceLogBusiness;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.model.entity.log.ApiServiceLogEntity;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @desc:API系统service模块业务日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:13
 */
public class ApiServiceLogServiceImpl implements ApiServiceLogDubboService {
    public static final Logger LOG = LogManager.getSlf4jLogger(ApiServiceLogServiceImpl.class);
    @Resource
    private ApiServiceLogBusiness apiServiceLogBusiness;

    /**
     * @param logDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description API系统service模块业务日志
     * @author Richard Core
     * @time 2017/2/6 17:18
     * @method saveLog
     */
    @Override
    public ResponseEntity saveLog(ApiServiceLogDto logDto) {
        try {
            if (logDto == null) {
                throw new EsbException(ErrorConstant.PARAM_REQUIRED);
            }
            ApiServiceLogEntity logEntity = BeanUtil.cloneBean(logDto, ApiServiceLogEntity.class);
            apiServiceLogBusiness.saveLog(logEntity);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            LOG.warn("api工程service模块日志记录异常：" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @description 保存API业务流水
     * @author hantongyang
     * @time 2017/2/28 11:22
     * @method saveBusinessFlow
     * @param logDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity saveBusinessFlow(ApiServiceLogDto logDto) {
        try {
            if (logDto == null) {
                throw new EsbException(ErrorConstant.PARAM_REQUIRED);
            }
            ApiServiceLogEntity logEntity = BeanUtil.cloneBean(logDto, ApiServiceLogEntity.class);
            apiServiceLogBusiness.saveBusinessFlow(logEntity);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            LOG.warn("api工程service模块日志记录异常：" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }
}
