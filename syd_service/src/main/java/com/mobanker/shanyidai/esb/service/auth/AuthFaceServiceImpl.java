package com.mobanker.shanyidai.esb.service.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.AuthFaceRecordDto;
import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto;
import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthUploadParamDto;
import com.mobanker.shanyidai.dubbo.dto.auth.WatermarkVerifyParamDto;
import com.mobanker.shanyidai.dubbo.service.auth.AuthFaceDubboService;
import com.mobanker.shanyidai.esb.business.auth.FaceAuthBusiness;
import com.mobanker.shanyidai.esb.common.constants.*;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import com.mobanker.shanyidai.esb.model.dto.auth.FacePicRefBean;
import com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity;
import com.mobanker.shanyidai.esb.service.auth.util.AuthDubboConvert;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/21 19:34
 */
public class AuthFaceServiceImpl implements AuthFaceDubboService {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(AuthFaceServiceImpl.class);
    @Resource
    private FaceAuthBusiness faceAuthBusiness;

    /**
     * @param paramsDto
     * @param file
     * @return ResponseEntity
     * @description 人脸识别上传服务
     * @author Richard Core
     * @time 2017/2/21 15:25
     * @method faceAuthUpload
     */
    @Override
    public ResponseEntity faceAuthUpload(FaceAuthUploadParamDto paramsDto, MultipartFile file) {
        if (paramsDto == null || file == null || StringUtils.isBlank(paramsDto.getFilePath())) {
            ResponseBuilder.errorResponse(ErrorConstant.FACE_AUTH_UPLOAD_FAILD, this.getClass().getSimpleName(), "faceAuthUpload");
        }
        FaceAuthResultDto resultDto = null;
        try {
            //人脸识别上传服务
            resultDto = faceAuthBusiness.faceAuthUpload(paramsDto, file);
            //验证人脸识别认证过程中返回值是否成功 为了将请求信息保存下来 对返回的status进行判断
            checkFaceAuthResult(resultDto);
            //记录进度
            saveUploadRecord(resultDto, FaceAuthProcessEnum.HEAK_DETECT, paramsDto, null);
            return ResponseBuilder.normalResponse(resultDto);
        } catch (Exception e) {
            //记录进度
            if (resultDto == null) {
                resultDto = new FaceAuthResultDto();
            }
            if (StringUtils.isBlank(resultDto.getLivenessDataId())) {
                Long userId = paramsDto.getUserId();
                String randomDataId = AuthDubboConvert.getRandomDataId(userId);
                resultDto.setLivenessDataId(randomDataId);
            }
            saveUploadRecord(resultDto, FaceAuthProcessEnum.UPLOAD_FAIL, paramsDto, e);
            return ResponseBuilder.errorResponse(ErrorConstant.FACE_AUTH_UPLOAD_FAILD, e, this.getClass().getSimpleName(), "faceAuthUpload");
        }
    }

    /**
     * @param resultDto
     * @param authProcess
     * @param paramDto
     * @param e
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity
     * @description 人脸识别进度记录
     * @author Richard Core
     * @time 2017/2/23 16:50
     * @method saveAuthFaceRecord
     */
    private void saveUploadRecord(FaceAuthResultDto resultDto, FaceAuthProcessEnum authProcess, FaceAuthUploadParamDto paramDto, Throwable e) {
        AuthFaceRecordEntity entity = mapFaceResultCommonField(resultDto, e, authProcess);
        entity.setLivenessDataId(resultDto.getLivenessDataId());   //语音识别数据id
        entity.setUploadReqId(resultDto.getRequestId());   //上传请求id
        entity.setUploadSid(resultDto.getTranserialsId());   //上传流水号
        entity.setUserId(paramDto.getUserId());
        entity.setFilePath(paramDto.getFilePath());
        entity.setUploadBeginTime(paramDto.getMethodBeginDate());
        Date end = new Date();
        entity.setUploadEndTime(end);
        entity.setUploadDuration(end.getTime() - paramDto.getMethodBeginDate().getTime());
        faceAuthBusiness.saveAuthFaceRecord(entity);

        List<String> picPathList = paramDto.getPicPath();
        if (picPathList != null && !picPathList.isEmpty()) {
            FacePicRefBean refBean = new FacePicRefBean();
            refBean.setLivenessDataId(resultDto.getLivenessDataId());
            refBean.setUserId(paramDto.getUserId());
            refBean.setPicPath(picPathList);
            faceAuthBusiness.saveRefFacePic(refBean);
        }

    }

