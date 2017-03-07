package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.filemgt.contract.upload.dto.FileMgtDto;
import java.util.Map;

/**
 * Created by liuhanqing on 2017/1/9.
 * 文件上传
 */
public interface UploadFileDubbo {

    /**
     * 文件上传
     * @return
     */
    public Map<String,String> uploadFile(FileMgtDto dto);


    /**
     * 根据参数查询文件
     * @param dto
     * @return
     */
    public Map<String,String> queryFileByParams(FileMgtDto dto);

}
