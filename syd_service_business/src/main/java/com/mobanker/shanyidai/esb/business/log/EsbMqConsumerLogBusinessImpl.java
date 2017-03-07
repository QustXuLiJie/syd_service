package com.mobanker.shanyidai.esb.business.log;

import com.mobanker.shanyidai.esb.common.constants.SydConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import com.mobanker.shanyidai.esb.dao.rabbitmq.MqConsumerVoiceLogMapper;
import com.mobanker.shanyidai.esb.model.entity.rabbitmq.MqConsumerVoiceLogEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc: mq consumer 日志记录
 * @author: Richard Core
 * @create time: 2017/2/18 16:13
 */
@Service
public class EsbMqConsumerLogBusinessImpl implements EsbMqConsumerLogBusiness {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(EsbMqConsumerLogBusinessImpl.class);
    @Resource
    private MqConsumerVoiceLogMapper mqConsumerVoiceLogMapper;

    /**
     * @param messageStr
     * @param queueName
     * @param e
     * @return void
     * @description 语音解析mq消费日志记录
     * @author Richard Core
     * @time 2017/2/18 16:41
     * @method saveMqVoiceConsumerLog
     */
     @Override
    public void saveMqVoiceConsumerLog(String messageStr, String queueName, Throwable e) {
        try {
            MqConsumerVoiceLogEntity entity = new MqConsumerVoiceLogEntity();
            entity.setMessageStr(messageStr);
            entity.setQueueName(queueName);
            //设置异常信息
            if (e != null) {
                entity.setStatus(SydConstant.RSP_STATUS_FAIL);
                if (e instanceof EsbException) {
                    EsbException esb = (EsbException) e;
                    entity.setErrorMessage(esb.errCode + esb.message);
                }else{
                    entity.setErrorMessage(CommonUtil.getStackTrace(e));
                }
            }
            mqConsumerVoiceLogMapper.insert(entity);
        } catch (Exception e1) {
            LOGGER.warn("语音解析mq消费日志记录失败" + e);
        }
    }

}
