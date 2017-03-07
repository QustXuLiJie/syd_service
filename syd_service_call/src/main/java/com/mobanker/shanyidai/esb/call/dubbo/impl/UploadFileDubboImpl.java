package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.mobanker.filemgt.contract.upload.dto.FileMgtDto;
import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.shanyidai.esb.call.dubbo.UploadFileDubbo;
import com.mobanker.filemgt.contract.upload.DubboFileMgtManager;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by liuhanqing on 2017/1/9.
 * 文件上传
 */
@Service
public class UploadFileDubboImpl implements UploadFileDubbo {

    public static final Logger logger = LogManager.getLogger(BankCardDubboImpl.class);

    @Resource
    DubboFileMgtManager dubboFileMgtManager;

    /**
     * 文件上传
     * @param dto
     * @return
     */
    @Override
    public Map<String,String> uploadFile(FileMgtDto dto) {
        ResponseEntity<Map<String,String>> responseEntity;
        try {
            responseEntity = dubboFileMgtManager.upload(dto);

        } catch (Exception e) {
            logger.error("上传文件异常:"+e.getMessage());
            throw new EsbException(ErrorConstant.SERVICE_FAIL,e);
        }
        if (!CallResultUtil.isSuccess(responseEntity)) {
            logger.warn("上传文件失败", responseEntity);
            throw new EsbException(ErrorConstant.UPLOAD_FILE_ERROR.getCode(),responseEntity.getMsg());
        }
        return responseEntity.getData();

    }

    /**
     * 查询文件
     * @param dto
     * @return
     */
    @Override
    public Map<String, String> queryFileByParams(FileMgtDto dto) {

        ResponseEntity<Map<String,String>> responseEntity;
        try {
            responseEntity = dubboFileMgtManager.getFileMgtByParams(dto);

        } catch (Exception e) {
            logger.error("查询文件异常");
            throw new EsbException(ErrorConstant.SERVICE_FAIL,e);
        }
        if (!CallResultUtil.isSuccess(responseEntity)) {
            logger.warn("查询文件失败", responseEntity);
            throw new EsbException(ErrorConstant.QUERY_FILE_ERROR.getCode(),responseEntity.getMsg());
        }
        return responseEntity.getData();
    }
}
