package com.mobanker.shanyidai.esb.dao.user;

import com.mobanker.framework.dao.BaseDao;
import com.mobanker.shanyidai.esb.model.entity.user.UserSessionEntity;

import java.util.List;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/22 22:12
 */
public interface UserSessionMapper extends BaseDao<UserSessionEntity> {

    Long insertSelective(UserSessionEntity record);

    Long deleteByCode(String code);

    Long deleteByUserId(String userId);

    List<UserSessionEntity> findUserSessionByCode(String code);

    List<UserSessionEntity> findUserSessionByCodeAndChannel(String code,String channel);

    Long updateUserSessionTime(Long primaryKey, Long updateTime);
}
