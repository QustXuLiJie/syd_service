package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.dto.BorrowPeriodFeeDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/30 11:35
 */
public interface ProductDubbo {

    /**
     * @description 根据产品模板Id查询产品模板信息
     * @author hantongyang
     * @time 2016/12/30 11:41
     * @method getProductTempById
     * @param dto
     * @return com.mobanker.framework.dto.ResponseEntity
     */
    ResponseEntity getProductTempById(BorrowPeriodFeeDto dto);
}
