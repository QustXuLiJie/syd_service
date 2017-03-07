package com.mobanker.shanyidai.esb.business.upload;

import com.mobanker.filemgt.contract.upload.dto.FileMgtDto;
import com.mobanker.shanyidai.esb.call.dubbo.UploadFileDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by liuhanqing on 2017/1/9.
 * 上传文件
 */
@Service
public class UploadFileBusinessImpl implements UploadFileBusiness{

    @Resource
    UploadFileDubbo uploadFileDubbo;

    @Override
    public Map<String,String> uploadFile(FileMgtDto dto) {
        Map<String,String> map;
        try {
            map = uploadFileDubbo.uploadFile(dto);
            if(map == null || map.size() == 0){
                throw new EsbException(ErrorConstant.UPLOAD_FILE_ERROR);
            }
            return map;
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.UPLOAD_FILE_ERROR, e);
        }
    }

    @Override
    public  Map<String,String> queryFileByParams(FileMgtDto dto) {

        Map<String,String> map;
        try {
            map = uploadFileDubbo.queryFileByParams(dto);
            if(map == null){
                throw new EsbException(ErrorConstant.QUERY_FILE_ERROR);
            }
            return map;
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.QUERY_FILE_ERROR, e);
        }
    }
}
