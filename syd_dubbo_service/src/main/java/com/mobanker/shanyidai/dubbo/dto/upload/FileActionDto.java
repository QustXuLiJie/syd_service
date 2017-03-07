package com.mobanker.shanyidai.dubbo.dto.upload;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by liuhanqing on 2017/1/9.
 * 文件操作的dto
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class FileActionDto extends Entity{

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 设备的ip
     */
    private String remoteIp;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 上传的文件
     */
    private MultipartFile file;

    /**
     *上传文件本地缓存路径
     */
    private String tempFilePath;

}
