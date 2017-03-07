package com.mobanker.shanyidai.esb.dao.auth;

import com.mobanker.framework.dao.BaseDao;
import com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @desc: 语音识别记录
 * @author: Richard Core
 * @create time: 2017/2/16 13:43
 */
public interface AuthVoiceMapper extends BaseDao<AuthVoiceEntity> {
    /**
     * @param orderNo
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity
     * @description 根据订单号查询语音识别记录
     * @author Richard Core
     * @time 2017/2/16 14:41
     * @method findByOrderNo
     */
    AuthVoiceEntity findByOrderNo(@Param("orderNo") String orderNo);

    /**
     * @param entity
     * @return void
     * @description 更新语音识别进度记录
     * @author Richard Core
     * @time 2017/2/16 14:43
     * @method updateVoiceRecord
     */
    int updateByOrderNo(@Param("entity")AuthVoiceEntity entity);

    /**
     * @param userId
     * @return AuthVoiceRecordDto
     * @description 查询用户语音识别进度 最后一次记录
     * @author Richard Core
     * @time 2017/2/21 9:49
     * @method findVoiceAuthProcessByUserId
     */
    AuthVoiceEntity findLastByUserId(@Param("userId")Long userId);

    /**
     * @param userId
     * @return AuthVoiceEntity
     * @description 查询当天用户语音识别次数
     * @author Richard Core
     * @time 2017/2/21 9:49
     * @method findTodayListByUserId
     */
    List<AuthVoiceEntity> findTodayListByUserId(@Param("userId")Long userId);
}
