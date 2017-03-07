package com.mobanker.shanyidai.dubbo.service.payback;

import com.mobanker.shanyidai.dubbo.dto.payback.OtherRepayParamDto;
import com.mobanker.shanyidai.dubbo.dto.payback.PayBackParamDto;
import com.mobanker.shanyidai.dubbo.dto.payback.RepayParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @desc:还款相关综合服务
 * @author: Richard Core
 * @create time: 2017/1/10 12:03
 */
public interface PayBackDubboService {
    /**
     * @param paramDto
     * @return ResponseEntity
     * @description 获取还款信息
     * @author Richard Core
     * @time 2017/1/10 11:58
     * @method getBorrowRepayDetail
     */
    public ResponseEntity getBorrowRepayDetail(PayBackParamDto paramDto);

    /**
     * @param param
     * @return ResponseEntity
     * @description 还款接口（一键还款，微信支付）
     * @author Richard Core
     * @time 2017/1/17 10:28
     * @method addBillsAndPeriodRepay
     */
    public ResponseEntity addBillsAndPeriodRepay(RepayParamDto param);

    /**
     * @param param
     * @return ResponseEntity
     * @description 还款接口（百度钱包，易宝）
     * @author Richard Core
     * @time 2017/1/17 10:28
     * @method addBillsAndOtherRepay
     */
    public ResponseEntity addBillsAndOtherRepay(OtherRepayParamDto param);

    /**
     * @param borrowNid
     * @param repayType
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 查询还款状态
     * @author Richard Core
     * @time 2017/1/23 11:36
     * @method getRepayStatus
     */
    public ResponseEntity getRepayStatus(String borrowNid, String repayType);
}
