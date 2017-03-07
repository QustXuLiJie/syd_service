package com.mobanker.shanyidai.esb.business.log;

/**
 * @desc: mq consumer log
 * @author: Richard Core
 * @create time: 2017/2/18 16:13
 */
public interface EsbMqConsumerLogBusiness {
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
    public void saveMqVoiceConsumerLog(String messageStr, String queueName, Throwable e);
}
