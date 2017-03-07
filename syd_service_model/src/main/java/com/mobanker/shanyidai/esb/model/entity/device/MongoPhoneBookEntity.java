package com.mobanker.shanyidai.esb.model.entity.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @description: 通讯录信息采集实体
 * @author: xulijie
 * @create time: 11:23 2017/2/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Document(collection = "ncontacts")
public class MongoPhoneBookEntity {
    private String _id;
    private Long user_id;//
    private Long addtime;//添加时间
    private String tel;//电话号码
    private String name;//姓名
    private Long updatatime;//号码更新时间
    private String device_id = "";
    private String device_id_type;//类型Android ios
}
