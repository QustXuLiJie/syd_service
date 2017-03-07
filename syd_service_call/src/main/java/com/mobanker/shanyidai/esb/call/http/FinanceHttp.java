package com.mobanker.shanyidai.esb.call.http;

import com.mobanker.shanyidai.dubbo.dto.payback.OtherRepayParamDto;
import com.mobanker.shanyidai.dubbo.dto.payback.RepayParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/1/16 20:10
 */
public interface FinanceHttp {
    /**
     *
     * @param restUrl
     * @param param
     * @return java.lang.Object
     * @description 还款接口
     * @author Richard Core
     * @time 2017/1/17 10:28
     * @method addBillsAndPeriodRepay
     */
    ResponseEntity addBillsAndPeriodRepay(String restUrl, RepayParamDto param);

    /**
     * @param restUrl
     * @param paramDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 百度钱包和易宝支付还款
     * @author Richard Core
     * @time 2017/1/22 14:47
     * @method addBillsAndOtherRepay
     */
    public ResponseEntity addBillsAndOtherRepay(String restUrl, OtherRepayParamDto paramDto);

}
