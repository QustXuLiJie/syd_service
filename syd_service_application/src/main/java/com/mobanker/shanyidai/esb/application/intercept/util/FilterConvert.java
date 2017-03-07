package com.mobanker.shanyidai.esb.application.intercept.util;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.shanyidai.dubbo.dto.log.BaseLogDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.EsbSystemEnum;
import com.mobanker.shanyidai.esb.common.constants.ReqTypeEnum;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;

import java.util.Date;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/13 10:54
 */
public class FilterConvert {

    /**
     * @param packageName
     * @param className
     * @param methodName
     * @param startTime
     * @return com.mobanker.shanyidai.dubbo.dto.log.BaseLogDto
     * @description 封装DubboLog实体参数
     * @author hantongyang
     * @time 2017/2/7 10:45
     */
    public static <T> T initBaseLogDto(String packageName, String className, String methodName, Date startTime, Class<T> clazz) {
        try {
            BaseLogDto dto = (BaseLogDto) clazz.newInstance();
            dto.setPackageName(packageName); //包名
            dto.setClassName(className); //类名
            dto.setMethodName(methodName); //方法名
            dto.setReqType(ReqTypeEnum.DUBBO.getType());
            dto.setReqStartTime(startTime);
            return (T)dto;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param dto
     * @return void
     * @description 初始化访问时间
     * @author hantongyang
     * @time 2017/2/7 11:28
     * @method setDubboMonitorEnd
     */
    public static void setDubboMonitorEnd(BaseLogDto dto) {
        dto.setReqEndTime(new Date());
        dto.setDuration(dto.getReqEndTime().getTime() - dto.getReqStartTime().getTime());
        dto.setCreateTime(new Date());
        dto.setUpdateTime(new Date());
    }

    /**
     * @description 初始化错误信息
     * @author hantongyang
     * @time 2017/2/7 16:28
     * @method setErrorInfo
     * @param dto
     * @return void
     */
    public static void setErrorInfo(BaseLogDto dto, Exception e){
        if(e instanceof EsbException){
            EsbException exception = (EsbException) e;
            dto.setErrorCode(exception.errCode);
            dto.setErrorResult(exception.message);
        }else{
            dto.setErrorCode(ErrorConstant.SYSTEM_FAIL.getCode());
            //将堆栈信息转换成流保存到库中 R.Core 添加公用获取堆栈方法
            dto.setErrorResult(CommonUtil.getStackTrace(e));
        }
    }

    /**
     * @description 初始化成功返回信息
     * @author hantongyang
     * @time 2017/2/7 16:29
     * @method setSuccessInfo
     * @param dto
     * @param result
     * @return void
     */
    public static void setSuccessInfo(BaseLogDto dto, Object result){
        //封装返回结果
        if (result != null) {
            //判断结果是否是ResponseEntity，如果不是则表示接口是成功响应，直接封装成JSON对象，如果是ResponseEntity，需要判断响应结果
            if(result instanceof ResponseEntity){
                //是ResponseEntity对象，需要判断响应结果
                ResponseEntity entity = (ResponseEntity) result;
                if (ResponseBuilder.isSuccess(entity)) {
                    dto.setIsSuccess(EsbSystemEnum.DB_STATUS_SUCCESS.getValue());
                    dto.setSuccessResult(JSONObject.toJSONString(entity.getData()));
                } else {
                    dto.setIsSuccess(EsbSystemEnum.DB_STATUS_FAILED.getValue());
                    dto.setErrorCode(entity.getError());
                    dto.setErrorResult(entity.getMsg());
                }
            }else{
                //不是ResponseEntity，表示接口是成功响应，直接封装成JSON对象
                dto.setIsSuccess(EsbSystemEnum.DB_STATUS_SUCCESS.getValue());
                dto.setSuccessResult(JSONObject.toJSONString(result));
            }
        } else {
            dto.setIsSuccess(EsbSystemEnum.DB_STATUS_FAILED.getValue());
            dto.setErrorCode(ErrorConstant.SERVICE_FAIL.getCode());
            dto.setErrorResult(ErrorConstant.SERVICE_FAIL.getDesc());
        }
    }
}