    /**
     * @param resultDto
     * @param authProcess
     * @return AuthFaceRecordEntity
     * @description 封装公共字段
     * @author Richard Core
     * @time 2017/2/23 17:44
     * @method mapFaceResultCommonField
     */
    private AuthFaceRecordEntity mapFaceResultCommonField(FaceAuthResultDto resultDto, Throwable e, FaceAuthProcessEnum authProcess) {
        AuthFaceRecordEntity entity = new AuthFaceRecordEntity();
        if (resultDto != null) {
            entity.setStatus(resultDto.getStatus());   //认证记录状态
            entity.setReason(resultDto.getReason());   //失败原因
            entity.setScore(resultDto.getScore());   //防伪分数
            entity.setHackScore(resultDto.getHackScore());   //防伪得分
            entity.setVerifyScore(resultDto.getVerifyScore());   //置信度
            entity.setImageId(resultDto.getImageId());   //带水印照片的id
            entity.setChannel(resultDto.getChannel());   //认证渠道
        }
        entity.setProcess(authProcess.getProcess());   //认证进度，AuthFaceProcessEnum
        if (e != null) {
            if (e instanceof EsbException) {
                EsbException esb = (EsbException) e;
                entity.setErrorMsg(esb.getMessage());
            } else {
                entity.setErrorMsg(CommonUtil.getStackTrace(e));
            }
        }
        return entity;
    }

    /**
     * @param result
     * @return com.mobanker.shanyidai.api.dto.auth.FaceAuthResult
     * @description 验证人脸识别认证过程中返回值是否成功
     * @author Richard Core
     * @time 2017/2/23 10:19
     * @method saveFaceAuthProcess
     */
    private FaceAuthResultDto checkFaceAuthResult(FaceAuthResultDto result) {
        FaceAuthResultStatusEnum instance = FaceAuthResultStatusEnum.getInstance(result.getStatus());
        switch (instance) {
            case SUCCESS:
                return result;
            default:
                LOGGER.warn("人脸识别失败" + result);
                if (StringUtils.isBlank(result.getReason())) {
                    throw new EsbException(instance.getErrorConstant().getCode(), instance.getErrorConstant().getDesc());
                } else {
                    throw new EsbException(instance.getErrorConstant().getCode(), result.getReason() + instance.getErrorConstant().getDesc());
                }
        }
    }

    /**
     * @param livenessDataId
     * @return ResponseEntity
     * @description 活体识别-活体防伪
     * @author Richard Core
     * @time 2017/2/22 14:19
     * @method faceAuthHackDetect
     */
    @Override
    public ResponseEntity faceAuthHackDetect(String livenessDataId) {
        FaceAuthResultDto resultDto = null;
        Date methodBeginDate = new Date();
        try {
            //活体防伪
            resultDto = faceAuthBusiness.faceAuthHackDetect(livenessDataId);
            //验证返回状态
            checkFaceAuthResult(resultDto);
            String hackScore = resultDto.getScore();
            if (StringUtils.isBlank(hackScore) || !FaceHackEnum.HACKNORMAL.getHackResult().equals(hackScore)) {
                throw new EsbException(ErrorConstant.FACE_AUTH_WATERMARK_VERIFY_FAILD.getCode(), "防伪认证未通过");
            }
            //记录进度
            updateAuthHackRecord(resultDto, FaceAuthProcessEnum.VERIFY, livenessDataId, null, methodBeginDate);
            return ResponseBuilder.normalResponse(resultDto);
        } catch (Exception e) {
            //记录进度
            updateAuthHackRecord(resultDto, FaceAuthProcessEnum.HEAK_FAIL, livenessDataId, e, methodBeginDate);
            return ResponseBuilder.errorResponse(ErrorConstant.FACE_AUTH_HACK_DETECT_FAILD, e, this.getClass().getSimpleName(), "faceAuthHackDetect");
        }
    }

    /**
     * @param resultDto
     * @param authProcess
     * @param livenessDataId
     * @param e
     * @param methodBeginDate
     * @return void
     * @description 记录活体防伪进度
     * @author Richard Core
     * @time 2017/2/23 17:57
     * @method updateAuthHackRecord
     */
    private void updateAuthHackRecord(FaceAuthResultDto resultDto, FaceAuthProcessEnum authProcess, String livenessDataId, Throwable e, Date methodBeginDate) {
        AuthFaceRecordEntity entity = mapFaceResultCommonField(resultDto, e, authProcess);
        entity.setLivenessDataId(livenessDataId);   //语音识别数据id
        if (resultDto != null) {
            entity.setHackReqId(resultDto.getRequestId());   //上传请求id
            entity.setHackSid(resultDto.getTranserialsId());   //上传流水号
        }
        entity.setHackBeginTime(methodBeginDate);
        Date hackEndTime = new Date();
        entity.setHackEndTime(hackEndTime);
        entity.setHackDuration(hackEndTime.getTime() - methodBeginDate.getTime());
        faceAuthBusiness.updateByDataId(entity);
    }

