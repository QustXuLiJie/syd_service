package com.mobanker.shanyidai.esb.common.packet;

import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;

import java.io.Serializable;

/**
 * @author R.Core
 * @Time 2016-12-22
 * @desc   调用第三方服务返回结果验证
 */
public final class CallResultUtil {
    /**
     * 返回成功.
     */
    public static final String RESPONSE_OK = "1";
    /**
     * 返回成功.
     */
    public static final String RESPONSE_FAIL = "0";




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
            throw new EsbException(ErrorConstant.SERVICE_FAIL);
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
            throw new EsbException(ErrorConstant.SERVICE_FAIL);
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

}
