package com.mobanker.shanyidai.esb.call.http;

import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto;
import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthUploadParamDto;
import com.mobanker.shanyidai.dubbo.dto.auth.WatermarkVerifyParamDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @desc: 活体识别--
 * @author: Richard Core
 * @create time: 2017/2/20 14:36
 */
public interface FaceAuthHttp {
    /**
     * @param reqUrl
     * @param paramsDto
     * @param file
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 人脸识别上传服务
     * @author Richard Core
     * @time 2017/2/21 15:25
     * @method faceAuthUpload
     */
    public FaceAuthResultDto faceAuthUpload(String reqUrl, FaceAuthUploadParamDto paramsDto, MultipartFile file);

    /**
     * @param restUrl
     * @param livenessDataId
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 活体识别-活体防伪
     * @author Richard Core
     * @time 2017/2/22 14:19
     * @method faceAuthHackDetect
     */
    public FaceAuthResultDto faceAuthHackDetect(String restUrl, String livenessDataId);

    /**
     * @param restUrl
     * @param paramDto
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 活体数据与水印照片比对
     * @author Richard Core
     * @time 2017/2/22 17:21
     * @method faceAuthWatermarkVerify
     */
    public FaceAuthResultDto faceAuthWatermarkVerify(String restUrl, WatermarkVerifyParamDto paramDto);
}
