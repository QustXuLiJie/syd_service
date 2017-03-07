package com.mobanker.shanyidai.esb.model.entity.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @description: lbs信息采集实体
 * @author: xulijie
 * @create time: 11:22 2017/2/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Document(collection="lbs")
public class MongoLbsEntity {
    private String _id;
    private Long user_id;
    private Long addtime;//添加时间
    private String type;//操作类型
    private String lat;//经度
    private String lon;//纬度
    private String map;//地图
    private String device_id = "";
    private String device_id_type;//类型Android ios
}
