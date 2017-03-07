package com.mobanker.shanyidai.esb.call.http.impl;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.framework.utils.HttpClientUtils;
import com.mobanker.shanyidai.esb.call.http.BankCardHttp;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @desc: 银行卡相关的rest接口调用封装
 * @author: Richard Core
 * @create time: 2017/1/3 15:32
 */
@Service
public class BankCardHttpImpl implements BankCardHttp {
    public static final Logger logger = LogManager.getSlf4jLogger(BankCardHttpImpl.class);

    /**
     * @param reqURL
     * @param params
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 银行卡认证
     * @author Richard Core
     * @time 2017/1/3 16:23
     * @method bankCardVerify
     */
    @Override
    public ResponseEntity bankCardVerify(String reqURL,Map<String, String> params) {
        try {
            ResponseEntity responseEntity = CommonUtil.doPost(reqURL, params, null, ErrorConstant.BANK_CARD_VERIFY);
            if(responseEntity == null){
                throw new EsbException(ErrorConstant.BANK_CARD_VERIFY);
            }
            //如果有异常，需要将异常编码替换成闪宜贷编码
//            if(!ResponseBuilder.isSuccess(responseEntity)){
//                logger.warn("======调用银行卡认证服务异常：" + responseEntity);
//                responseEntity.setError(ErrorConstant.SERVICE_FAIL.getCode());
//            }
            return responseEntity;
        } catch (Exception e) {
            logger.error("======调用银行卡认证服务异常：" + e);
            throw new EsbException(ErrorConstant.BANK_CARD_VERIFY, e);
        }
    }
}
