package com.mobanker.shanyidai.esb.model.dto.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: 人脸识别生成图片和认证记录关联表
 * @author: Richard Core
 * @create time: 2017/2/26 10:36
 */
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class FacePicRefBean implements Serializable{
    private Long userId;//用户Id
    private List<String> picPath;//上传图片路径
    private String livenessDataId;//活体数据id  保存图片关联使用
}
