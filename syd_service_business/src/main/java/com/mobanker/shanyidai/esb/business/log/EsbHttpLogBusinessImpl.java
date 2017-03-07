package com.mobanker.shanyidai.esb.business.log;

import com.mobanker.shanyidai.esb.dao.log.EsbHttpLogMapper;
import com.mobanker.shanyidai.esb.model.entity.log.EsbHttpLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc:综合服务系统访问第三方HTTP协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:17
 */
@Service
public class EsbHttpLogBusinessImpl implements EsbHttpLogBusiness {
    @Autowired
    private EsbHttpLogMapper esbHttpLogMapper;

    /**
     * @param logEntity
     * @return void
     * @description 综合服务系统访问第三方HTTP协议访问日志
     * @author Richard Core
     * @time 2017/2/6 16:38
     * @method saveLog
     */
    @Override
    public void saveLog(EsbHttpLogEntity logEntity) {
        esbHttpLogMapper.insert(logEntity);
    }
}
