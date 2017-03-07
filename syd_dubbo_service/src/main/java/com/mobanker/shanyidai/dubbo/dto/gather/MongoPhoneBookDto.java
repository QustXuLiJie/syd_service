package com.mobanker.shanyidai.dubbo.dto.gather;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: xulijie
 * @description: 通讯录信息采集
 * @create time: 10:27 2017/2/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class MongoPhoneBookDto extends Entity{
    private String _id;
    private Long user_id;//
    private Long addtime;//添加时间
    private String tel;//电话号码
    private String name;//姓名
    private Long updatatime;//号码更新时间
    private String device_id = "";
    private String device_id_type;//类型Android ios
}
