/**
 * 
 */
package com.mobanker.shanyidai.esb.business.borrow;

import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowInfoParamDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowOrderParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.model.dto.rabbitmq.OrderConsumerMessage;
import com.mobanker.shanyidai.esb.model.entity.borrow.BorrowEntity;

/**
 * 借款业务逻辑服务定义
 * 
 * @author chenjianping
 * @data 2016年12月16日
 */
public interface BorrowBusiness {

    /**
     * @description 是否在前隆借款中
     * @author hantongyang
     * @time 2017/1/10 15:57
     * @method checkIsQLBorrowIng
     * @param userId
     * @return ResponseEntity
     */
    ResponseEntity checkIsQLBorrowIng(Long userId);

    /**
     * @description 根据订单编号获取借款详情
     * @author hantongyang
     * @time 2017/1/12 15:10
     * @method getBorrowInfo
     * @param nid
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity getBorrowInfo(String nid);

    /**
     * @param param
     * @return
     * @description 查询借款历史
     * @author Richard Core
     * @time 2017/1/12 9:39
     * @method storyList
     */
    ResponseEntity getHistoryList(BorrowInfoParamDto param);

    /**
     * @description 保存借款单信息到流水表中
     * @author hantongyang
     * @time 2017/1/19 19:51
     * @method addBorrowRecord
     * @param param
     * @return Long
     */
    Long addBorrowRecord(BorrowOrderParamDto param);

    /**
     * @description 修改借款单流水表信息
     * @author hantongyang
     * @time 2017/1/20 17:59
     * @method updateBorrowRecord
     * @param entity
     * @return java.lang.Long
     */
    Long updateBorrowRecord(BorrowEntity entity);

    /**
     * @description 根据ID和产品查询闪宜贷数据库的借款单流水信息
     * @author hantongyang
     * @time 2017/1/20 16:50
     * @method getBorrowByIdAndProd
     * @param id
     * @param product
     * @return com.mobanker.shanyidai.esb.model.entity.borrow.BorrowEntity
     */
    BorrowEntity getBorrowByIdAndProd(Long id, String product);

    /**
     * @description 保存借款单MQ回调流水
     * @author hantongyang
     * @time 2017/1/20 17:20
     * @method addBorrowCallBackEntity
     * @param param
     * @return java.lang.Long
     */
    Long addBorrowCallBackEntity(OrderConsumerMessage param);

    /**
     * @description 保存旧订单中心订单
     * @author hantongyang
     * @time 2017/1/20 18:04
     * @method addOldBorrowOrder
     * @param entity
     * @return java.lang.Long
     */
    Long addOldBorrowOrder(BorrowEntity entity);

    /**
     * @description 保存调用订单中心MQ流水
     * @author hantongyang
     * @time 2017/1/23 10:31
     * @method addMqProducerOrderEntity
     * @param param
     * @return java.lang.Long
     */
    Long addMqProducerOrderEntity(BorrowOrderParamDto param);

    /**
     * @description 订单系统MQ回调监控
     * @author hantongyang
     * @time 2017/2/17 13:43
     * @method callBackBorrowOrder
     * @param callMsg
     * @param queue
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity callBackBorrowOrder(String callMsg, String queue);
}
