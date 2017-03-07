package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.mobanker.business.dubbo.api.pojo.CheckIsQLBorrowIngBean;
import com.mobanker.business.dubbo.api.service.BorrowInfoDubboService;
import com.mobanker.business.dubbo.api.service.BorrowInfoDubboServiceTo3c;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowInfoParamDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.OldBorrowOrderDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.QlBorrowIngDto;
import com.mobanker.shanyidai.esb.call.dubbo.BorrowDubbo;
import com.mobanker.shanyidai.esb.call.dubbo.convert.CallBorrowConvert;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.mobanker.shanyidai.esb.call.dubbo.convert.CallBorrowConvert.initBorrowApplyBean;

/**
 * @author hantongyang
 * @description 借款相关服务实现
 * @time 2017/1/10 16:50
 */
@Service
public class BorrowDubboImpl implements BorrowDubbo {
    public static final Logger logger = LogManager.getSlf4jLogger(BorrowDubboImpl.class);

    @Resource
    private BorrowInfoDubboService borrowInfoDubboService;
    @Resource
    private BorrowInfoDubboServiceTo3c borrowInfoDubboServiceTo3c;

    /**
     * @description 是否在前隆借款中
     * @author hantongyang
     * @time 2017/1/10 17:05
     * @method checkIsQLBorrowIng
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity checkIsQLBorrowIng(Long userId) {
        com.mobanker.core.pojo.ResponseEntity responseEntity = null;
        try {
            responseEntity = borrowInfoDubboService.checkIsQLBorrowIng(userId);
        }catch(Exception e){
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), e.getMessage());
        }
        ResponseEntity result = BeanUtil.cloneResponseEntity(responseEntity);
        if(!ResponseBuilder.isSuccess(result)){
            logger.warn("调用订单中心服务-是否在前隆借款中服务异常", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        //重新封装DATA参数
        Object obj = result.getData();
        if(obj instanceof CheckIsQLBorrowIngBean){
            QlBorrowIngDto dto = BeanUtil.cloneBean(result.getData(), QlBorrowIngDto.class);
            result.setData(dto);
        }
        return result;
    }

    /**
     * @param param
     * @return
     * @description 查询借款历史
     * @author Richard Core
     * @time 2017/1/12 9:39
     * @method storyList
     */
    @Override
    public ResponseEntity getHistoryList(BorrowInfoParamDto param) {
        //封装查询参数
        CallBorrowConvert.getBorrowInfoParams(param);
        //调用服务
        com.mobanker.core.pojo.ResponseEntity result = null;
        try {
            result = borrowInfoDubboService.getHistoryList(param.getUserId(), param.getBorrowNid(), param.getQueryType(), param.getSystemType(), param.getProduct());
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.BORROW_INFO_ERROR, e);
        }
        //返回值处理
        ResponseEntity responseResult = CallBorrowConvert.getBorrowResponseEntity(result);
        return responseResult;

    }

    /**
     * @description 保存旧订单系统订单
     * @author hantongyang
     * @time 2017/1/19 21:02
     * @method addOldBorrowOrder
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity addOldBorrowOrder(OldBorrowOrderDto dto) {
        if(dto == null){
            throw new EsbException(ErrorConstant.ERROR_BORROW_ADD_OLD_RECORD);
        }
        try {
            Integer value = borrowInfoDubboServiceTo3c.initiBorrow(initBorrowApplyBean(dto));
            return ResponseBuilder.normalResponse(value);
        }catch(Exception e){
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), e.getMessage());
        }
    }
}
