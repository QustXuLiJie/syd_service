package com.mobanker.shanyidai.dubbo.dto.auth;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @desc:活体识别参数
 * @author: Richard Core
 * @create time: 2017/2/20 17:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class FaceAuthUploadParamDto extends Entity {
    private Long userId;//用户Id
    private String filePath;//上传到云端的路径
    private Date methodBeginDate;//方法开始调用时间 记录日志使用
    private List<String> picPath;//上传图片路径

    private String livenessDataId;//活体数据id  保存图片关联使用

}
