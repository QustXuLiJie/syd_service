package com.mobanker.shanyidai.esb.call.http.impl;

import com.mobanker.shanyidai.esb.call.http.UserDataHttp;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/5 17:38
 */
@Service
public class UserDataHttpImpl implements UserDataHttp {

    public static final Logger logger = LogManager.getSlf4jLogger(UserDataHttpImpl.class);

    /**
     * @description 实名认证
     * @author hantongyang
     * @time 2017/1/5 17:41
     * @method authRealName
     * @param restUrl
     * @param params
     * @return java.lang.String
     */
    @Override
    public ResponseEntity authRealName(String restUrl, Map<String, String> params) {
        return CommonUtil.doPost(restUrl, params, null, ErrorConstant.USER_REALNAME_ERROR);
    }

    /**
     * @description 学历认证
     * @author hantongyang
     * @time 2017/1/5 20:22
     * @method authEdu
     * @param restUrl
     * @param params
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity authEdu(String restUrl, Map<String, String> params) {
        return CommonUtil.doPost(restUrl, params, null, ErrorConstant.USER_EDU_ERROR);
    }

    /**
     * @description 固话反查
     * @author hantongyang
     * @time 2017/1/5 20:29
     * @method fixedConstrast
     * @param restUrl
     * @param params
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity fixedConstrast(String restUrl, Map<String, String> params) {
        return CommonUtil.doPost(restUrl, params, null, ErrorConstant.ERROR_AUTH_TEL);
    }
}
