/**
 * 
 */
package com.mobanker.shanyidai.dubbo.service.borrow;

import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowInfoParamDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowOrderParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * 借款相关Dubbo接口服务声明
 * 
 * @author chenjianping
 * @data 2016年12月16日
 */
public interface BorrowDubboService {

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 是否在前隆借款中
     * @author hantongyang
     * @time 2017/1/10 15:47
     * @method checkIsQLBorrowIng
     */
    ResponseEntity checkIsQLBorrowIng(Long userId);

    /**
     * @param nid
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据订单号查询借款详情
     * @author hantongyang
     * @time 2017/1/12 14:53
     * @method getBorrowInfo
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
     * @description 保存借款单
     * @author hantongyang
     * @time 2017/1/17 11:46
     * @method addBorrowOrder
     * @param param
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity addBorrowOrder(BorrowOrderParamDto param);

}
