package com.mobanker.shanyidai.esb.call.http;

import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.Map;

/**
 * @desc: 银行卡相关的rest接口调用封装
 * @author: Richard Core
 * @create time: 2017/1/3 15:32
 */
public interface BankCardHttp {
    /**
     * @param reqURL
     * @param params
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 银行卡认证
     * @author Richard Core
     * @time 2017/1/3 16:23
     * @method bankCardVerify
     */
    public ResponseEntity bankCardVerify(String reqURL, Map<String, String> params);
}
