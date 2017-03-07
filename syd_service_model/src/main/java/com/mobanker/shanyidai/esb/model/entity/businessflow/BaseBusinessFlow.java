package com.mobanker.shanyidai.esb.model.entity.businessflow;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/28 16:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BaseBusinessFlow extends BaseEntity{

    private String reqMethod;   //请求方法名
    private Date reqStartTime;   //请求开始时间
    private Date reqEndTime;   //请求结束时间
    private Integer duration;   //请求时长
    private String reqParam;   //请求参数
    private String errorCode;   //异常编码
    private String errorResult;   //异常结果
    private String result;   //返回结果
    private String isSuccess;   //是否请求成功
    private String reqDesc; //请求功能描述
}
