package com.mobanker.shanyidai.dubbo.dto.payback;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Description:还款参数
 *
 * @author R.Core
 *         Create date: 2017年1月17日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class RepayParamDto extends Entity {

    private Long userId;//用户id
    private String repayUid;//还款渠道
    private String type;//还款产品
//    private String channel;//还款产品渠道 entity中取
    private String debitcardNum;//还款银行卡号
    private String frontUrl;//回调接口地址
    private String period;//还款期数
    private String spbillCreateIp;//APP和网页支付提交用户端ip
    private String openId;//用户在商户appid下的唯一标识
    private String borrowNid;//借款单号，查询支付结果使用
}
