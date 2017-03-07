package com.mobanker.shanyidai.esb.model.entity.user;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserSessionEntity extends BaseEntity{
    private Long id;
    private Long userId;
    private String code;
    private Date createTime;
    private Date updateTime;
    private String channel;
    private String product;
}