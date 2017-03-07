package com.mobanker.shanyidai.esb.call.http.convert;

import com.mobanker.shanyidai.dubbo.dto.payback.OtherRepayParamDto;
import com.mobanker.shanyidai.dubbo.dto.payback.RepayParamDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc: 财务基础服务参数封装和验证
 * @author: Richard Core
 * @create time: 2017/1/17 14:52
 */
public class FinanceHttpConvert {
    /**
     * @param repayParamDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 验证和封装支付参数
     * @author Richard Core
     * @time 2017/1/17 14:53
     * @method checkAndMapRepayParams
     */
    public static Map<String, String> checkAndMapRepayParams(RepayParamDto repayParamDto) {
        if (repayParamDto == null) {
            throw new EsbException(ErrorConstant.REPAY_PARAM_ERROR);
        }
        if (repayParamDto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        if (repayParamDto.getRepayUid() == null) {
            throw new EsbException(ErrorConstant.REPAY_REPAY_TYPE_NULL);
        }
        if (StringUtils.isBlank(repayParamDto.getType())) {
            throw new EsbException(ErrorConstant.REPAY_TYPE_NULL);
        }
        if (StringUtils.isBlank(repayParamDto.getChannel())) {
            throw new EsbException(ErrorConstant.REPAY_CHANNEL_NULL);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", repayParamDto.getUserId().toString());//用户id
        params.put("repayUid", repayParamDto.getRepayUid());//还款渠道
        params.put("type", repayParamDto.getType());//还款产品
        params.put("channel", repayParamDto.getChannel());//还款产品渠道 entity中取
        params.put("debitcardNum", repayParamDto.getDebitcardNum());//还款银行卡号
        params.put("frontUrl", repayParamDto.getFrontUrl());//回调接口地址
        params.put("period", repayParamDto.getPeriod());//还款期数
        params.put("spbillCreateIp", repayParamDto.getSpbillCreateIp());//APP和网页支付提交用户端ip
        params.put("openId", repayParamDto.getOpenId());//用户在商户appid下的唯一标识
        return params;
    }

    /**
     * @param paramDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 封装易宝和百度钱包支付方式参数
     * @author Richard Core
     * @time 2017/1/22 14:41
     * @method checkAndMapOtherRepayParams
     */
    public static Map<String,String> checkAndMapOtherRepayParams(OtherRepayParamDto paramDto) {
        if (paramDto == null) {
            throw new EsbException(ErrorConstant.REPAY_PARAM_ERROR);
        }
        if (paramDto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        if (paramDto.getRepayUid() == null) {
            throw new EsbException(ErrorConstant.REPAY_REPAY_TYPE_NULL);
        }
        if (StringUtils.isBlank(paramDto.getType())) {
            throw new EsbException(ErrorConstant.REPAY_TYPE_NULL);
        }
        if (StringUtils.isBlank(paramDto.getChannel())) {
            throw new EsbException(ErrorConstant.REPAY_CHANNEL_NULL);
        }
        if (StringUtils.isBlank(paramDto.getPayType())) {
            throw new EsbException(ErrorConstant.REPAY_REPAY_TYPE_NULL.getCode(),"支付类型");
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", paramDto.getUserId().toString());//用户id
        params.put("repayUid", paramDto.getRepayUid());//还款渠道 50
        params.put("type", paramDto.getType());//还款产品(手机贷：shoujidai
        params.put("channel", paramDto.getChannel());//U族：uzone)
        params.put("debitcardNum", paramDto.getDebitcardNum());//还款产品渠道(微网站：microsite)
        params.put("period", paramDto.getPeriod());//银行卡号
        params.put("payType", paramDto.getPayType());//单期 1; 多期 (1,2,3…)逗号隔
        params.put("userIp", paramDto.getIp());//支付类型 baidu  yibao
        params.put("requestId", paramDto.getRequestId());//易宝不能为空,百度可为空用户ip
        params.put("goodsUrl", paramDto.getGoodsUrl());//易宝支付不能为空
        params.put("pageUrl", paramDto.getPageUrl());//（不支付） 商品在商户网站上的URL（百度支付）

        return params;
    }
}
