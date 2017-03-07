package com.mobanker.shanyidai.esb.business.user;

import com.mobanker.shanyidai.esb.model.entity.user.UserSessionEntity;

import java.util.List;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/22 22:02
 */
public interface UserSessionBusiness {
    Long deleteUserSessionByCode(String code);

    Long deleteUserSessionByUserId(String userId);

    List<UserSessionEntity> getUserSessionBy(String code);

    List<UserSessionEntity> getUserSessionBy(String code, String channel);

    /**
     * 更新用户Session时间为updateTime所表示的时间.<br>
     *
     * @param primaryKey 主键标识.<br>
     * @param updateTime 当前时间毫秒数.<br>
     * @return
     */
    Long updateUserSessionTime(Long primaryKey, Long updateTime);
}
