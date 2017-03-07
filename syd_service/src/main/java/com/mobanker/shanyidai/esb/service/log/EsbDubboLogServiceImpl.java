package com.mobanker.shanyidai.esb.service.log;

import com.mobanker.shanyidai.dubbo.dto.log.EsbDubboLogDto;
import com.mobanker.shanyidai.dubbo.service.log.EsbDubboLogDubboService;
import com.mobanker.shanyidai.esb.business.log.EsbDubboLogBusiness;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.model.entity.log.EsbDubboLogEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc:综合服务系统访问第三方dubbo协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:15
 */
@Service
public class EsbDubboLogServiceImpl implements EsbDubboLogDubboService {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(EsbDubboLogServiceImpl.class);
    @Resource
    private EsbDubboLogBusiness esbDubboLogBusiness;

    /**
     * @param logDto
     * @return void
     * @description 综合服务系统访问第三方dubbo协议访问日志
     * @author Richard Core
     * @time 2017/2/6 17:28
     * @method saveLog
     */
    @Override
    public void saveLog(EsbDubboLogDto logDto) {
        if (logDto == null) {
            LOGGER.warn("综合服务系统访问第三方dubbo协议访问日志-记录日志没有参数");
            return;
        }
        try {
            EsbDubboLogEntity logEntity = BeanUtil.cloneBean(logDto, EsbDubboLogEntity.class);
            esbDubboLogBusiness.saveLog(logEntity);
        } catch (Exception e) {
            LOGGER.warn("综合服务系统访问第三方dubbo协议访问日志异常" + e);
        }
    }
}
