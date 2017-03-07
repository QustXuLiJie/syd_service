package com.mobanker.shanyidai.dubbo.dto.gather;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

/**
 * @author: xulijie
 * @description: 通话记录信息采集
 * @create time: 10:40 2017/2/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class MongoCallRecordsDto extends Entity {
    private String _id;
    private Long user_id;//
    private Long addtime;//添加时间
    private String phone;//号码
    private int type;//呼叫类型：1–呼入 2–呼出 3–错过未接 4–语音邮件
    private String name;//姓名
    private String calltime;//通话时间
    private String duration;//通话时长
    private String device_id = "";
    private String device_id_type;//类型Android ios
}
