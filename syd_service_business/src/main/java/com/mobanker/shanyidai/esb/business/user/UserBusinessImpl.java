package com.mobanker.shanyidai.esb.business.user;

import com.mobanker.cust.baseinfo.contract.params.BaseInfoParams;
import com.mobanker.cust.searchengine.contract.params.SearchOneByPhoneParams;
import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.shanyidai.esb.business.bigdata.BigDataConvert;
import com.mobanker.shanyidai.esb.call.dubbo.BaseInfoDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.LoginEnum;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.model.dto.user.UserLoginBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/22 15:51
 */
@Service
public class UserBusinessImpl implements UserBusiness {
    public static final Logger logger = LoggerFactory.getLogger(UserBusinessImpl.class);
    public final static String loginType = "normal";

    @Resource
    private BaseInfoDubbo baseInfoDubbo;


    /**
     * @param phone
     * @return boolean
     * @description 通过手机号查询用户是否存在
     * @author Richard Core
     * @time 2016/12/22 15:52
     * @method checkUserInfoByPhone
     */
    @Override
    public void checkUserInfoByPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new EsbException(ErrorConstant.PARAMS_PHONE);
        }
        //查询基础服务 根据手机号查询用户信息
        SearchOneByPhoneParams params = new SearchOneByPhoneParams();
        params.setPhone(phone);
        params.setFields(Arrays.asList("idCard", "phone", "userStatus"));
        ResponseEntity<List<Map<String, String>>> result = baseInfoDubbo.getOneUserInfoByPhone(params);
        //查询基础服务失败
        if (result == null) {
            throw new EsbException(ErrorConstant.SERVICE_VALID);
        }
        //手机号已经注册过
        if (ResponseBuilder.RESPONSE_OK.equals(result.getStatus()) &&
                ErrorConstant.SUCCESS.getCode().equals(result.getError()) &&
                result.getData() != null) {
            throw new EsbException(ErrorConstant.ERROR_EXIST_PHONE);
        }
    }

    /**
     * @param phone
     * @param fields
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 根据手机号查询用户信息
     * @author Richard Core
     * @time 2016/12/28 11:50
     * @method getUserInfoByPhone
     */
    @Override
    public Map<String, String> getUserInfoByPhone(String phone, List<String> fields) {
        SearchOneByPhoneParams params = new SearchOneByPhoneParams();
        params.setPhone(phone);
        params.setFields(fields);

        Map<String, String> userInfoByPhone = baseInfoDubbo.getUserInfoByPhone(params);
        return userInfoByPhone;
    }
    /**
     * @param params
     * @return int
     * @description 创建用户
     * @author Richard Core
     * @time 2016/12/22 18:26
     * @method createUserLogin
     */
    @Override
    public Map<String, String> createUser(BaseInfoParams params) {
        //调用基础服务创建用户
        ResponseEntity<Map<String, String>> res = baseInfoDubbo.addBaseInfo(params);
        //验证创建结果
        if (!CallResultUtil.isSuccess(res)) {
            throw new EsbException(ErrorConstant.REGISTER_ERROR);
        }
        return res.getData();
    }


    /**
     * @param phone
     * @param selectField
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @description 根据手机号获取用户信息
     * @author Richard Core
     * @time 2016/12/24 12:16
     * @method getUserInfo
     */
    @Override
    public List<Map<String, String>> getUserInfo(String phone, List<String> selectField) {
        List<Map<String, String>> userInfo = baseInfoDubbo.getUserInfo(phone, selectField);
        return userInfo;
    }

    /**
     * @param userId
     * @param selectField
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @description 根据用户ID获取用户信息
     * @author hantongyang
     * @time 2016/12/26 13:39
     * @method getUserInfo
     */
    @Override
    public Map<String, String> getUserInfo(Long userId, List<String> selectField) {
        Map<String, String> userInfo = baseInfoDubbo.getUserInfoByUserId(userId, selectField);
        return userInfo;
    }

    /**
     * @param userId
     * @param selectField
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 根据用户ID获取用户信息
     * @author Richard Core
     * @time 2016/12/27 17:19
     * @method getUserInfo
     */
    @Override
    public Map<String, String> getUserInfo(Long userId, String... selectField) {
        Map<String, String> userInfo = getUserInfo(userId, Arrays.asList(selectField));
        return userInfo;
    }

    /**
     * @param dto
     * @return java.lang.String
     * @description 用户登录验证
     * @author Richard Core
     * @time 2016/12/24 14:01
     * @method Map<String, String>
     */
    @Override
    public Map<String, String> userLogin(UserLoginBean dto) {
        //获取用户信息
        List<String> selectField = Arrays.asList("password", "phone", "realname");
        List<Map<String, String>> userInfoList = getUserInfo(dto.getPhone(), selectField);
        dto.setLoginType(loginType);
        //验证返回参数
        if (userInfoList == null || userInfoList.isEmpty()) {
            BigDataConvert.sendUserLogin(dto);
            throw new EsbException(ErrorConstant.PHONE_PASSWORD_INVALID);
        }
        Map<String, String> user = userInfoList.get(0);
        String userId = user.get("userId");
        String password = user.get("password");
//        String realname = user.get("realname");
        String phone = user.get("phone");
        if (StringUtils.isBlank(userId) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(phone)) {
            logger.error("账号不存在");
            BigDataConvert.sendUserLogin(dto);
            throw new EsbException(ErrorConstant.PHONE_PASSWORD_INVALID);
        }
        if (!dto.getPhone().equals(phone)) {
            logger.error("查询返回账号错误");
            BigDataConvert.sendUserLogin(dto);
            throw new EsbException(ErrorConstant.PHONE_PASSWORD_INVALID);
        }
        if (!dto.getPassword().equals(password)) {
            logger.error("密码错误");
            BigDataConvert.sendUserLogin(dto);
            throw new EsbException(ErrorConstant.PHONE_PASSWORD_INVALID);
        }
        dto.setUserId(userId);
        dto.setLoginResult(LoginEnum.LOGIN_SUC.getStatus());
        BigDataConvert.sendUserLogin(dto);//用户登录信息, 即登录行为记录
        BigDataConvert.sendUserSignin(dto);//用户登入信息,即登录成功流水
        return user;
    }

    /**
     * @param params
     * @return void
     * @description 根据userId更新用户信息
     * @author Richard Core
     * @time 2016/12/27 16:38
     * @method updateUserInfo
     */
    @Override
    public void updateUserInfo(BaseInfoParams params) {
        baseInfoDubbo.saveUserInfo(params);
    }

    /**
     * @param userId
     * @param saveFields
     * @param commonFields
     * @return void
     * @description 根据userId更新用户信息
     * @author Richard Core
     * @time 2016/12/27 16:46
     * @method updateUserInfo
     */
    @Override
    public void updateUserInfo(Long userId, Map<String, Object> saveFields, Map<String, Object> commonFields) {
        baseInfoDubbo.saveUserInfo(userId, saveFields, commonFields);
    }

    /**
     * @description 根据身份证号查询用户信息
     * @author hantongyang
     * @time 2017/3/4 14:33
     * @method getUserByCardNo
     * @param cardNo
     * @param fields
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    public Map<String, String> getUserByCardNo(String cardNo, List<String> fields) {
        return baseInfoDubbo.getUserInfoByCard(cardNo, fields);
    }
}
