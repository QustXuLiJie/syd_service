package com.mobanker.shanyidai.dubbo.service.upload;

import com.mobanker.shanyidai.dubbo.dto.upload.FileActionDto;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;


/**
 * Created by liuhanqing on 2017/1/9.
 * 上传文件的服务
 */
@NoEELog
public interface UploadFileDubboService {

    /**
     * 上传文件
     * @param dto  文件操作的dto
     * @return
     */
    public ResponseEntity uploadFile(FileActionDto dto);


    /**
     * 根据参数查询文件
     * @param dto  文件操作的dto
     * @return
     */
    public ResponseEntity queryFileByParams(FileActionDto dto);

}
