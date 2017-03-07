package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @desc: 银行卡信息
 * @author: Richard Core
 * @create time: 2017/1/3 16:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BankCardDto extends Entity {
    private String bankCardNo;//银行卡号
    private String bankName;//开户行名称
    private String phone;     //银行预留手机号
    private String cardType;//银行卡类型  枚举  C:贷记卡（信用卡） D:借记卡（储蓄卡）
    private int cardYstatus;//银行卡验证状态
    private Long userId;//用户userId
    private String realName;//用户实名
    private String idCard;//身份证号
}
