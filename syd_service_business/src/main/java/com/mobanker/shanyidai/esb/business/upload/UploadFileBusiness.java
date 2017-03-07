package com.mobanker.shanyidai.esb.business.upload;

import com.mobanker.filemgt.contract.upload.dto.FileMgtDto;

import java.util.Map;

/**
 * Created by liuhanqing on 2017/1/9.
 * 文件上传
 */
public interface UploadFileBusiness {

    /**
     * 文件上传
     * @param file  文件
     * @param type  文件类型
     * @param userId  用户id
     * @return
     */
    public Map<String,String> uploadFile(FileMgtDto dto);


    /**
     * 根据参数查询文件
     * @param dto
     * @return
     */
    public  Map<String,String> queryFileByParams(FileMgtDto dto);
}
