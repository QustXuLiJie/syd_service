package com.mobanker.shanyidai.esb.business.user;

import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.dao.user.UserSessionMapper;
import com.mobanker.shanyidai.esb.model.entity.user.UserSessionEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/22 22:03
 */
@Component("userSessionBusiness")
public class UserSessionBusinessImpl implements UserSessionBusiness {

    @Autowired
    private UserSessionMapper userSessionMapper;

    @Override
    public Long deleteUserSessionByCode(String code) {
        if(StringUtils.isBlank(code)){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        return userSessionMapper.deleteByCode(code);
    }

    @Override
    public Long deleteUserSessionByUserId(String userId) {
        if(StringUtils.isBlank(userId)){
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        return userSessionMapper.deleteByUserId(userId);
    }
    @Override
    public List<UserSessionEntity> getUserSessionBy(String code) {
        if(StringUtils.isBlank(code)){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        return userSessionMapper.findUserSessionByCode(code);
    }

    @Override
    public List<UserSessionEntity> getUserSessionBy(String code, String channel) {
        if(StringUtils.isBlank(code) || StringUtils.isBlank(channel)){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        return userSessionMapper.findUserSessionByCodeAndChannel(code, channel);
    }

    @Override
    public Long updateUserSessionTime(Long primaryKey, Long updateTime) {
        if(primaryKey == null){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        return userSessionMapper.updateUserSessionTime(primaryKey, updateTime);
    }
}
