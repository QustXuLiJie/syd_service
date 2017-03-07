package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.mobanker.cust.creditinfo.contract.manager.DubboCreditInfoManager;
import com.mobanker.cust.creditinfo.contract.params.CreditInfoParams;
import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.shanyidai.dubbo.dto.auth.CreditInfoParamsDto;
import com.mobanker.shanyidai.esb.call.dubbo.UserCreditInfoDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @desc: 用户信用信息
 * @author: Richard Core
 * @create time: 2017/1/23 19:34
 */
@Service
public class UserCreditInfoDubboImpl implements UserCreditInfoDubbo {
    public static final Logger logger = LogManager.getSlf4jLogger(UserCreditInfoDubboImpl.class);
    @Resource
    private DubboCreditInfoManager dubboCreditInfoManager;

    /**
     * @param paramsDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 查询芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:55
     * @method getAlipayInfoByUserId
     */
    @Override
    public Map<String, String> getAlipayInfoByUserId(CreditInfoParamsDto paramsDto) {
        if (paramsDto == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        if (paramsDto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        if (StringUtils.isBlank(paramsDto.getType())) {
            throw new EsbException(ErrorConstant.PARAMS_PRODUCT);
        }
        if (paramsDto.getFields() == null || paramsDto.getFields().isEmpty()) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        CreditInfoParams params = BeanUtil.cloneBean(paramsDto, CreditInfoParams.class);
        params.setType("shoujidai");
        ResponseEntity<Map<String, String>> responseEntity = null;
        try {
            responseEntity = dubboCreditInfoManager.getAlipayInfoByUserId(params);
        } catch (Exception e) {
            logger.error("查询芝麻分异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), e.getMessage());
        }
        if (!CallResultUtil.isSuccess(responseEntity)) {
            logger.warn("查询芝麻分异常", responseEntity);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), responseEntity.getMsg());
        }
        return responseEntity.getData();
    }

    /**
     * @param paramsDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 保存芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:59
     * @method saveCreditInfoByUserId
     */
    @Override
    public Map<String, String> saveCreditInfoByUserId(CreditInfoParamsDto paramsDto) {
        if (paramsDto == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        if (paramsDto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        if (StringUtils.isBlank(paramsDto.getType())) {
            throw new EsbException(ErrorConstant.PARAMS_PRODUCT);
        }
        if (paramsDto.getSaveFields() == null || paramsDto.getSaveFields().isEmpty()) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        if (paramsDto.getCommonFields() == null || paramsDto.getCommonFields().isEmpty()) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        CreditInfoParams params = BeanUtil.cloneBean(paramsDto, CreditInfoParams.class);
        params.setType("shoujidai");
        ResponseEntity<Map<String, String>> responseEntity = null;
        try {
            responseEntity = dubboCreditInfoManager.saveCreditInfoByUserId(params);
        } catch (Exception e) {
            logger.error("保存芝麻分异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), e.getMessage());
        }
        if (!CallResultUtil.isSuccess(responseEntity)) {
            logger.error("调用保存芝麻分基础服务出错", responseEntity);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), responseEntity.getMsg());
        }
        return responseEntity.getData();
    }

}
