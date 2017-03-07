package com.mobanker.shanyidai.dubbo.dto.gather;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: xulijie
 * @description: lbs定位采集信息
 * @create time: 18:25 2017/2/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class MongoLbsDto extends Entity {
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
