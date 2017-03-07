package com.mobanker.shanyidai.esb.service.payback;

import com.mobanker.shanyidai.dubbo.dto.payback.*;
import com.mobanker.shanyidai.dubbo.service.payback.PayBackDubboService;
import com.mobanker.shanyidai.esb.business.payback.PayBackBussiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @desc: 还款相关综合服务
 * @author: Richard Core
 * @create time: 2017/1/10 12:04
 */
@Service
public class PayBackServiceImpl implements PayBackDubboService {
    @Resource
    private PayBackBussiness payBackBussiness;

    /**
     * @param paramDto
     * @return ResponseEntity
     * @description 获取还款信息
     * @author Richard Core
     * @time 2017/1/10 11:58
     * @method getBorrowRepayDetail
     */
    @Override
    public ResponseEntity getBorrowRepayDetail(PayBackParamDto paramDto) {
        try {
            //参数验证
            CommonUtil.checkBaseParam(paramDto);
            if (StringUtils.isBlank(paramDto.getBorrowNid())) {
                throw new EsbException(ErrorConstant.ERROR_BORROW_NID_NULL);
            }
            //查询还款信息
            Object borrowRepayDetail = payBackBussiness.getBorrowRepayDetail(paramDto.getBorrowNid());
            PayBackInfoDto dto = new PayBackInfoDto();
            dto.setBorrowNid(dto.getBorrowNid());
            dto.setLateDays(10);
            dto.setRepayTime(new Date());
            dto.setRepayMoney(BigDecimal.ONE);
            return ResponseBuilder.normalResponse(dto);
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.PAY_BACK_ERROR, e, this.getClass().getSimpleName(), "getBorrowRepayDetail");
        }
    }

    /**
     * @param param
     * @return ResponseEntity
     * @description 还款接口（一键还款，微信支付）
     * @author Richard Core
     * @time 2017/1/17 10:28
     * @method addBillsAndPeriodRepay
     */
    @Override
    public ResponseEntity addBillsAndPeriodRepay(RepayParamDto param) {
        try {
            //支付
            ResponseEntity responseEntity = payBackBussiness.addBillsAndPeriodRepay(param);
            //查询支付结果
            if (ResponseBuilder.isSuccess(responseEntity)) {
                PayResultDto repayStatus = payBackBussiness.getRepayStatus(param.getBorrowNid(), null);
                responseEntity.setData(repayStatus);
            }
            return responseEntity;
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.REPAY_ERROR, e, this.getClass().getSimpleName(), "addBillsAndPeriodRepay");
        }
    }

    /**
     * @param param
     * @return ResponseEntity
     * @description 还款接口（百度钱包，易宝）
     * @author Richard Core
     * @time 2017/1/17 10:28
     * @method addBillsAndPeriodRepay
     */
    @Override
    public ResponseEntity addBillsAndOtherRepay(OtherRepayParamDto param) {
        try {
            //支付
            ResponseEntity responseEntity = payBackBussiness.addBillsAndOtherRepay(param);
            //查询支付结果
            if (ResponseBuilder.isSuccess(responseEntity)) {
                PayResultDto repayStatus = payBackBussiness.getRepayStatus(param.getBorrowNid(), null);
                responseEntity.setData(repayStatus);
            }
            return responseEntity;
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.REPAY_ERROR, e, this.getClass().getSimpleName(), "addBillsAndOtherRepay");
        }
    }

    /**
     * @param borrowNid
     * @param repayType
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 查询还款状态
     * @author Richard Core
     * @time 2017/1/23 11:36
     * @method getRepayStatus
     */
    @Override
    public ResponseEntity getRepayStatus(String borrowNid, String repayType) {
        try {
            PayResultDto repayStatus = payBackBussiness.getRepayStatus(borrowNid, repayType);
            return ResponseBuilder.normalResponse(repayStatus);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.pay_result_error, e, this.getClass().getSimpleName(), "getRepayStatus");
        }

    }
}
