package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/27 21:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserBaseInfoDto extends Entity {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private List<String> fields;
    private Map<String, Object> saveFields;
    private Map<String, Object> commonFields;
}
