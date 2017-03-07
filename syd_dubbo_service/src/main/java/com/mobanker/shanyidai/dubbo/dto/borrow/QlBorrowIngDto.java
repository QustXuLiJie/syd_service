package com.mobanker.shanyidai.dubbo.dto.borrow;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/10 16:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class QlBorrowIngDto extends Entity {
    private String borrowNid; //借款单号
    private Long addtime; //申请时间
    private int status; //借款状态：0审核中 1审核通过 2审核失败 3放款成功 4放款失败 5还款成功
    private Integer borrowType; //借款类型：6手机贷单期 7闪电贷 8现金分期 9同程 10大额贷 11应花分期 51 u族分期
    private String addProduct; //产品
    private String addChannel; //渠道
}
