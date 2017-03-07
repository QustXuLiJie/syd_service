/**
 * 
 */
package com.mobanker.shanyidai.esb.model.entity.borrow;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.mobanker.framework.entity.BaseEntity;

/**
 * 借款单实体对象
 * 
 * @author chenjianping
 * @data 2016年12月16日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_borrow_record")
public class BorrowEntity extends BaseEntity {

	private Long id;   //编号
	private Long userId;   //用户ID
	private String borrowNid;   //订单编号
	private BigDecimal borrowAmount;   //借款单金额
	private BigDecimal borrowRate;   //借款费率
	private BigDecimal borrowLimit;   //借款额度
	private Integer borrowPeriod;   //借款期数
	private Integer periodDays;   //每期天数
	private Date borrowTime;   //借款时间
	private String borrowStatus;   //借款状态
	private String borrowType;   //借款类型
	private Date borrowSuccessTime;   //借款成功时间
	private String bankCard;   //入账银行卡号
	private String bankName;   //入账银行名称
	private String bankBranchName;   //开户行名称
	private String captcha;   //验证码
	private String product;   //添加产品
	private String channel;   //添加渠道
	private String phone;   //用户手机号
	private String userPhone;   //用户手机信息
	private String remoteIp;   //用户IP
	private String userCode;   //用户CODE
	private Date createTime;   //创建时间
	private String createUser;   //创建人
	private Date updateTime;   //修改时间
	private String updateUser;   //修改人
	private String chargesRule;   //收费规则，前置、后置
	private BigDecimal periodFeeApr;   //固定费用
	private Integer minDays;   //最小逾期天数
	private Integer maxDays;   //最大逾期天数
	private BigDecimal lateReminder;   //逾期滞纳金
	private BigDecimal lateFee;   //逾期费率
	private String productModelId;   //产品模板ID
	private String userName;   //用户真实姓名
}
