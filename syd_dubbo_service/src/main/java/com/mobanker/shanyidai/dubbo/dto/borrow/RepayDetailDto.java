package com.mobanker.shanyidai.dubbo.dto.borrow;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class RepayDetailDto extends Entity{

	private String amount;
	private String borrowNid;
	private String channelName;
	private String repayStatus;
	private String repayTime;
	private String weixinFee;
}
