package com.mobanker.shanyidai.dubbo.dto.auth;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author liuhanqing
 * @description 语音验证的dto
 * @time 2017/2/9.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class VoiceAuthDto extends Entity{

    private String duration; /** 文件时长 */
    private String fileName; /** 文件名称  单位毫秒 */
    private String fileSize; /** 文件大小  单位字节 */
    private MultipartFile file; /** 文件 */

    private Long userId;
    private String filePath;//阿里云或者UC的上传地址
    private Date uploadBeginDate;//记录上传开始时间，统计上传时长使用
    private String realName;//真实姓名
    private String phone;//手机号

}
