package com.mobanker.shanyidai.esb.business.log;

import com.mobanker.shanyidai.esb.dao.log.EsbDubboLogMapper;
import com.mobanker.shanyidai.esb.model.entity.log.EsbDubboLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc:综合服务系统访问第三方dubbo协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:15
 */
@Service
public class EsbDubboLogBusinessImpl implements EsbDubboLogBusiness {
    @Autowired
    private EsbDubboLogMapper esbDubboLogMapper;

    /**
     * @param logEntity
     * @return void
     * @description 综合服务系统访问第三方dubbo协议访问日志
     * @author Richard Core
     * @time 2017/2/6 16:36
     * @method saveLog
     */
    @Override
    public void saveLog(EsbDubboLogEntity logEntity) {
        esbDubboLogMapper.insert(logEntity);
    }
}
