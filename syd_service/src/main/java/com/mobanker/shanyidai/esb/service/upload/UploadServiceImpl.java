package com.mobanker.shanyidai.esb.service.upload;

import com.github.pagehelper.StringUtil;
import com.mobanker.filemgt.contract.upload.dto.FileMgtDto;
import com.mobanker.shanyidai.dubbo.dto.upload.FileActionDto;
import com.mobanker.shanyidai.dubbo.service.upload.UploadFileDubboService;
import com.mobanker.shanyidai.esb.business.upload.UploadFileBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by liuhanqing on 2017/1/9.
 * 文件上传
 */
public class UploadServiceImpl implements UploadFileDubboService{

    @Resource
    UploadFileBusiness uploadFileBusiness;

    /**
     * 上传文件
     * @return
     */
    @Override
    public ResponseEntity uploadFile(FileActionDto dto) {

        if (dto == null || dto.getUserId() == 0 || StringUtil.isEmpty(dto.getFileType())
                ||StringUtil.isEmpty(dto.getRemoteIp())){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "uploadFile");
        }

        FileMgtDto fileMgtDto = new FileMgtDto();
        fileMgtDto.setFile(dto.getFile());
        fileMgtDto.setType(dto.getFileType());
        fileMgtDto.setUserId(dto.getUserId());
        fileMgtDto.setRemoteIp(dto.getRemoteIp());
        try {
            UploadEsbConvert.checkUploadParam(fileMgtDto);
            Map<String, String> map = uploadFileBusiness.uploadFile(fileMgtDto);
            return ResponseBuilder.normalResponse(map);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.UPLOAD_FILE_ERROR, e, this.getClass().getSimpleName(), "uploadFile");
        }
    }

    @Override
    public ResponseEntity queryFileByParams(FileActionDto dto) {

        if (dto == null || dto.getUserId() == 0 || StringUtil.isEmpty(dto.getFileType())){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "queryFileByParams");
        }
        FileMgtDto fileMgtDto = new FileMgtDto();
        fileMgtDto.setType(dto.getFileType());
        fileMgtDto.setUserId(dto.getUserId());
        try {
            UploadEsbConvert.checkUploadParam(fileMgtDto);
            Map<String, String> map = uploadFileBusiness.queryFileByParams(fileMgtDto);
            return ResponseBuilder.normalResponse(map);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.QUERY_FILE_ERROR, e, this.getClass().getSimpleName(), "queryFileByParams");
        }
    }
}
