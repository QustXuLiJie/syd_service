package com.mobanker.shanyidai.esb.service.log;

import com.mobanker.shanyidai.dubbo.dto.log.EsbHttpLogDto;
import com.mobanker.shanyidai.dubbo.service.log.EsbHttpLogDubboService;
import com.mobanker.shanyidai.esb.business.log.EsbHttpLogBusiness;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.model.entity.log.EsbHttpLogEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc:综合服务系统访问第三方HTTP协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:17
 */
@Service
public class EsbHttpLogServiceImpl implements EsbHttpLogDubboService {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(EsbHttpLogServiceImpl.class);
    @Resource
    private EsbHttpLogBusiness esbHttpLogBusiness;


    /**
     * @param logDto
     * @return void
     * @description 综合服务系统访问第三方HTTP协议访问日志
     * @author Richard Core
     * @time 2017/2/6 17:41
     * @method saveLog
     */
    @Override
    public void saveLog(EsbHttpLogDto logDto) {

        if (logDto == null) {
            LOGGER.warn("综合服务系统访问第三方HTTP协议访问日志-记录日志没有参数");
            return;
        }
        try {
            EsbHttpLogEntity logEntity = BeanUtil.cloneBean(logDto, EsbHttpLogEntity.class);
            esbHttpLogBusiness.saveLog(logEntity);
        } catch (Exception e) {
            LOGGER.warn("综合服务系统访问第三方HTTP协议访问日志异常" + e);
        }
    }
}
