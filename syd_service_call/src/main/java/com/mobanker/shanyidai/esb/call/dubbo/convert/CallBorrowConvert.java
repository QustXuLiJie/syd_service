package com.mobanker.shanyidai.esb.call.dubbo.convert;

import com.mobanker.business.dubbo.api.pojo.BorrowApplyBean;
import com.mobanker.business.dubbo.api.pojo.BorrowHistoryBean;
import com.mobanker.business.dubbo.api.pojo.BorrowHistoryRepayBean;
import com.mobanker.business.dubbo.api.pojo.RepayDetailBean;
import com.mobanker.framework.pojo.Page;
import com.mobanker.shanyidai.dubbo.dto.borrow.*;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @desc: 借款相关辅助类
 * @author: Richard Core
 * @create time: 2017/1/12 10:00
 */
public class CallBorrowConvert {

    /**
     * @param borrowHistoryBean
     * @param dto
     * @return void
     * @description 封装还款信息
     * @author Richard Core
     * @time 2017/1/12 9:58
     * @method setRepayList
     */
    public static void setRepayList(BorrowHistoryBean borrowHistoryBean, BorrowHistoryDto dto) {
        List<BorrowHistoryRepayDto> repayDtoList = new ArrayList<BorrowHistoryRepayDto>();
        List<BorrowHistoryRepayBean> repayList = borrowHistoryBean.getRepayList();
        if (repayList == null || repayList.isEmpty()) {
            return;
        }
        for (BorrowHistoryRepayBean repayBean : repayList) {
            if (repayBean == null) {
                continue;
            }
            BorrowHistoryRepayDto borrowHistoryRepayDto = BeanUtil.cloneBean(repayBean, BorrowHistoryRepayDto.class);
            repayDtoList.add(borrowHistoryRepayDto);
        }
        dto.setRepayList(repayDtoList);
    }

    /**
     * @param borrowHistoryBean
     * @param dto
     * @return void
     * @description 封装还款详情
     * @author Richard Core
     * @time 2017/1/12 9:59
     * @method setRepayDetailList
     */
    public static void setRepayDetailList(BorrowHistoryBean borrowHistoryBean, BorrowHistoryDto dto) {
        List<RepayDetailBean> repayDetails = borrowHistoryBean.getRepayDetails();
        List<RepayDetailDto> detailList = new ArrayList<RepayDetailDto>();
        if (repayDetails == null || repayDetails.isEmpty()) {
            return;
        }
        for (RepayDetailBean repayDetail : repayDetails) {
            if (repayDetail == null) {
                continue;
            }
            RepayDetailDto repayDetailDto = BeanUtil.cloneBean(repayDetail, RepayDetailDto.class);
            detailList.add(repayDetailDto);
        }
        dto.setRepayDetails(detailList);
    }

