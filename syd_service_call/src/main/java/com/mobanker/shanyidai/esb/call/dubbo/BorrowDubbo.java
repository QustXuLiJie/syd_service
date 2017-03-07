package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.business.dubbo.api.pojo.BorrowApplyBean;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowInfoParamDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.OldBorrowOrderDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/10 16:50
 */
public interface BorrowDubbo {

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
     * @param param
     * @return
     * @description 查询借款历史
     * @author Richard Core
     * @time 2017/1/12 9:39
     * @method storyList
     */
    ResponseEntity getHistoryList(BorrowInfoParamDto param);

    /**
     * @description 保存旧订单系统订单
     * @author hantongyang
     * @time 2017/1/19 21:02
     * @method addOldBorrowOrder
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity addOldBorrowOrder(OldBorrowOrderDto dto);
}
