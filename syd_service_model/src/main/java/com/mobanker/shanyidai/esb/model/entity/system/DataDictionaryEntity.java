package com.mobanker.shanyidai.esb.model.entity.system;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/23 11:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_data_dictionary")
public class DataDictionaryEntity extends BaseEntity {

    private Long id; //ID
    private String dicType; //字典类型
    private String dicName; //字典名称
    private String dicCode; //字典编码
    private String dicContent; //字典内容，保存json字符串，用于业务字段
    private String status; //是否可用，1可用，0不可用
    private String remark; //备注
}
