package com.mobanker.shanyidai.esb.call.http;

import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.Map;

/**
 * @author hantongyang
 * @description 用户资料相关REST接口调用封装
 * @time 2017/1/5 17:37
 */
public interface UserDataHttp {

    /**
     * @description 实名认证
     * @author hantongyang
     * @time 2017/1/5 17:41
     * @method authRealName
     * @param restUrl
     * @param params
     * @return java.lang.String
     */
    ResponseEntity authRealName(String restUrl, Map<String, String> params);

    /**
     * @description 学历认证
     * @author hantongyang
     * @time 2017/1/5 20:22
     * @method authEdu
     * @param restUrl
     * @param params
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity authEdu(String restUrl, Map<String, String> params);

    /**
     * @description 固话反查
     * @author hantongyang
     * @time 2017/1/5 20:22
     * @method authEdu
     * @param restUrl
     * @param params
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity fixedConstrast(String restUrl, Map<String, String> params);
}
