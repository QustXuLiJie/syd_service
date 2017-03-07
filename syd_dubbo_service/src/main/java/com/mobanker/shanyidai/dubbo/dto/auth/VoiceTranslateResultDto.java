package com.mobanker.shanyidai.dubbo.dto.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/14 13:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class VoiceTranslateResultDto implements Serializable {
    private String billNo; // 流水号
    private String skuNumber; // 唯一标示
    private String orderNo; // 订单号
    private String audioId; // 音频ID
    private String result; // 内容返回
    private String status; // 是否转换成功0失败1成功2超时失败

}
