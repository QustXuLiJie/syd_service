package com.mobanker.shanyidai.esb.business.payback;

import com.mobanker.shanyidai.dubbo.dto.payback.OtherRepayParamDto;
import com.mobanker.shanyidai.dubbo.dto.payback.PayResultDto;
import com.mobanker.shanyidai.dubbo.dto.payback.RepayParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @desc:财务相关方法封装
 * @author: Richard Core
 * @create time: 2017/1/10 11:54
 */
public interface PayBackBussiness {
    /**
     * @param orderId
     * @return java.lang.Object
     * @description 获取还款信息
     * @author Richard Core
     * @time 2017/1/10 11:58
     * @method getBorrowRepayDetail
     */
    public Object getBorrowRepayDetail(String orderId);

    /**
     * @param param
     * @return java.lang.Object
     * @description 还款接口（一键还款，微信支付）
     * @author Richard Core
     * @time 2017/1/17 10:28
     * @method addBillsAndPeriodRepay
     */
    public ResponseEntity addBillsAndPeriodRepay(RepayParamDto param);

    /**
     * @param paramDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 百度钱包和易宝支付还款
     * @author Richard Core
     * @time 2017/1/22 14:47
     * @method addBillsAndOtherRepay
     */
    public ResponseEntity addBillsAndOtherRepay(OtherRepayParamDto paramDto);

    /**
     * @param borrowNid
     * @param repayType
     * @return com.mobanker.shanyidai.dubbo.dto.payback.PayResultDto
     * @description 查询支付结果
     * @author Richard Core
     * @time 2017/1/23 10:54
     * @method getRepayStatus
     */
    public PayResultDto getRepayStatus(String borrowNid, String repayType);

}
