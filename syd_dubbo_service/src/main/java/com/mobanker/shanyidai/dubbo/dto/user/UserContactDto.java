package com.mobanker.shanyidai.dubbo.dto.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/28 15:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserContactDto extends Entity {

    private Long userId;
    private String type;
    private List<String> fields;

    private List<Map<String, Object>> saveFields; //用户联系人信息，不能为空
    private Map<String, Object> commonFields; //协议参数,不能为空
}
