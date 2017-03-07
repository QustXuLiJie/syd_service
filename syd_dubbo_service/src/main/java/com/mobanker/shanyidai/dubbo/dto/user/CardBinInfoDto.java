package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/1/4 18:18
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class CardBinInfoDto extends Entity {
    private String sourceId;
    private String bankId; //基础服务系统  银行卡主键
    private String bankName;//发卡行
    private String shortName;//发卡行简称
    private String cardLen;//卡号长度
    private String cardBin;//卡bin
    private String unionStandard;
    private String cardType;//银行卡类型

}
