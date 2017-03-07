package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.shanyidai.dubbo.dto.payback.PayResultDto;
import com.mobanker.shanyidai.esb.call.dubbo.FinanceDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import com.mobanker.tkj.cw.dubboapi.biz.OutApiManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/1/10 9:49
 */
@Component
public class FinanceDubboImpl implements FinanceDubbo {
    public static final Logger logger = LogManager.getSlf4jLogger(FinanceDubboImpl.class);
    @Resource
    private OutApiManager outApiManager;

    /**
     * @param orderId
     * @return java.lang.Object
     * @description 获取还款信息
     * @author Richard Core
     * @time 2017/1/10 11:58
     * @method getBorrowRepayDetail
     */
    public Object getBorrowRepayDetail(String orderId) {
        if (StringUtils.isBlank(orderId)) {
            throw new EsbException(ErrorConstant.ERROR_BORROW_NID_NULL);
        }
        ResponseEntity result = null;
        try {
            result = outApiManager.getBorrowRepayDetail(orderId);
        } catch (Exception e) {
            logger.error("获取财务借款详情失败", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        //经与财务人员确认 财务接口判断只判断status是否是1
        if (!CallResultUtil.isFinished(result)) {
            logger.warn("获取财务借款详情失败", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        return result.getData();
//        return null;
    }

    /**
     * @param borrowNid
     * @param repayType
     * @return com.mobanker.shanyidai.dubbo.dto.payback.PayResultDto
     * @description 查询支付结果
     * @author Richard Core
     * @time 2017/1/23 10:54
     * @method getRepayStatus
     */
    @Override
    public PayResultDto getRepayStatus(String borrowNid, String repayType) {
        if (StringUtils.isBlank(borrowNid)) {
            throw new EsbException(ErrorConstant.ERROR_BORROW_NID_NULL);
        }
        ResponseEntity responseEntity = null;
        try {
            responseEntity = outApiManager.getRepayStatus(borrowNid, repayType);
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.REPAY_ERROR.getCode(), e.getMessage(), e);
        }
        if (!CallResultUtil.isFinished(responseEntity)) {
            logger.warn("查询财务系统支付结果失败，", responseEntity.getError() + responseEntity.getMsg());
            throw new EsbException(ErrorConstant.REPAY_ERROR.getCode(), responseEntity.getMsg());
        }
        Object data = responseEntity.getData();
        if (data == null) {
            throw new EsbException(ErrorConstant.pay_result_error.getCode(), "查询支付结果失败，财务系统没有返回支付结果");
        }
       return JSONObject.parseObject(JSONObject.toJSON(data).toString(), PayResultDto.class);
//        return (PayResultDto) data;
    }
}
