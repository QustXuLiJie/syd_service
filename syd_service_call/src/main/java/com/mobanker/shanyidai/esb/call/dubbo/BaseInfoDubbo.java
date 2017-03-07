package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.cust.baseinfo.contract.params.BaseInfoParams;
import com.mobanker.cust.searchengine.contract.params.SearchOneByIdCardParams;
import com.mobanker.cust.searchengine.contract.params.SearchOneByPhoneParams;
import com.mobanker.framework.dto.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/23 22:46
 */
public interface BaseInfoDubbo {
    /**
     * @param params
     * @return com.mobanker.framework.dto.ResponseEntity<java.util.List<java.util.Map<java.lang.String,java.lang.String>>>
     * @description 获取用户信息
     * @author Richard Core
     * @time 2016/12/23 21:38
     * @method getOneUserInfoByPhone
     */
    public ResponseEntity<List<Map<String, String>>> getOneUserInfoByPhone(SearchOneByPhoneParams params);

    /**
     * @param params
     * @return com.mobanker.framework.dto.ResponseEntity<java.util.Map<java.lang.String,java.lang.String>>
     * @description 基础服务-创建用户
     * @author Richard Core
     * @time 2016/12/23 21:46
     * @method addBaseInfo
     */
    public ResponseEntity<Map<String, String>> addBaseInfo(BaseInfoParams params);
    /**
     * @param phone
     * @param selectField
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @description 根据手机号获取用户信息
     * @author Richard Core
     * @time 2016/12/24 12:30
     * @method getUserInfo
     */
    public List<Map<String, String>> getUserInfo(String phone, List<String> selectField);

    /**
     * @description 根据用户ID查询用户详情
     * @author hantongyang
     * @time 2016/12/26 13:35
     * @method getUserInfoByUserId
     * @param userId
     * @param selectField
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public Map<String,String> getUserInfoByUserId(Long userId, List<String> selectField);

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
    public void saveUserInfo(Long userId, Map<String, Object> fields, Map<String, Object> commonFields);

    /**
     * @param params
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 根据用户Id保存用户信息服务
     * @author Richard Core
     * @time 2016/12/27 14:54
     * @method saveUserInfo
     */
    public void saveUserInfo(BaseInfoParams params);

    /**
     * @param params
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 获取用户信息
     * @author Richard Core
     * @time 2016/12/28 21:38
     * @method getOneUserInfoByPhone
     */
    public Map<String, String> getUserInfoByPhone(SearchOneByPhoneParams params);

    /**
     * @description 根据身份证号查询用户ID
     * @author hantongyang
     * @time 2017/3/4 14:23
     * @method getUserInfoByCard
     * @param cardNo
     * @param fields
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> getUserInfoByCard(String cardNo, List<String> fields);
}
