package com.mobanker.shanyidai.esb.service.upload;


import com.github.pagehelper.StringUtil;
//import com.mobanker.filemgt.contract.upload.dto.FileMgtDto;
import com.mobanker.filemgt.contract.upload.dto.FileMgtDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;

/**
 * @author liuhanqing
 * @description 上传参数的验证
 * @date 2017/01/10 13:48
 */
public class UploadEsbConvert {


    /**
     * 检查上传参数
     * @param dto
     */
    public static void checkUploadParam(FileMgtDto dto){
        if (dto.getUserId() == 0)
        {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }

        if (StringUtil.isEmpty(dto.getType())){
            throw new EsbException(ErrorConstant.FILE_TYPE_NOTNULL);
        }
    }

}
