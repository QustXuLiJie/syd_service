package com.mobanker.shanyidai.esb.business.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto;
import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthUploadParamDto;
import com.mobanker.shanyidai.dubbo.dto.auth.WatermarkVerifyParamDto;
import com.mobanker.shanyidai.esb.model.dto.auth.FacePicRefBean;
import com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/21 11:59
 */
public interface FaceAuthBusiness {
    /**
     * @param paramsDto
     * @param file
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 人脸识别上传服务
     * @author Richard Core
     * @time 2017/2/21 15:25
     * @method faceAuthUpload
     */
    public FaceAuthResultDto faceAuthUpload(FaceAuthUploadParamDto paramsDto, MultipartFile file);

    /**
     * @param livenessDataId
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 活体识别-活体防伪
     * @author Richard Core
     * @time 2017/2/22 14:19
     * @method faceAuthHackDetect
     */
    public FaceAuthResultDto faceAuthHackDetect(String livenessDataId);

    /**
     * @param paramDto
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 活体数据与水印照片比对
     * @author Richard Core
     * @time 2017/2/22 17:21
     * @method faceAuthWatermarkVerify
     */
    public FaceAuthResultDto faceAuthWatermarkVerify(WatermarkVerifyParamDto paramDto);

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity
     * @description 根据userId查询用户最后一次人脸识别进度
     * @author Richard Core
     * @time 2017/2/23 15:32
     * @method findByUserId
     */
    public AuthFaceRecordEntity findByUserId(Long userId);
    /**
     * @param dataId
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity
     * @description 根据dataId查询用户人脸识别进度
     * @author Richard Core
     * @time 2017/2/23 15:32
     * @method findByDataId
     */
    public AuthFaceRecordEntity findByDataId(String dataId);
    /**
     * @param entity
     * @return void
     * @description 更新人脸识别进度信息
     * @author Richard Core
     * @time 2017/2/23 15:43
     * @method updateByDataId
     */
    public void updateByDataId(AuthFaceRecordEntity entity);
    /**
     * @param entity
     * @return void
     * @description 保存人脸识别进度信息
     * @author Richard Core
     * @time 2017/2/23 15:43
     * @method saveAuthFaceRecord
     */
    void saveAuthFaceRecord(AuthFaceRecordEntity entity);

    /**
     * @param refBean
     * @return void
     * @description 保存人脸识别生成图片和认证记录关联
     * @author Richard Core
     * @time 2017/2/26 10:34
     * @method saveRefFacePic
     */
    void saveRefFacePic(FacePicRefBean refBean);

    /**
     * @param livenessDataId
     * @return java.util.List<java.lang.String>
     * @description 查询人脸识别记录的图片
     * @author Richard Core
     * @time 2017/2/27 10:44
     * @method findRefPicByDataId
     */
    List<String> findRefPicByDataId(String livenessDataId);

    /**
     * @param userId
     * @return int
     * @description 获取用户当天人脸验证次数
     * @author Richard Core
     * @time 2017/3/4 15:47
     * @method getAuthFaceTimesToday
     */
    public int getAuthFaceTimesToday(Long userId);
}
