package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserSessionDto extends Entity{
	private Long id;
	
    private Long userId;

    private String code;

    private Long createTime;

    private Long updateTime;

    private String channel;

    private String product;
    
}