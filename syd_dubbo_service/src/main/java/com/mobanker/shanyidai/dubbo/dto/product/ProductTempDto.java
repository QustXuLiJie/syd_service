package com.mobanker.shanyidai.dubbo.dto.product;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/30 14:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ProductTempDto extends Entity {
    private String id;
    private Integer period; //分期类型
    private String merchant; //商户
    private String channel; //渠道
    private String appVersion; //当前版本号
    private BigDecimal periodFee; //服务费率
    private BigDecimal accrualFee; //利息费率
    private BigDecimal lateReminder; //逾期滞纳金
    private BigDecimal lateFee; //逾期费率
    private String limitAmount; //额度范围
    private String chargesRule; //收费规则
    private BigDecimal periodFeeApr; //固定费用
    private Integer minDays; //最小逾期天数
    private Integer maxDays; //最大逾期天数

}
