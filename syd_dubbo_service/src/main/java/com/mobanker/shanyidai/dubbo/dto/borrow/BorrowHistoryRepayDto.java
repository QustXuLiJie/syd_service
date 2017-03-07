package com.mobanker.shanyidai.dubbo.dto.borrow;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BorrowHistoryRepayDto extends Entity{
	//	userId	Long	N	用户ID
	private Long userId;
	//	borrowNid	String	N	借款单号
	private String borrowNid;
	//	period	Integer	N	期数
	private Integer period;
	// 每期天数
	private Integer periodDays;
	//	repayStatus	Integer	N	当前状态0未还款，1已还款
	private Integer status;
	//	lateDays	Integer	N	逾期天数
	private Integer lateDays;
	//	repayTime	Long	N	应还时间
	private Long repayTime;
	//	repayCapital	Float	N	应还本
	private Float repayCapital;
	//	repayPoundage	Float	N	应还手续费
	private Float repayPoundage;
	//	lateInterest	Float	N	逾期罚息
	private Float lateInterest;
	//	lateReminder	Float	N	滞纳金
	private Float lateReminder;
	//	repayAmount	Float	N	当前应还总金额
	private Float repayAmount;
	//	repayYesTime	Long	Y	实还时间
	private Long repayYesTime;
	//	repayYesAmount	Float	Y	实还金额
	private Float repayYesAmount;
	//	forcedDeduct 	Long	Y	强扣时间
	private Long forcedDeduct;
	// 减免金额
	private Float exemptionAmount;
	// 挂账金额
	private Float partAccountSum;
}
