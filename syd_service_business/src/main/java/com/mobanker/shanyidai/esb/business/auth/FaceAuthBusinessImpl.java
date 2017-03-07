package com.mobanker.shanyidai.esb.business.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto;
import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthUploadParamDto;
import com.mobanker.shanyidai.dubbo.dto.auth.WatermarkVerifyParamDto;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.call.http.FaceAuthHttp;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.dao.auth.AuthFaceRecordMapper;
import com.mobanker.shanyidai.esb.model.dto.auth.FacePicRefBean;
import com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @desc: 人脸  活体识别相关
 * @author: Richard Core
 * @create time: 2017/2/21 11:59
 */
@Service
public class FaceAuthBusinessImpl implements FaceAuthBusiness {
    @Resource
    private FaceAuthHttp faceAuthHttp;
    @Resource
    private EsbCommonBusiness esbCommonBusiness;
    @Resource
    private AuthFaceRecordMapper authFaceRecordMapper;

    /**
     * @param paramsDto
     * @param file
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 人脸识别上传服务
     * @author Richard Core
     * @time 2017/2/21 15:25
     * @method faceAuthUpload
     */
    @Override
    public FaceAuthResultDto faceAuthUpload(FaceAuthUploadParamDto paramsDto, MultipartFile file) {
//        String reqUrl = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.FACE_VOICE_UPLOAD_REQURL.getZkValue(),
//                ErrorConstant.CONFIG_DATA_NULL.getCode(), "认证系统上传人脸识别文件服务地址获取失败");
        String reqUrl = "http://192.168.1.61:8080/auth/api/1.0.0/shangtang/uploadLivenessData";
        FaceAuthResultDto resultDto = faceAuthHttp.faceAuthUpload(reqUrl, paramsDto, file);
        return resultDto;
    }

    /**
     * @param livenessDataId
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 活体识别-活体防伪
     * @author Richard Core
     * @time 2017/2/22 14:19
     * @method faceAuthHackDetect
     */
    @Override
    public FaceAuthResultDto faceAuthHackDetect(String livenessDataId) {
//        String reqUrl = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.FACE_VOICE_UPLOAD_REQURL.getZkValue(),
//                ErrorConstant.CONFIG_DATA_NULL.getCode(), "认证系统活体防伪服务地址获取失败");
        String reqUrl = "http://192.168.1.61:8080/auth/api/1.0.0/shangtang/livenessHackDetect";
        FaceAuthResultDto resultDto = faceAuthHttp.faceAuthHackDetect(reqUrl, livenessDataId);
        return resultDto;
    }

    /**
     * @param paramDto
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 活体数据与水印照片比对
     * @author Richard Core
     * @time 2017/2/22 17:21
     * @method faceAuthWatermarkVerify
     */
    @Override
    public FaceAuthResultDto faceAuthWatermarkVerify(WatermarkVerifyParamDto paramDto) {
//        String reqUrl = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.FACE_VOICE_UPLOAD_REQURL.getZkValue(),
//                ErrorConstant.CONFIG_DATA_NULL.getCode(), "认证系统与带水印照片比对服务地址获取失败");
        String reqUrl = "http://192.168.1.61:8080/auth/api/1.0.0/shangtang/livenessWatermarkVerification";
        FaceAuthResultDto resultDto = faceAuthHttp.faceAuthWatermarkVerify(reqUrl, paramDto);
        return resultDto;
    }


    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity
     * @description 根据userId查询用户最后一次人脸识别进度
     * @author Richard Core
     * @time 2017/2/23 15:32
     * @method findByUserId
     */
    @Override
    public AuthFaceRecordEntity findByUserId(Long userId) {
        AuthFaceRecordEntity entity = authFaceRecordMapper.findLastByUserId(userId);

        return entity;
    }

    /**
     * @param dataId
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity
     * @description 根据dataId查询用户人脸识别进度
     * @author Richard Core
     * @time 2017/2/23 15:32
     * @method findByDataId
     */
    @Override
    public AuthFaceRecordEntity findByDataId(String dataId) {
        if (StringUtils.isBlank(dataId)) {
            return null;
        }
        AuthFaceRecordEntity entity = authFaceRecordMapper.findByDataId(dataId);
        return entity;
    }

    /**
     * @param entity
     * @return void
     * @description 更新人脸识别信息
     * @author Richard Core
     * @time 2017/2/23 15:43
     * @method updateByDataId
     */
    @Override
    public void updateByDataId(AuthFaceRecordEntity entity) {
        if (entity == null || StringUtils.isBlank(entity.getLivenessDataId())) {
            throw new EsbException(ErrorConstant.UPDATE_AUTH_FACE_RECORD);
        }
        int count = authFaceRecordMapper.updateByDataId(entity);
        if (count < 1) {
            throw new EsbException(ErrorConstant.AUTH_DAO_FAIL.getCode(), "更新人脸识别进度失败");
        }
    }

    /**
     * @param entity
     * @return void
     * @description 保存人脸识别进度信息
     * @author Richard Core
     * @time 2017/2/23 15:43
     * @method saveAuthFaceRecord
     */
    @Override
    public void saveAuthFaceRecord(AuthFaceRecordEntity entity) {
        if (entity == null || StringUtils.isBlank(entity.getLivenessDataId())) {
            throw new EsbException(ErrorConstant.UPDATE_AUTH_FACE_RECORD);
        }
        int count = authFaceRecordMapper.insert(entity);
        if (count < 1) {
            throw new EsbException(ErrorConstant.AUTH_DAO_FAIL.getCode(), "保存人脸识别进度信息失败");
        }
    }

    /**
     * @param refBean
     * @return void
     * @description 保存人脸识别生成图片和认证记录关联
     * @author Richard Core
     * @time 2017/2/26 10:34
     * @method saveRefFacePic
     */
    @Override
    public void saveRefFacePic(FacePicRefBean refBean) {
        if (refBean == null || refBean.getUserId() == null ||
                StringUtils.isBlank(refBean.getLivenessDataId()) || refBean.getPicPath() == null
                || refBean.getPicPath().isEmpty()) {
            return;
        }
        authFaceRecordMapper.saveRefFacePic(refBean);
    }

    /**
     * @param livenessDataId
     * @return java.util.List<java.lang.String>
     * @description 查询人脸识别记录的图片
     * @author Richard Core
     * @time 2017/2/27 10:44
     * @method findRefPicByDataId
     */
    @Override
    public List<String> findRefPicByDataId(String livenessDataId) {

        if (StringUtils.isBlank(livenessDataId)) {
            return null;
        }
        List<String> picList = authFaceRecordMapper.findRefPicByDataId(livenessDataId);
        return picList;
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
    public int getAuthFaceTimesToday(Long userId) {
        if (userId == null) {
            return 0;
        }
        List<AuthFaceRecordEntity> authList = authFaceRecordMapper.findTodayListByUserId(userId);
        if (authList == null || authList.isEmpty()) {
            return 0;
        }
        return authList.size();
    }
}
