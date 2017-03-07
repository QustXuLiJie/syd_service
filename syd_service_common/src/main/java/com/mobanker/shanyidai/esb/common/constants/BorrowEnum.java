package com.mobanker.shanyidai.esb.common.constants;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/18 10:34
 */
public enum BorrowEnum {
    BORROW_STATUS("0"), //订单状态，0为下单成功
    BORROW_PERIOD("1"), //订单期数
    BORROW_MQ_TYPE("1"), //1发送，2接收
    BORROW_ROUTING_KEY("borrow.apply.notify"), //订单MQ的routingKey
    ;

    private String value;

    BorrowEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
