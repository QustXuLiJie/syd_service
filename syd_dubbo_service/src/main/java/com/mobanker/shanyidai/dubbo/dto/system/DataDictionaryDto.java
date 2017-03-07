package com.mobanker.shanyidai.dubbo.dto.system;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/23 11:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class DataDictionaryDto extends Entity {
    private Long id; //ID
    private String dicType; //字典类型
    private String dicName; //字典名称
    private String dicCode; //字典编码
    private String dicContent; //字典内容，保存json字符串，用于业务字段
    private String status; //是否可用，1可用，0不可用
    private String remark; //备注
}
