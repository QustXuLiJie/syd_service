package com.mobanker.shanyidai.esb.dao.auth;

import com.mobanker.framework.dao.BaseDao;
import com.mobanker.shanyidai.esb.model.dto.auth.FacePicRefBean;
import com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @desc: 人脸识别记录
 * @author: Richard Core
 * @create time: 2017/2/16 13:43
 */
public interface AuthFaceRecordMapper extends BaseDao<AuthFaceRecordEntity> {
    /**
     * @param dataId
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthFaceRecordEntity
     * @description 根据订单号查询人脸识别记录
     * @author Richard Core
     * @time 2017/2/16 14:41
     * @method findByDataId
     */
    AuthFaceRecordEntity findByDataId(@Param("dataId") String dataId);

    /**
     * @param entity
     * @return void
     * @description 更新人脸识别记录
     * @author Richard Core
     * @time 2017/2/16 14:43
     * @method updateByDataId
     */
    int updateByDataId(@Param("entity") AuthFaceRecordEntity entity);

    /**
     * @param userId
     * @return AuthFaceRecordEntity
     * @description 查询用户人脸识别记录 最后一次记录
     * @author Richard Core
     * @time 2017/2/21 9:49
     * @method findLastByUserId
     */
    AuthFaceRecordEntity findLastByUserId(@Param("userId") Long userId);

    /**
     * @param refBean
     * @return int
     * @description 保存人脸识别生成图片和认证记录关联
     * @author Richard Core
     * @time 2017/2/26 10:34
     * @method saveRefFacePic
     */
    int saveRefFacePic(@Param("refBean")FacePicRefBean refBean);

    /**
     * @param livenessDataId
     * @return java.util.List<java.lang.String>
     * @description 查询人脸识别记录的图片
     * @author Richard Core
     * @time 2017/2/27 10:44
     * @method findRefPicByDataId
     */
    List<String> findRefPicByDataId(@Param("livenessDataId")String livenessDataId);
    /**
     * @param userId
     * @return AuthFaceRecordEntity
     * @description 查询当天用户人脸识别次数
     * @author Richard Core
     * @time 2017/2/21 9:49
     * @method findTodayListByUserId
     */
    List<AuthFaceRecordEntity> findTodayListByUserId(@Param("userId")Long userId);
}
