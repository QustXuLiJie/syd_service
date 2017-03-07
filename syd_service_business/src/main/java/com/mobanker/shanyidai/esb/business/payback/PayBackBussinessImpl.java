package com.mobanker.shanyidai.esb.business.payback;

import com.mobanker.shanyidai.dubbo.dto.payback.OtherRepayParamDto;
import com.mobanker.shanyidai.dubbo.dto.payback.PayResultDto;
import com.mobanker.shanyidai.dubbo.dto.payback.RepayParamDto;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.call.dubbo.FinanceDubbo;
import com.mobanker.shanyidai.esb.call.http.FinanceHttp;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.ZkConfigConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/1/10 11:56
 */
@Service
public class PayBackBussinessImpl implements PayBackBussiness {
    @Resource
    private FinanceDubbo financeDubbo;
    @Resource
    private EsbCommonBusiness esbCommonBusiness;
    @Resource
    private FinanceHttp financeHttp;

    /**
     * @param orderId
     * @return java.lang.Object
     * @description 获取还款信息
     * @author Richard Core
     * @time 2017/1/10 11:58
     * @method getBorrowRepayDetail
     */
    public Object getBorrowRepayDetail(String orderId){

        Object borrowRepayDetail = financeDubbo.getBorrowRepayDetail(orderId);
        return borrowRepayDetail;
    }
    /**
     * @param param
     * @return java.lang.Object
     * @description 还款接口 （一键还款和微信支付）
     * @author Richard Core
     * @time 2017/1/17 10:28
     * @method addBillsAndPeriodRepay
     */
    @Override
    public ResponseEntity addBillsAndPeriodRepay(RepayParamDto param) {
        //配置系统获取支付链接
        String restUrl = null;
        try {
            restUrl = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.REPAY_CW_URL.getZkValue());
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.REPAY_ERROR.getCode(),"配置系统没有找到财务还款链接");
        }
        if (StringUtils.isBlank(restUrl)) {
            throw new EsbException(ErrorConstant.REPAY_ERROR.getCode(),"配置系统没有找到财务还款链接");
        }
        //支付
        return financeHttp.addBillsAndPeriodRepay(restUrl,param);
    }
    /**
     * @param paramDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 百度钱包和易宝支付还款
     * @author Richard Core
     * @time 2017/1/22 14:47
     * @method addBillsAndOtherRepay
     */
    @Override
    public ResponseEntity addBillsAndOtherRepay( OtherRepayParamDto paramDto) {
        //配置系统获取支付链接
        String restUrl = null;
        try {
            restUrl = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.OTHER_REPAY_CW_URL.getZkValue());
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.REPAY_ERROR.getCode(),"配置系统没有找到财务其他还款链接");
        }
        if (StringUtils.isBlank(restUrl)) {
            throw new EsbException(ErrorConstant.REPAY_ERROR.getCode(),"配置系统没有找到财务其他还款链接");
        }
        //支付
        return financeHttp.addBillsAndOtherRepay(restUrl,paramDto);
    }

    /**
     * @param borrowNid
     * @param repayType
     * @return com.mobanker.shanyidai.dubbo.dto.payback.PayResultDto
     * @description 查询支付结果
     * @author Richard Core
     * @time 2017/1/23 10:54
     * @method getRepayStatus
     */
    @Override
    public PayResultDto getRepayStatus(String borrowNid, String repayType) {
        return financeDubbo.getRepayStatus(borrowNid, repayType);
    }
}
