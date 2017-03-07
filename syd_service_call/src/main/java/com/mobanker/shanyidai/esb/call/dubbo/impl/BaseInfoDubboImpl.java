package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.mobanker.cust.baseinfo.contract.manager.DubboBaseInfoManager;
import com.mobanker.cust.baseinfo.contract.params.BaseInfoParams;
import com.mobanker.cust.searchengine.contract.manager.DubboSearchByIdCardManager;
import com.mobanker.cust.searchengine.contract.manager.DubboSearchByPhoneManager;
import com.mobanker.cust.searchengine.contract.params.SearchOneByIdCardParams;
import com.mobanker.cust.searchengine.contract.params.SearchOneByPhoneParams;
import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.shanyidai.esb.call.dubbo.BaseInfoDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Richard Core
 * @description 用户基础服务
 * @date 2016/12/23 21:29
 */
@Service
public class BaseInfoDubboImpl implements BaseInfoDubbo {
    public static final Logger logger = LogManager.getSlf4jLogger(BaseInfoDubboImpl.class);
    @Resource
    private DubboSearchByPhoneManager dubboSearchByPhoneManager;
    @Resource
    private DubboBaseInfoManager dubboBaseInfoManager;
    @Resource
    private DubboSearchByIdCardManager dubboSearchByIdCardManager;

