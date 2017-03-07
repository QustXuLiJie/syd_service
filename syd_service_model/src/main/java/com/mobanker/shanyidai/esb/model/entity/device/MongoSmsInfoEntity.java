package com.mobanker.shanyidai.esb.model.entity.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @description: 短信信息采集实体
 * @author: xulijie
 * @create time: 11:23 2017/2/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Document(collection = "nsms")
public class MongoSmsInfoEntity {
    private String _id;
    private Long user_id;//
    private Long addtime;//添加时间
    private String name;//姓名
    private String phone;//发件人号码
    private int type;//短信类型：1 - 接收到的 2 - 已发出的
    private String content;//短信内容
    private String calltime;//短信时间
    private String device_id = "";
    private String device_id_type;//类型Android ios
}
