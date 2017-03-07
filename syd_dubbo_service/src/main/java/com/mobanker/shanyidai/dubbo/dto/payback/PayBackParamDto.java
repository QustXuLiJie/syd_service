package com.mobanker.shanyidai.dubbo.dto.payback;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @desc: 还款信息查询参数
 * @author: Richard Core
 * @create time: 2017/1/10 11:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class PayBackParamDto extends Entity {
    private Long userId;//用户id
    private String borrowNid;//借款单Id
}
