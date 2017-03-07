package com.mobanker.shanyidai.esb.common.constants;

import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author hantongyang
 * @description 订单中心回调MQ中的状态
 * @time 2017/1/20 15:38
 */
public enum OrderCenterStatusEnum {
    CHECK_WAIT("check_wait", "0", "待审核"),
    CREDIT_YES("credit_yes", "1", "信审成功"),
    CREDIT_NO("credit_no", "2", "信审失败"),
    CALL_YES("call_yes", "3", "客服电核成功"),
    CALL_NO("call_no", "4", "客服电核失败"),
    LOAN_YES("loan_yes", "5", "放款成功"),
    LOAN_NO("loan_no", "6", "放款失败"),
    REPAY_YESG("repay_yesg", "7", "还款成功"),
    ;

    private String code;
    private String status;
    private String msg;

    OrderCenterStatusEnum(String code, String status, String msg) {
        this.code = code;
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    /**
     * @description 根据编码获取状态枚举
     * @author hantongyang
     * @time 2017/1/20 17:38
     * @method getByCode
     * @param code
     * @return com.mobanker.shanyidai.esb.common.constants.OrderCenterStatusEnum
     */
    public static OrderCenterStatusEnum getByCode(String code){
        if(StringUtils.isBlank(code)){
            throw new EsbException(ErrorConstant.ENUM_ERROR);
        }
        for (OrderCenterStatusEnum statusEnum : OrderCenterStatusEnum.values()){
            if(statusEnum.getStatus().equals(code)){
                return statusEnum;
            }
        }
        throw new EsbException(ErrorConstant.ENUM_ERROR);
    }
}
