package com.mobanker.shanyidai.esb.model.entity.log;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * @desc:综合服务系统访问第三方HTTP协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 15:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_log_esb_http")
public class EsbHttpLogEntity extends BaseEntity {
    private String packageName;   //包名
    private String className;   //类名
    private String methodName;   //方法名
    private Date reqStartTime;   //请求时间
    private Date reqEndTime;   //请求结束时间
    private Long duration;   //请求时长
//    private String reqUrl;   //请求URL
    private String reqParam;   //请求参数
    private String reqType;   //请求类型
    private String isSuccess;   //请求是否成功
    private String successResult;   //返回结果
    private String errorCode;   //异常编码
    private String errorResult;   //异常结果

}
