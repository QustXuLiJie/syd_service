package com.mobanker.shanyidai.dubbo.dto.gather;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: xulijie
 * @description: 用户短信信息采集
 * @create time: 13:44 2017/2/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class MongoSmsInfoDto extends Entity{
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
