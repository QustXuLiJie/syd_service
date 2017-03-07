/**
 * 
 */
package com.mobanker.shanyidai.esb.middleware.rabbitmq.producer;

import com.alibaba.fastjson.JSON;
import com.mobanker.framework.rabbitmq.RabbitmqProducerProxy;
import com.mobanker.framework.tracking.EE;
import com.mobanker.shanyidai.esb.common.constants.BorrowEnum;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.model.dto.rabbitmq.BorrowProducerMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * 订单中心RabbitiMQ处理
 * 
 * @author chenjianping
 * @data 2017年1月16日
 */
public class BorrowHandleProducer {
	public static final Logger logger = LogManager.getSlf4jLogger(BorrowHandleProducer.class);

	private RabbitmqProducerProxy rabbitmqProducer;

	public void setRabbitmqProducer(RabbitmqProducerProxy rabbitmqProducer) {
		this.rabbitmqProducer = rabbitmqProducer;
	}
	
	/**
	 * 订单进件MQ通知
	 * @author chenjianping
	 * @data 2017年1月16日
	 * @Version V1.0
	 * @throws EsbException
	 */
	public void borrowNotifyMQ(BorrowProducerMessage messageBean) throws EsbException{
		logger.info("=======================发送订单开始");
		try {
			String borrowInfo = JSON.toJSONString(messageBean);
			Message message = new Message(borrowInfo.getBytes(),new MessageProperties());
			//保存大数据 // TODO: 2017/2/13
//			BigDataClient.saveBigdata(message, EsbSystemEnum.PRODUCT.getValue(), "borrowNotifyMQ");
			//发送消息
			rabbitmqProducer.send(BorrowEnum.BORROW_ROUTING_KEY.getValue(), message);
			logger.info("=======================发送订单成功，push：" + borrowInfo);
		} catch (Exception e) {
			logger.error("=======================发订单失败："+e.getMessage());
			EE.logEvent("Monitor_SYD_Rabbitmq_Producer", "ERROR-borrowNotifyMQ"+e.getMessage());
		}
	}

}
