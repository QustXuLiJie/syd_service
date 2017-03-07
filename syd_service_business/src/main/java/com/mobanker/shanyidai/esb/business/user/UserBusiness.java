package com.mobanker.shanyidai.esb.business.user;


import com.mobanker.cust.baseinfo.contract.params.BaseInfoParams;
import com.mobanker.shanyidai.esb.model.dto.user.UserLoginBean;

import java.util.List;
import java.util.Map;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/22 15:51
 */
public interface UserBusiness {
    /**
     * @param phone
     * @return boolean
     * @description 通过手机号查询用户是否存在
     * @author Richard Core
     * @time 2016/12/22 15:52
     * @method checkUserInfoByPhone
     */
    void checkUserInfoByPhone(String phone);


    /**
     * @param params
     * @return int
     * @description 创建用户
     * @author Richard Core
     * @time 2016/12/22 18:26
     * @method createUserLogin
     */
    Map<String, String> createUser(BaseInfoParams params);

    /**
     * @param phone
     * @param fields
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 根据手机号查询用户信息
     * @author Richard Core
     * @time 2016/12/28 11:50
     * @method getUserInfoByPhone
     */
    public Map<String, String> getUserInfoByPhone(String phone, List<String> fields);

    /**
     * 通过用户手机号查找并返回用户记录.<br>
     *
     * @param phone       手机号.<br>
     * @param selectField 自定义查询域.
     */
    List<Map<String, String>> getUserInfo(String phone, List<String> selectField);

    /**
     * @param userId
     * @param selectField
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 根据用户ID获取用户信息
     * @author hantongyang
     * @time 2016/12/26 13:39
     * @method getUserInfo
     */
    Map<String, String> getUserInfo(Long userId, List<String> selectField);

    /**
     * @param userId
     * @param selectField
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 根据用户ID获取用户信息
     * @author Richard Core
     * @time 2016/12/27 17:19
     * @method getUserInfo
     */
    Map<String, String> getUserInfo(Long userId, String... selectField);

    /**
     * @param dto
     * @return java.lang.String
     * @description 用户登录验证
     * @author Richard Core
     * @time 2016/12/24 14:01
     * @method Map<String, String>
     */
    Map<String, String> userLogin(UserLoginBean dto);

    /**
     * @param params
     * @return void
     * @description 根据userId更新用户信息
     * @author Richard Core
     * @time 2016/12/27 16:38
     * @method saveUserInfo
     */
    void updateUserInfo(BaseInfoParams params);

    /**
     * @param userId
     * @param saveFields
     * @param commonFields
     * @return void
     * @description 根据userId更新用户信息
     * @author Richard Core
     * @time 2016/12/27 16:46
     * @method saveUserInfo
     */
    void updateUserInfo(Long userId, Map<String, Object> saveFields, Map<String, Object> commonFields);

    /**
     * @description 根据身份证号查询用户信息
     * @author hantongyang
     * @time 2017/3/4 14:33
     * @method getUserByCardNo
     * @param cardNo
     * @param fields
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> getUserByCardNo(String cardNo, List<String> fields);
}
