package com.mobanker.shanyidai.esb.common.packet;

import com.dianping.cat.message.Event;
import com.mobanker.framework.tracking.EE;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;

import java.io.Serializable;

/**
 * @author R.Core
 * @Time 2016-12-22
 * @desc 封装综合服务返回值
 */
public final class ResponseBuilder {

    public static final Logger logger = LogManager.getSlf4jLogger(ResponseBuilder.class);

    /**
     * 返回成功.
     */
    public static final String RESPONSE_OK = "1";
    /**
     * 返回成功.
     */
    public static final String RESPONSE_FAIL = "0";


    /**
     * @param errorConstant
     * @param e
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 构造一个失败响应.
     * @author Richard Core
     * @time 2016/12/22 15:09
     * @method buildErrorResponse
     */
    public static ResponseEntity errorResponse(ErrorConstant errorConstant, Throwable e, String className, String functionName) {
        if (e instanceof EsbException) {
            EsbException ee = (EsbException) e;
            //记录鹰眼
            EE.logEvent("Monitor_Esb_Exception", className + "-" + ee.message, Event.SUCCESS, functionName);
            return errorResponse(ee.errCode, ee.message);
        } else {
            //记录鹰眼
            EE.logError(className + "." + functionName + "发生运行时异常", e);
            return errorResponse(errorConstant.getCode(), e.getMessage());
        }
    }

    /**
     * @param errorCode 失败错误编码
     * @param errorMsg  失败错误说明.
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description
     * @author Richard Core
     * @time 2016/12/22 15:09
     * @method buildErrorResponse
     */
    public static ResponseEntity errorResponse(String errorCode, String errorMsg) {
        ResponseEntity entity = new ResponseEntity();
        entity.setStatus(RESPONSE_FAIL);
        entity.setError(errorCode);
        entity.setMsg(errorMsg);
        return entity;
    }

    /**
     * @param errorConstant
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 构造一个失败响应.
     * @author Richard Core
     * @time 2016/12/22 15:10
     * @method buildErrorResponse
     */
    public static ResponseEntity errorResponse(ErrorConstant errorConstant, String className, String functionName) {
        //记录鹰眼
        EE.logEvent("Monitor_Esb_Exception", className + "-" + errorConstant.getDesc(), Event.SUCCESS, functionName);

        return errorResponse(errorConstant.getCode(), errorConstant.getDesc());
    }


    /**
     * @param
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 构造一个正常响应. 通常用于没有返回值的接口
     * @author Richard Core
     * @time 2016/12/22 15:15
     * @method buildNormalResponse
     */
    public static ResponseEntity normalResponse() {
        //规范：所有正常请求(status="1",error="00000000"),code与pageCount属性被废弃.
        return new ResponseEntity(RESPONSE_OK, ErrorConstant.SUCCESS.getCode());
    }

    /**
     * @param data 接口返回值
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 构造一个正常响应. 通常用于有返回值的接口
     * @author Richard Core
     * @time 2016/12/22 15:15
     * @method buildNormalResponse
     */
    public static <T> ResponseEntity normalResponse(T data) {
        ResponseEntity entity = normalResponse();
        entity.setData(data);
        return entity;
    }


    /**
     * @param entity
     * @return java.lang.Boolean
     * @description 判断接口是否通讯成功
     * @author Richard Core
     * @time 2016/12/22 15:23
     * @method isFinished
     */
    public static boolean isFinished(ResponseEntity entity) {
        if (entity == null) {
            throw new EsbException(ErrorConstant.SYSTEM_FAIL);
        }
        return RESPONSE_OK.equals(entity.getStatus());
    }

    /**
     * @param entity
     * @return boolean
     * @description 接口通讯成功，并且业务处理成功
     * @author Richard Core
     * @time 2016/12/22 15:26
     * @method isSuccess
     */
    public static boolean isSuccess(ResponseEntity entity) {
        if (entity == null) {
            throw new EsbException(ErrorConstant.SYSTEM_FAIL);
        }
        return isFinished(entity) && ErrorConstant.SUCCESS.getCode().equals(entity.getError());
    }

    /**
     * @param entity
     * @return T
     * @description 获取接口返回值
     * @author Richard Core
     * @time 2016/12/22 15:27
     * @method getEntity
     */
    public static <T extends Serializable> T getEntity(ResponseEntity entity, Class<T> clazz) {
        if (entity == null) {
            return null;
        }
        if (isSuccess(entity)) {
            return (T) entity.getData();
        }
        return null;
    }
    /**
     * @param responseEntity 基础服务返回的responseEntity
     * @param errorConstant 有问题时抛出的异常码
     * @param checkFinishOnly 是否只验证通讯是否成功
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 检查服务返回值
     * @author Richard Core
     * @time 2017/1/12 13:40
     * @method checkResponseEntity
     */
    public static ResponseEntity checkResponseEntity(Object responseEntity, ErrorConstant errorConstant, boolean checkFinishOnly) {
        if (errorConstant == null) {
            errorConstant = ErrorConstant.SERVICE_FAIL;
        }
        ResponseEntity clonedRsp = BeanUtil.cloneBean(responseEntity, ResponseEntity.class);
        //只验证调用是否成功
        if (checkFinishOnly) {
            if (!ResponseBuilder.isFinished(clonedRsp)) {
                logger.warn("调用Dubbo服务异常", clonedRsp);
                throw new EsbException(errorConstant.getCode(), clonedRsp.getMsg());
            }
            return clonedRsp;
        }
        //验证调用成功，验证业务操作成功
        if (!ResponseBuilder.isSuccess(clonedRsp)) {
            logger.warn("调用Dubbo服务异常", clonedRsp);
            throw new EsbException(errorConstant.getCode(), clonedRsp.getMsg());
        }
        return clonedRsp;
    }
    public static ResponseEntity checkResponseEntity(Object responseEntity, ErrorConstant errorConstant) {
        return checkResponseEntity(responseEntity, errorConstant, false);
    }
    public static ResponseEntity checkResponseEntity(Object responseEntity, boolean checkFinishOnly) {
        return checkResponseEntity(responseEntity, ErrorConstant.SERVICE_FAIL, checkFinishOnly);
    }
    public static ResponseEntity checkResponseEntity(Object responseEntity) {
        return checkResponseEntity(responseEntity, ErrorConstant.SERVICE_FAIL, false);
    }
}
