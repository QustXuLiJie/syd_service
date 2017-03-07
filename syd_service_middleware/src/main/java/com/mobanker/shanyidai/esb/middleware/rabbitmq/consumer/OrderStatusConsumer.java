/**
 * 
 */
package com.mobanker.shanyidai.esb.middleware.rabbitmq.consumer;

import com.mobanker.framework.rabbitmq.ChannelAwareMessageProcesser;
import com.mobanker.framework.tracking.EE;
import com.mobanker.shanyidai.esb.business.borrow.BorrowBusiness;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单状态监听处理(订单中心MQ)
 * 
 * @author chenjianping
 * @data 2017年1月17日
 */
@Service
public class OrderStatusConsumer implements ChannelAwareMessageProcesser {

	public static final Logger logger = LogManager.getSlf4jLogger(OrderStatusConsumer.class);

	@Resource
	private BorrowBusiness borrowBusiness;

	/**
	 * @description 借款单回调方法
	 * @author hantongyang
	 * @time 2017/1/19 20:16
	 * @method processMessage
	 * @param message
	 * @param channel
	 * @return void
	 */
	@Override
	public void processMessage(Message message, Channel channel) {
		String messageStr = null;
		String queueName = null;
		try {
			messageStr = new String(message.getBody());
			//将此处代码中的shanyidai定义为常量处理
			if (StringUtils.isNotBlank(messageStr)) {
				queueName = message.getMessageProperties().getConsumerQueue();
            }
			//保存大数据
// TODO: 2017/2/13 `
//			BigDataClient.saveBigdata(messageStr, EsbSystemEnum.PRODUCT.getValue(), "borrowProcessMQ");
			borrowBusiness.callBackBorrowOrder(messageStr, queueName);
		} catch (Exception e) {
			logger.error("=======================订单接收失败："+e.getMessage());
			EE.logEvent("Monitor_SYD_Rabbitmq_Consumer", "ERROR-borrowProcessMQ"+e.getMessage());
			throw e;
		}
	}

}