    /**
     * @param param
     * @return void
     * @description 封装借款参数验证
     * @author Richard Core
     * @time 2017/1/12 10:01
     * @method getBorrowInfoParams
     */
    public static void getBorrowInfoParams(BorrowInfoParamDto param) {
        if (param == null) {
            throw new EsbException(ErrorConstant.BORROW_PARAM_NULL);
        }
        if (param.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
    }

    /**
     * @param result
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 从服务返回值获取借款历史信息
     * @author Richard Core
     * @time 2017/1/12 10:04
     * @method getBorrowResponseEntity
     */
    public static ResponseEntity getBorrowResponseEntity(com.mobanker.core.pojo.ResponseEntity result) {
        //返回结果验证
        ResponseEntity responseEntity = ResponseBuilder.checkResponseEntity(result, ErrorConstant.BORROW_INFO_ERROR,true);
        if (responseEntity.getData() == null) {
            return ResponseBuilder.normalResponse(new ArrayList<BorrowHistoryDto>());
        }
        //获取历史列表
        List<BorrowHistoryDto> resultList = getBorrowHistoryDtos(responseEntity);
        ResponseEntity responseResult = ResponseBuilder.normalResponse(resultList);
        Page page = BeanUtil.cloneBean(responseEntity.getPage(),Page.class);
        responseResult.setPage(page);
        return responseResult;
    }

    /**
     * @param responseEntity
     * @return java.util.List<com.mobanker.shanyidai.dubbo.dto.borrow.BorrowHistoryDto>
     * @description 获取历史列表
     * @author Richard Core
     * @time 2017/1/12 10:14
     * @method getBorrowHistoryDtos
     */
    public static List<BorrowHistoryDto> getBorrowHistoryDtos(ResponseEntity responseEntity) {
        List<BorrowHistoryBean> dataList = (List<BorrowHistoryBean>) responseEntity.getData();
        if (dataList == null || dataList.isEmpty()) {
            return new ArrayList<BorrowHistoryDto>();
        }
        List<BorrowHistoryDto> resultList = new ArrayList<BorrowHistoryDto>();
        for (BorrowHistoryBean borrowHistoryBean : dataList) {
            if (borrowHistoryBean == null) {
                continue;
            }
            BorrowHistoryDto dto = BeanUtil.cloneBean(borrowHistoryBean, BorrowHistoryDto.class);
            //封装还款信息
            CallBorrowConvert.setRepayList(borrowHistoryBean, dto);
            //封装还款详情
            CallBorrowConvert.setRepayDetailList(borrowHistoryBean, dto);
            resultList.add(dto);
        }
        return resultList;
    }

    /**
     * @description 初始化旧订单系统订单DTO
     * @author hantongyang
     * @time 2017/1/19 21:04
     * @method initBorrowApplyBean
     * @param
     * @return com.mobanker.business.dubbo.api.pojo.BorrowApplyBean
     */
    public static BorrowApplyBean initBorrowApplyBean(OldBorrowOrderDto dto){
        BorrowApplyBean bean = BeanUtil.cloneBean(dto, BorrowApplyBean.class);
        bean.setId(null);
        bean.setFromChannel(dto.getChannel());
        bean.setCode(dto.getUserCode());
        bean.setSmsCode(dto.getCaptcha());
        bean.setAddIp(dto.getRemoteIp());
        bean.setPeriod(dto.getBorrowPeriod() == null ? null : dto.getBorrowPeriod().doubleValue());
        Double borrowAmount = dto.getBorrowAmount() == null ? null : dto.getBorrowAmount().doubleValue();
        bean.setBorrowAmountWait(borrowAmount);
        bean.setVouchAmount(borrowAmount);
        bean.setVouchAmountWait(borrowAmount);
        bean.setName(dto.getBorrowNid());
        bean.setAgreedCardNum(dto.getBankCard());
        bean.setSn(dto.getId() + "");
        bean.setMobileInfo(dto.getUserPhone());
        bean.setTerminalChannel(dto.getChannel());
        bean.setAppVersion(dto.getVersion());
        bean.setFeeFlag("FRONT".equals(dto.getChargesRule()) ? 1 : 0);
        //初始化静态参数
        setBorrowApplyBean4StaticParam(bean);
        return bean;
    }

    /**
     * @description 初始化旧订单系统静态参数
     * @author hantongyang
     * @time 2017/1/19 20:30
     * @method setBorrowApplyBean4StaticParam
     * @param
     * @return com.mobanker.business.dubbo.api.pojo.BorrowApplyBean
     */
    private static void setBorrowApplyBean4StaticParam(BorrowApplyBean bean){
        bean.setAddtime(System.currentTimeMillis()/1000);
        bean.setBorrowStyle(6);
        bean.setIsSeconds(0);
        bean.setType("1");
        bean.setBorrowApr(360d);
        bean.setBorrowValidTime(5L);
        bean.setOpenAccount(1);
        bean.setOpenBorrow(1);
        bean.setOpenCredit(1);
        bean.setAlipayNum("");
        bean.setFddays(5);
        bean.setViewType("");
        bean.setBorrowEndTime("");
        bean.setCashStatus(1);
        bean.setOtherWebStatus(0);
        bean.setAccountContents("");
        bean.setBorrowFlag("");
        bean.setBorrowStatus(0);
        bean.setBorrowFullStatus(0);
        bean.setBorrowPartStatus(0);
        bean.setCancelStatus(0);
        bean.setCancelTime("");
        bean.setCancelRemark("");
        bean.setTenderLastTime("");
        bean.setRepayLastTime("");
        bean.setRepayTimes(0);
        bean.setLateInterest(0d);
        bean.setLateForfeit(0d);
        bean.setVouchStatus(0);
        bean.setVouchUserStatus(2);
        bean.setVouchUsers("");
        bean.setVouchAwardScale(0d);
        bean.setFastStatus(0);
        bean.setVouchStatus(0);
        bean.setGroupStatus(0);
        bean.setGroupId(0);
        bean.setCommentStaus(0);
        bean.setCommentTimes(0);
        bean.setCommentUsertype(null);
        bean.setEffective(0);
        bean.setReasonFall(0);
        bean.setDistribute(0);
        bean.setRemarkStatus(0);
        bean.setPreverifyTime("");
        bean.setDealwaitRemark("");
        bean.setTelDistribute(0);
        bean.setTelVerifyUserid(0);
        bean.setTelVerifyTime("");
        bean.setLoanPurpose("");
        bean.setAgreementStatus(0);
        bean.setSecondDistribute(0);
        bean.setMarker("");
        bean.setIsTest(0);
        bean.setFlowFlag("");
        bean.setLateApr1(0.02d);
        bean.setLateApr2(0.02d);
        bean.setLateApr3(0.02d);
    }
}
