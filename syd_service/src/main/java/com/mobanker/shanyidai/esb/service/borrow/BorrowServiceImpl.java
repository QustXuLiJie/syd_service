/**
 *
 */
package com.mobanker.shanyidai.esb.service.borrow;

import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowInfoParamDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowOrderParamDto;
import com.mobanker.shanyidai.dubbo.service.borrow.BorrowDubboService;
import com.mobanker.shanyidai.esb.business.borrow.BorrowBusiness;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.common.constants.*;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.middleware.rabbitmq.producer.BorrowHandleProducer;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

import static com.mobanker.shanyidai.esb.business.borrow.util.BorrowConvert.initBorrowProducerMessage;

/**
 * 借款服务Dubbo实现
 *
 * @author chenjianping
 * @data 2016年12月16日
 */
public class BorrowServiceImpl implements BorrowDubboService {
    public static final Logger logger = LogManager.getSlf4jLogger(BorrowServiceImpl.class);
    @Resource
    private BorrowBusiness borrowBusiness;
    @Resource
    private EsbCommonBusiness esbCommonBusiness;
    @Resource
    private BorrowHandleProducer orderCenterMq;

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 是否在前隆借款中
     * @author hantongyang
     * @time 2017/1/10 15:47
     * @method checkIsQLBorrowIng
     */
    @Override
    public ResponseEntity checkIsQLBorrowIng(Long userId) {
        logger.debug("是否在前隆借款中开始", userId);
        if (userId == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "checkIsQLBorrowIng");
        }
        try {
            ResponseEntity entity = borrowBusiness.checkIsQLBorrowIng(userId);
            return entity;
        } catch (Exception e) {
            logger.debug("是否在前隆借款中异常", e);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_BORROW_CHECK_QL_BORROWING, e, this.getClass().getSimpleName(), "checkIsQLBorrowIng");
        }
    }

    /**
     * @param nid
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据订单号查询借款详情
     * @author hantongyang
     * @time 2017/1/12 14:53
     * @method getBorrowInfo
     */
    @Override
    public ResponseEntity getBorrowInfo(String nid) {
        logger.debug("根据订单号查询借款详情开始", nid);
        if (StringUtils.isBlank(nid)) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_BORROW_NID_NULL, this.getClass().getSimpleName(), "getBorrowInfo");
        }
        try {
            return borrowBusiness.getBorrowInfo(nid);
        } catch (Exception e) {
            logger.debug("根据订单号查询借款详情异常", e);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_BORROW_GET_INFO, e, this.getClass().getSimpleName(), "getBorrowInfo");
        }
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
        logger.debug("获取借款历史方法开始", param);
        if (param == null || param.getUserId() == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.LOGIN_TIME_OUT, this.getClass().getSimpleName(), "getHistoryList");
        }
        try {
            ResponseEntity result = borrowBusiness.getHistoryList(param);
            return result;
        } catch (Exception e) {
            logger.error("获取借款历史出错", e);
            return ResponseBuilder.errorResponse(ErrorConstant.BORROW_INFO_ERROR, e, this.getClass().getSimpleName(), "getHistoryList");
        }
    }

    /**
     * @description 保存借款单
     * @author hantongyang
     * @time 2017/1/17 11:46
     * @method addBorrowOrder
     * @param param
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity addBorrowOrder(BorrowOrderParamDto param) {
        logger.debug("保存借款单方法开始", param);
        if(param == null){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "addBorrowOrder");
        }
        try {
            //获取配置系统配置的重试次数
            String cacheSysConfig = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.BORROW_ORDER_MQ_REP_COUNT.getZkValue());
            int configCount = Integer.parseInt(cacheSysConfig);
            //生成流水表订单
            param.setBorrowId(borrowBusiness.addBorrowRecord(param));
            //调用生成借款单
            ResponseEntity result = addBorrowOrder(param, 1, configCount);
            //入库rabbitMq流水
            borrowBusiness.addMqProducerOrderEntity(param);
            return result;
        } catch (Exception e) {
            logger.debug("保存借款单出错", e);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_ADD_ORDER, e, this.getClass().getSimpleName(), "addBorrowOrder");
        }
    }

    /**
     * @description 保存借款单
     * @author hantongyang
     * @time 2017/1/17 11:57
     * @method addBorrowOrder
     * @param param
     * @param count 默认传1，判断重试次数
     * @param configCount 配置系统重试次数
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    private ResponseEntity addBorrowOrder(BorrowOrderParamDto param, int count, int configCount) {
        //判断是否超出最大次数
        if(count > configCount){
            //抛出异常
            throw new EsbException(ErrorConstant.ERROR_ADD_ORDER);
        }
        try{
            orderCenterMq.borrowNotifyMQ(initBorrowProducerMessage(param));
        }catch (Exception e){
            //计数+1，并再次调用生成借款单
            count ++;
            addBorrowOrder(param, count, configCount);
        }
        return ResponseBuilder.normalResponse();
    }
}
