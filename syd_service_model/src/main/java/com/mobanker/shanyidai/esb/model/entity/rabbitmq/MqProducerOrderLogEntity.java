package com.mobanker.shanyidai.esb.model.entity.rabbitmq;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hantongyang
 * @description 订单中心回调实体
 * @time 2017/1/20 16:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_log_mq_producer_order")
public class MqProducerOrderLogEntity extends BaseEntity {
    private Long id; //ID
    private String messageId; //消息ID
    private String queueName; //生成借款单队列名称
    private String systemName; //系统名称
    private String mqType; //消息类型，1:rabbitmq,2:activemq,3:kafka

    private Long userId; //用户ID
    private String userName; //真实姓名
    private String phone; //手机号
    private String mobileInfo; //用户手机信息
    //产品模板相关信息
    private String productId; //产品模板ID
    private BigDecimal borrowRate; //借款利率
    private BigDecimal borrowLimit; //额度
    private String chargesRule; //收费规则，前置、后置
    private BigDecimal periodFeeApr; //固定费用
    private Integer minDays; //最小逾期天数
    private Integer maxDays; //最大逾期天数
    private BigDecimal lateReminder; //逾期滞纳金
    private BigDecimal lateFee; //逾期费率
    //借款单相关信息
    private Long borrowId; //流水单号
    private BigDecimal borrowAmount; //借款金额 必填
    private Integer borrowDays; //借款天数 必填
    private Date borrowTime; //借款时间 必填
    private String captcha; //验证码  必填
    //银行信息
    private String bankCard; //入账银行 必填
    private String bankName; //银行名称
    private Long provinceId; //省编码
    private Long cityId; //市编码
    private String bankBranchName; //银行卡开户行名称
    //系统信息
    private String code; //用户sessionCode
    private String ip;
    private String product;
    private String channel;
    private String version;

    private String borrowType; //借款类型
    private Integer period; //借款期数
    private Integer periodDays; //每期天数
    private String status; //状态
}
