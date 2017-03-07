/**
 * 
 */
package com.mobanker.shanyidai.esb.model.dto.rabbitmq;

import com.mobanker.framework.dto.BaseMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 监控订单中心状态通知MQ消息参数
 * 
 * @author chenjianping
 * @data 2017年1月17日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class OrderConsumerMessage extends BaseMessage {

	/** 用户ID */
	private String userId;
	/** 各系统的订单号 */
	private String orderNo;
	/** 订单号 */
	private String orderNid;
	/**
	 * 0 待审核 check_wait ， 1 信审成功 credit_yes，2 信审失败 credit_no， 3 客服电核成功
	 * call_yes，4 客服电核失败 call_no ，5 放款成功 loan_yes， 6 放款失败 loan_no 8还款成功
	 * repay_yes
	 */
	private String status;
	/** 借款申请金额 */
	private String amount;
	/** 借款类型6手机贷大额，7手机贷小额，8手机贷分期 */
	private String borrowType;
	/** 期数 */
	private String period;
	/** 每期天数 */
	private String periodDays;
	/** 添加产品 */
	private String addProduct;
	/** 添加渠道 */
	private String addChannel;
	/** 版本号 */
	private String appversion;
	/** 是否为测试单 */
	private String isTest;
	/** 产品id */
	private String productModelId;
	/** 是否逾期 */
	private String isOverdue;
	/** 逾期时间 */
	private String overdueDay;
	/** 借款申请时间 */
	private String addtime;
	/** 借款成功时间 */
	private String borrowTime;
	/** 最后修改时间 */
	// private Date updateTime;
}
