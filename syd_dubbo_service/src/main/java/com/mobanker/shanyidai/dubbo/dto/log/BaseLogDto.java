package com.mobanker.shanyidai.dubbo.dto.log;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/10 18:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BaseLogDto extends Entity {
    private Long id;   //ID
    private String packageName;   //包名
    private String className;   //类名
    private String methodName;   //方法名
    private Date reqStartTime;   //请求时间
    private Date reqEndTime;   //请求结束时间
    private Long duration;   //请求时长
    private String reqParam;   //请求参数
    private String reqType;   //请求类型
    private String isSuccess;   //请求是否成功
    private String successResult;   //返回结果
    private String errorCode;   //异常编码
    private String errorResult;   //异常结果
    private Date createTime;   //创建日期
    private String createUser;   //创建人
    private Date updateTime;   //修改日期
    private String updateUser;   //修改人
}
