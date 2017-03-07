package com.mobanker.shanyidai.esb.service.user;

import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.common.constants.ZkConfigConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.apache.commons.lang3.StringUtils;
import com.mobanker.shanyidai.dubbo.service.user.UserSessionService;
import com.mobanker.shanyidai.esb.business.user.UserSessionBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.SydConstant;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.DateUtil;
import com.mobanker.shanyidai.esb.model.entity.user.UserSessionEntity;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/22 21:56
 */
public class UserSessionServiceImpl implements UserSessionService {

    @Resource
    private UserSessionBusiness userSessionBusiness;
    @Resource
    private EsbCommonBusiness esbCommonBusiness;

    @Override
    public ResponseEntity deleteUserSessionByCode(String code) {
        if(StringUtils.isBlank(code)){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED,this.getClass().getSimpleName(),"deleteUserSessionByCode");
        }
        Long userId = userSessionBusiness.deleteUserSessionByCode(code);
        if(userId == null){
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_RESPONSE,this.getClass().getSimpleName(),"deleteUserSessionByCode");
        }
        return new ResponseEntity(SydConstant.RSP_STATUS_SUCCESS, userId);
    }

    @Override
    public ResponseEntity deleteUserSessionByUserId(String userId) {
        if(StringUtils.isBlank(userId)){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED,this.getClass().getSimpleName(),"deleteUserSessionByUserId");
        }
        Long id = userSessionBusiness.deleteUserSessionByUserId(userId);
        if(userId == null){
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_RESPONSE,this.getClass().getSimpleName(),"deleteUserSessionByUserId");
        }
        return new ResponseEntity(SydConstant.RSP_STATUS_SUCCESS, id);
    }
    @Override
    public ResponseEntity getUserSessionBy(String code) {
        // TODO Auto-generated method stub
        if(StringUtils.isBlank(code)){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED,this.getClass().getSimpleName(),"deleteUserSessionByUserId");
        }
        List<UserSessionEntity> userSessionList = userSessionBusiness.getUserSessionBy(code);
        return getUserId(userSessionList, code);
    }

    @Override
    public ResponseEntity getUserSessionBy(String code, String channel) {
        // TODO Auto-generated method stub
        if(StringUtils.isBlank(code) || StringUtils.isBlank(channel)){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED,this.getClass().getSimpleName(),"deleteUserSessionByUserId");
        }
        List<UserSessionEntity> userSessionList = userSessionBusiness.getUserSessionBy(code, channel);
        return getUserId(userSessionList, code);
    }

    /**
     * @description 判断集合是否为空，并判断用户登录是否已失效，未失效更新数据库中的用户时间，已失效删除用户登录信息
     * @author hantongyang
     * @time 2016/12/23 11:59
     * @method getUserId
     * @param list
     * @param code
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    private ResponseEntity getUserId(List<UserSessionEntity> list, String code){
        if (null == list || list.size() <= 0) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_TOKEN_INVALID,this.getClass().getSimpleName(),"getUserId");
        }

        UserSessionEntity session = list.get(0);
        Long nowTime = DateUtil.getNowTime(null);
        //TODO 获取配置系统中的超时时间
        Integer survivalTime = getSurvivalTime();
        //如果用户登录时间+存活时长，大于当前时间，表示用户还在有效期内，需要更新用户登录时间，否则删除用户登录信息
        if (DateUtil.getNowTime(session.getUpdateTime()) + survivalTime >= nowTime) {
            userSessionBusiness.updateUserSessionTime(session.getId(), nowTime);
        } else {
            userSessionBusiness.deleteUserSessionByCode(code);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_TOKEN_EXPIRE,this.getClass().getSimpleName(),"getUserId");
        }
        return new ResponseEntity(SydConstant.RSP_STATUS_SUCCESS, session.getUserId());
    }

    @Override
    public ResponseEntity updateUserSessionTime(Long primaryKey, Long updateTime) {
        Long userId = userSessionBusiness.updateUserSessionTime(primaryKey, updateTime);
        if(userId == null){
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_RESPONSE,this.getClass().getSimpleName(),"updateUserSessionTime");
        }
        return new ResponseEntity(SydConstant.RSP_STATUS_SUCCESS, userId);
    }
    /**
     * @description 获取zk配置中心保存的系统过期时间，如果配置中心没有获取到，则使用系统默认的过期时间
     * @author hantongyang
     * @time 2016/12/22 16:03
     * @method getSurvivalTime
     * @param
     * @return java.lang.Integer
     */
    private Integer getSurvivalTime(){
        //获取配置中心过期时间
        String cacheSysConfig = null;
        try {
            cacheSysConfig = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.SYSTEM_TIMEOUT.getZkValue());
        }catch (Exception e){
            throw new EsbException(ErrorConstant.CONFIG_DATA_NULL);
        }
        return Integer.valueOf(cacheSysConfig);
    }

}
