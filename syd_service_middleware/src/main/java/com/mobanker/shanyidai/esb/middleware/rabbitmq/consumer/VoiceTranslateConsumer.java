/**
 *
 */
package com.mobanker.shanyidai.esb.middleware.rabbitmq.consumer;

import com.mobanker.framework.rabbitmq.ChannelAwareMessageProcesser;
import com.mobanker.framework.tracking.EE;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceTranslateResultDto;
import com.mobanker.shanyidai.esb.business.auth.RecognizeBusiness;
import com.mobanker.shanyidai.esb.business.log.EsbMqConsumerLogBusiness;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 语音识别解析结果消费 （认证系统）
 *
 * @author 郭宇超
 * @data 2017年2月14日
 */
@Service
public class VoiceTranslateConsumer implements ChannelAwareMessageProcesser {

    public static final Logger logger = LogManager.getSlf4jLogger(VoiceTranslateConsumer.class);

    @Resource
    private RecognizeBusiness recognizeBusiness;
    @Resource
    private EsbMqConsumerLogBusiness esbMqConsumerLogBusiness;



    /**
     * @param message
     * @param channel
     * @return void
     * @description 认证系统语音解析结果MQ消费
     * @author Richard Core
     * @time 2017/2/14 10:42
     * @method processMessage
     */
    @Override
    public void processMessage(Message message, Channel channel) {
        logger.info("=====语音解析结果MQ消费======");
        String messageStr = null;
        String queueName = null;
        try {
            messageStr = new String(message.getBody());
            if (StringUtils.isBlank(messageStr)) {
                logger.info("=====语音解析结果MQ消费 队列消息内容为空======");
                return;
            }
            queueName = message.getMessageProperties().getConsumerQueue();
            //将此处代码中的shanyidai定义为常量处理
            VoiceTranslateResultDto resultDto = BeanUtil.parseJson(messageStr, VoiceTranslateResultDto.class);
            if (resultDto == null || StringUtils.isBlank(resultDto.getOrderNo())) {
                logger.warn("认证系统语音识别解析结果MQ通知信息为空" + messageStr);
            }
            //解析结果更新到数据库
            recognizeBusiness.saveVoiceTransResult(resultDto);
            //保存解析流水
            esbMqConsumerLogBusiness.saveMqVoiceConsumerLog(messageStr,queueName,null);
            //保存大数据
// TODO: 2017/2/13 `
//			BigDataClient.saveBigdata(messageStr, EsbSystemEnum.PRODUCT.getValue(), "borrowProcessMQ");
            //调用DUBBO实现其余业务逻辑
//			dubboBorrowService.callBackBorrowOrder(messageStr, queueName);
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error("=======================语音解析失败：" + e);
            EE.logEvent("Monitor_SYD_Rabbitmq_Consumer", "ERROR-borrowProcessMQ" + e.getMessage());
            //重新放回队列里
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);

            //保存解析流水
            esbMqConsumerLogBusiness.saveMqVoiceConsumerLog(messageStr,queueName,e);
        }
    }

}
