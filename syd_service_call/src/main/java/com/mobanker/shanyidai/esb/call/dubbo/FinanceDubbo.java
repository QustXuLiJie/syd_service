package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.shanyidai.dubbo.dto.payback.PayResultDto;

/**
 * @desc: 财务相关服务封装
 * @author: Richard Core
 * @create time: 2017/1/10 9:47
 */
public interface FinanceDubbo {
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
