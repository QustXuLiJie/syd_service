package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.shanyidai.dubbo.dto.auth.CreditInfoParamsDto;

import java.util.Map;

/**
 * @desc: 用户信用相关
 * @author: Richard Core
 * @create time: 2017/1/23 19:33
 */
public interface UserCreditInfoDubbo {
    /**
     * @param paramsDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 查询芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:55
     * @method getAlipayInfoByUserId
     */
    public Map<String, String> getAlipayInfoByUserId(CreditInfoParamsDto paramsDto);

    /**
     * @param paramsDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 保存芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:59
     * @method saveCreditInfoByUserId
     */
    public Map<String, String> saveCreditInfoByUserId(CreditInfoParamsDto paramsDto);
}
