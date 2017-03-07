/**
 * 
 */
package com.mobanker.shanyidai.esb.model.dto.borrow;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 借款属性Bean
 * 
 * @author chenjianping
 * @data 2016年12月16日
 */
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class BorrowBean {
	/**
	 * 借款订单号
	 */
	private String borrowNid;
	/**
	 * 借款单状态
	 */
	private Integer borroStatus;
	/**
	 * 借款金额
	 */
	private Long borrowAmount;

	/**
	 * 借款期数（天数）
	 */
	private Integer borrowPeriod;

	/**
	 * 借款类型
	 */
	private String borrowType;

	/**
	 * 自有借款渠道 app
	 */
	private String channel;

	/**
	 * 第三方合作渠道
	 */
	private String fromChannel;

	/**
	 * 会话code
	 */
	private String code;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 借记卡号
	 */
	private String debitcardNum;

	/**
	 * 用户终端IP
	 */
	private String ip;

	/**
	 * 产品标记
	 */
	private String product;

	/**
	 * 短信验证码
	 */
	private String smsCode;

	/**
	 * 产品模板编号
	 */
	private Integer templateId;

	/**
	 * 用户编号
	 */
	private Long userId;

	/**
	 * 用户手机号
	 */
	private String userPhone;

	/**
	 * 终端版本号
	 */
	private String version;

	/**
	 * 操作系统类型 android/ios
	 */
	private String osType;

}
