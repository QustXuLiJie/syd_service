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
@Table(name = "syd_log_mq_consumer_order")
public class MqConsumerOrderLogEntity extends BaseEntity {
    private Long id; //ID
    private String status; //状态
    private String channel; //渠道
    private String product; //产品
    private String version; //版本
    private String remoteIp; //IP
    private Long userId; //用户ID
    private String messageId; //消息ID
    private String systemName; //系统名称
    private String queueName; //生成借款单队列名称
    private Long borrowId; //借款单流水号，闪宜贷系统生成的借款单号
    private String borrowNid; //借款单号，全产品唯一单号
    private String borrowType; //借款类型
    private String borrowStatus; //借款状态 组合后的借款单状态
    private BigDecimal borrowAmount; //借款金额
    private Date borrowTime; //借款时间
    private Integer period; //借款期数
    private Integer periodDays; //每期天数
    private String productModelId; //产品模板ID
    private String isTest; //是否白名单
    private String isOverdue; //是否逾期
    private String overdueDay; //逾期天数
    private String mqType; //消息类型，1:rabbitmq,2:activemq,3:kafka
}
