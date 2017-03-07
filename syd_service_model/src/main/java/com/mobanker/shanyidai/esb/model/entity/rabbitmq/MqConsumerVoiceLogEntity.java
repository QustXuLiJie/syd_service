package com.mobanker.shanyidai.esb.model.entity.rabbitmq;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/18 16:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_log_mq_consumer_voice")
public class MqConsumerVoiceLogEntity extends BaseEntity {
    private Long id;   //编码
    private String createUser;   //创建人
    private Date createTime;   //创建时间
    private String updateUser;   //修改人
    private Date updateTime;   //修改时间
    private String status;   //状态， 0失败，1成功
    private String queueName;   //生成借款单队列名称
    private String mqType;   //消息类型，1:rabbitmq,2:activemq,3:kafka
    private String messageStr;   //消息内容
    private String errorMessage;   //失败异常信息
}
