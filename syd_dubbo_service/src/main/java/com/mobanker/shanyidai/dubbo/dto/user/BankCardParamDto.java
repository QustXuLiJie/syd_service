package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @desc: 银行卡查询参数
 * @author: Richard Core
 * @create time: 2017/1/3 10:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BankCardParamDto extends Entity {
    private Long userId; //用户 userId
    private String type; //银行卡类型  debitCard(借记卡)，creditCard(信用卡)
//    private Long cardId; //银行卡号
    private List<String> fields;//需要查询的字段
    private Map<String, Object> saveFields;//需要保存的字段
    private Map<String, Object> commonFields;//公共字段
}
