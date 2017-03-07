package com.mobanker.shanyidai.esb.call.http.impl;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.framework.utils.HttpClientUtils;
import com.mobanker.shanyidai.dubbo.dto.payback.OtherRepayParamDto;
import com.mobanker.shanyidai.dubbo.dto.payback.RepayParamDto;
import com.mobanker.shanyidai.esb.call.http.FinanceHttp;
import com.mobanker.shanyidai.esb.call.http.convert.FinanceHttpConvert;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc: 财务相关http服务
 * @author: Richard Core
 * @create time: 2017/1/16 20:10
 */
@Service
public class FinanceHttpImpl implements FinanceHttp {
    public static final Logger logger = LogManager.getSlf4jLogger(FinanceHttpImpl.class);

    /**
     * @param repayParamDto
     * @return java.lang.Object
     * @description 还款接口 一键还款和微信支付
     * @author Richard Core
     * @time 2017/1/17 10:28
     * @method addBillsAndPeriodRepay
     */
    @Override
    public ResponseEntity addBillsAndPeriodRepay(String restUrl, RepayParamDto repayParamDto) {
        //验证参数
        Map<String, String> params = FinanceHttpConvert.checkAndMapRepayParams(repayParamDto);
        //支付
        return post2FinanceHttp(restUrl, repayParamDto.getType(), params);
    }

    /**
     * @param restUrl
     * @param paramDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 百度钱包和易宝支付还款
     * @author Richard Core
     * @time 2017/1/22 14:47
     * @method addBillsAndOtherRepay
     */
    @Override
    public ResponseEntity addBillsAndOtherRepay(String restUrl, OtherRepayParamDto paramDto) {
        //验证参数
        Map<String, String> params = FinanceHttpConvert.checkAndMapOtherRepayParams(paramDto);
        //支付
        return post2FinanceHttp(restUrl, paramDto.getType(), params);
    }

    /**
     * @param restUrl
     * @param contentUid
     * @param params
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 调用财务系统支付
     * @author Richard Core
     * @time 2017/1/22 14:45
     * @method post2FinanceHttp
     */
    public ResponseEntity post2FinanceHttp(String restUrl, String contentUid, Map<String, String> params) {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-uid", contentUid);
        //支付
        try {
            ResponseEntity responseEntity = CommonUtil.doPost(restUrl, params, header, ErrorConstant.REPAY_ERROR);
            //如果接口返回值显示调用成功，但是error为空，则将SUCCESS的Code值赋给error
            if (responseEntity == null) {
                throw new EsbException(ErrorConstant.REPAY_ERROR.getCode(), "调用财务系统支付没有接收到支付结果");
            }
//            if (!ResponseBuilder.isFinished(responseEntity)) {
//                logger.warn("调用财务支付失败", responseEntity);
//                responseEntity.setError(ErrorConstant.REPAY_ERROR.getCode());
//            }else{
//                if (StringUtils.isBlank(responseEntity.getError())) {
//                    responseEntity.setError(ErrorConstant.SUCCESS.getCode());
//                }
//            }
            return responseEntity;
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.REPAY_ERROR.getCode(), e.getMessage(), e);
        }
    }
}
