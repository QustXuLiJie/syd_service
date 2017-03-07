package com.mobanker.shanyidai.esb.call.http;

import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @author hantongyang
 * @description 借款相关http接口
 * @time 2017/1/11 16:52
 */
public interface BorrowHttp {

    /**
     * @description 根据订单号，获取借款详情
     * @author hantongyang
     * @time 2017/1/11 16:07
     * @method getBorrowInfo
     * @param restUrl
     * @param borrowNid
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity getBorrowInfo(String restUrl, String borrowNid);
}