    /**
     * @param params
     * @return com.mobanker.framework.dto.ResponseEntity<java.util.List<java.util.Map<java.lang.String,java.lang.String>>>
     * @description 获取用户信息
     * @author Richard Core
     * @time 2016/12/23 21:38
     * @method getOneUserInfoByPhone
     */
    public ResponseEntity<List<Map<String, String>>> getOneUserInfoByPhone(SearchOneByPhoneParams params) {
        ResponseEntity<List<Map<String, String>>> response = null;
        try {
            response = dubboSearchByPhoneManager.getOneUserInfoByPhone(params);
        } catch (Exception e) {
            logger.error("调用用户基础服务-根据手机号查询用户信息方法异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (!CallResultUtil.isSuccess(response)) {
            logger.warn("调用用户基础服务-获取用户信息方法异常", response);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), response.getMsg());
        }
        return response;
    }


    /**
     * @param params
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 获取用户信息
     * @author Richard Core
     * @time 2016/12/28 21:38
     * @method getOneUserInfoByPhone
     */
    @Override
    public Map<String, String> getUserInfoByPhone(SearchOneByPhoneParams params) {
        ResponseEntity<List<Map<String, String>>> response = null;
        try {
            response = dubboSearchByPhoneManager.getOneUserInfoByPhone(params);
        } catch (Exception e) {
            logger.error("调用用户基础服务-根据手机号查询用户信息方法异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (!CallResultUtil.isSuccess(response)) {
            logger.warn("调用用户基础服务-根据手机号查询用户信息方法异常", response);
            throw new EsbException(ErrorConstant.GET_USER_INFO_ERROR.getCode(), response.getMsg());
        }
        List<Map<String, String>> data = response.getData();
        if (data == null || data.isEmpty()) {
            logger.warn("调用用户基础服务-根据手机号没有查到用户信息", response);
            throw new EsbException(ErrorConstant.PHONE_UNREGIST);
        }
        return data.get(0);
    }

    /**
     * @param params
     * @return com.mobanker.framework.dto.ResponseEntity<java.util.Map<java.lang.String,java.lang.String>>
     * @description 基础服务-创建用户
     * @author Richard Core
     * @time 2016/12/23 21:46
     * @method addBaseInfo
     */
    public ResponseEntity<Map<String, String>> addBaseInfo(BaseInfoParams params) {
        ResponseEntity<Map<String, String>> response = null;
        try {
            response = dubboBaseInfoManager.addBaseInfo(params);
        } catch (Exception e) {
            logger.error("调用用户基础服务-保存用户信息方法异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (!CallResultUtil.isSuccess(response)) {
            logger.warn("调用用户基础服务-创建用户方法异常", response);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), response.getMsg());
        }
        return response;
    }

    /**
     * @param phone
     * @param selectField
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @description 根据手机号获取用户信息
     * @author Richard Core
     * @time 2016/12/24 12:30
     * @method getUserInfo
     */
    @Override
    public List<Map<String, String>> getUserInfo(String phone, List<String> selectField) {
        SearchOneByPhoneParams params = new SearchOneByPhoneParams();
        params.setPhone(phone);
        params.setFields(selectField);
        ResponseEntity<List<Map<String, String>>> result = null;
        try {
            result = dubboSearchByPhoneManager.getOneUserInfoByPhone(params);
        } catch (Exception e) {
            logger.error("调用用户基础服务-根据手机号查询用户信息方法异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (!CallResultUtil.isSuccess(result)) {
            logger.warn("调用用户基础服务-根据手机号查询用户信息方法异常", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        return result.getData();
    }


    /**
     * @param userId
     * @param selectField
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @description 根据用户ID查询用户详情
     * @author hantongyang
     * @time 2016/12/26 13:35
     * @method getUserInfoByUserId
     */
    @Override
    public Map<String, String> getUserInfoByUserId(Long userId, List<String> selectField) {
        if (userId == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        if (selectField == null || selectField.isEmpty()) {
            selectField.add("userStatus");
            selectField.add("phone");
        }
        BaseInfoParams param = new BaseInfoParams();
        param.setUserId(userId);
        param.setFields(selectField);
        ResponseEntity<Map<String, String>> result = dubboBaseInfoManager.getBaseInfoByUserId(param);
        if (!CallResultUtil.isSuccess(result)) {
            logger.warn("调用用户基础服务-根据用户ID查询用户基本信息方法异常", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        return result.getData();
    }

    /**
     * @param userId
     * @param fields
     * @param commonFields
     * @return com.mobanker.framework.dto.ResponseEntity
     * @description 更新用户信息
     * @author R.Core
     * @time 2016/12/26 22:40
     * @method saveUserInfo
     */
    @Override
    public void saveUserInfo(Long userId, Map<String, Object> fields, Map<String, Object> commonFields) {
        if (userId == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        BaseInfoParams params = new BaseInfoParams();
        params.setUserId(userId);
        params.setSaveFields(fields);
        params.setCommonFields(commonFields);
        saveUserInfo(params);
    }

    /**
     * @param params
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 根据用户Id保存用户信息服务
     * @author Richard Core
     * @time 2016/12/27 14:54
     * @method saveUserInfo
     */
    @Override
    public void saveUserInfo(BaseInfoParams params) {
        ResponseEntity<Map<String, String>> result = null;
        try {
            result = dubboBaseInfoManager.saveBaseInfoByUserId(params);
        } catch (Exception e) {
            logger.error("调用用户基础服务-根据用户Id保存用户信息服务异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (!CallResultUtil.isSuccess(result)) {
            logger.warn("调用用户基础服务-根据用户Id保存用户信息服务异常", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
    }

    /**
     * @description 根据身份证号查询用户ID
     * @author hantongyang
     * @time 2017/3/4 14:23
     * @method getUserInfoByCard
     * @param cardNo
     * @param fields
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    public Map<String, String> getUserInfoByCard(String cardNo, List<String> fields) {
        //1、验证参数
        if(StringUtils.isBlank(cardNo) || fields == null || fields.isEmpty()){
            logger.error("调用用户基础服务-根据身份证号查询用户信息服务异常", ErrorConstant.PARAM_VALID.getDesc());
            throw new EsbException(ErrorConstant.PARAM_VALID);
        }
        //2、封装查询参数
        SearchOneByIdCardParams params = new SearchOneByIdCardParams();
        params.setIdCard(cardNo);
        params.setFields(fields);
        //3、查询
        ResponseEntity<List<Map<String, String>>> result = null;
        try {
            result = dubboSearchByIdCardManager.getOneUserInfoByIdCard(params);
        } catch (Exception e) {
            logger.error("调用用户基础服务-根据身份证号查询用户信息服务异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        //4、验证是否成功
        if (!CallResultUtil.isSuccess(result)) {
            logger.warn("调用用户基础服务-根据身份证号查询用户信息服务异常", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        //5、转换参数
        List<Map<String, String>> list = result.getData();
        if(list == null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
