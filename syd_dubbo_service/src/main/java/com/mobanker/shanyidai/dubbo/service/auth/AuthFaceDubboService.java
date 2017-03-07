package com.mobanker.shanyidai.dubbo.service.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthUploadParamDto;
import com.mobanker.shanyidai.dubbo.dto.auth.WatermarkVerifyParamDto;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @desc: 活体识别相关
 * @author: Richard Core
 * @create time: 2017/2/21 19:34
 */
public interface AuthFaceDubboService {
    /**
     * @param paramsDto
     * @param file
     * @return ResponseEntity
     * @description 人脸识别上传服务
     * @author Richard Core
     * @time 2017/2/21 15:25
     * @method faceAuthUpload
     */
    @NoEELog
    public ResponseEntity faceAuthUpload(FaceAuthUploadParamDto paramsDto, MultipartFile file);

    /**
     * @param livenessDataId
     * @return ResponseEntity
     * @description 活体识别-活体防伪
     * @author Richard Core
     * @time 2017/2/22 14:19
     * @method faceAuthHackDetect
     */
    public ResponseEntity faceAuthHackDetect(String livenessDataId);

    /**
     * @param paramDto
     * @return ResponseEntity
     * @description 活体数据与水印照片比对
     * @author Richard Core
     * @time 2017/2/22 17:21
     * @method faceAuthWatermarkVerify
     */
    public ResponseEntity faceAuthWatermarkVerify(WatermarkVerifyParamDto paramDto);

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 查询人脸识别进度
     * @author Richard Core
     * @time 2017/2/23 20:11
     * @method getAuthFaceprocess
     */
    public ResponseEntity getAuthFaceprocess(Long userId);

    /**
     * @param userId
     * @return int
     * @description 获取用户当天人脸验证次数
     * @author Richard Core
     * @time 2017/3/4 15:47
     * @method getAuthFaceTimesToday
     */
    public ResponseEntity getAuthFaceTimesToday(Long userId);
}