    /**
     * @param paramDto
     * @return ResponseEntity
     * @description 活体数据与水印照片比对
     * @author Richard Core
     * @time 2017/2/22 17:21
     * @method faceAuthWatermarkVerify
     */
    @Override
    public ResponseEntity faceAuthWatermarkVerify(WatermarkVerifyParamDto paramDto) {
        FaceAuthResultDto resultDto = null;
        Date methodBeginDate = new Date();
        try {
            //活体数据与水印照片比对
            resultDto = faceAuthBusiness.faceAuthWatermarkVerify(paramDto);
            //验证返回状态
            checkFaceAuthResult(resultDto);
            checkVerifyResult(resultDto);
            //记录进度
            updateAuthVerifyRecord(resultDto, FaceAuthProcessEnum.SUCCESS, paramDto, null, methodBeginDate);
            return ResponseBuilder.normalResponse(resultDto);
        } catch (Exception e) {
            updateAuthVerifyRecord(resultDto, FaceAuthProcessEnum.VERIFY_FAIL, paramDto, e, methodBeginDate);
            return ResponseBuilder.errorResponse(ErrorConstant.FACE_AUTH_WATERMARK_VERIFY_FAILD, e, this.getClass().getSimpleName(), "faceAuthWatermarkVerify");
        }
    }

    /**
     * @param resultDto
     * @return void
     * @description 认证分数判断
     * @author Richard Core
     * @time 2017/3/1 14:58
     * @method checkVerifyResult
     */
    private void checkVerifyResult(FaceAuthResultDto resultDto) {
        String hackScore = resultDto.getHackScore();
        String verifyScore = resultDto.getVerifyScore();
        if (StringUtils.isBlank(hackScore) || !FaceHackEnum.HACKNORMAL.getHackResult().equals(hackScore)) {
            throw new EsbException(ErrorConstant.FACE_AUTH_WATERMARK_VERIFY_FAILD.getCode(), "防伪认证未通过");
        }
        if (StringUtils.isBlank(verifyScore) || !FaceVerifyEnum.COMPARESUCCES.getVerifyResult().equals(verifyScore)) {
            throw new EsbException(ErrorConstant.FACE_AUTH_WATERMARK_VERIFY_FAILD.getCode(), "水印照片认证未通过");
        }
    }

    /**
     * @param resultDto
     * @param authProcess
     * @param paramDto
     * @param e
     * @param methodBeginDate
     * @return void
     * @description 记录活体防伪进度
     * @author Richard Core
     * @time 2017/2/23 17:57
     * @method updateAuthHackRecord
     */
    private void updateAuthVerifyRecord(FaceAuthResultDto resultDto, FaceAuthProcessEnum authProcess, WatermarkVerifyParamDto paramDto, Throwable e, Date methodBeginDate) {
        AuthFaceRecordEntity entity = mapFaceResultCommonField(resultDto, e, authProcess);
        entity.setLivenessDataId(paramDto.getLivenessDataId());   //语音识别数据id
        entity.setRealName(paramDto.getRealName());
        entity.setIdCardNo(paramDto.getIdCardNo());
        if (resultDto != null) {
            entity.setVerifyReqId(resultDto.getRequestId());   //上传请求id
            entity.setVerifySid(resultDto.getTranserialsId());   //上传流水号
        }
        entity.setVerifyBeginTime(methodBeginDate);
        Date verifyEndTime = new Date();
        entity.setVerifyEndTime(verifyEndTime);
        entity.setVerifyDuration(verifyEndTime.getTime() - methodBeginDate.getTime());
        faceAuthBusiness.updateByDataId(entity);
    }

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 查询人脸识别进度
     * @author Richard Core
     * @time 2017/2/23 20:11
     * @method getAuthFaceprocess
     */
    @Override
    public ResponseEntity getAuthFaceprocess(Long userId) {
        try {
            AuthFaceRecordEntity entity = faceAuthBusiness.findByUserId(userId);

            AuthFaceRecordDto result = BeanUtil.cloneBean(entity, AuthFaceRecordDto.class);
            if (entity != null && StringUtils.isNotBlank(entity.getLivenessDataId())) {
                List<String> picList = faceAuthBusiness.findRefPicByDataId(entity.getLivenessDataId());
                result.setPicList(picList);
            }
            return ResponseBuilder.normalResponse(result);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.FACE_AUTH_PROCESS_ERROR, e, this.getClass().getSimpleName(), "getAuthFaceprocess");
        }
    }

    /**
     * @param userId
     * @return int
     * @description 获取用户当天人脸验证次数
     * @author Richard Core
     * @time 2017/3/4 15:47
     * @method getAuthFaceTimesToday
     */
    @Override
    public ResponseEntity getAuthFaceTimesToday(Long userId) {
        try {
            int times = faceAuthBusiness.getAuthFaceTimesToday(userId);
            return ResponseBuilder.normalResponse(times);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.FACE_AUTH_PROCESS_ERROR, e, this.getClass().getSimpleName(), "getAuthFaceTimesToday");
        }
    }
}
