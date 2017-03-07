package com.mobanker.shanyidai.esb.call.http.impl;


import com.alibaba.fastjson.JSONObject;
import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthDto;
import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthParamDto;
import com.mobanker.shanyidai.esb.call.http.AuthZhimaHttp;
import com.mobanker.shanyidai.esb.call.http.convert.AuthZhimaHttpConvert;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by xulijie on 2017/1/22.
 */
@Service
public class AuthZhimaHttpImpl implements AuthZhimaHttp {

    public static final Logger logger = LoggerFactory.getLogger(AuthZhimaHttpImpl.class);

    /**
     * @param reqURL
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 芝麻认证链接
     * @author xulijie
     * @time 2017/1/22 20:23
     * @method getZhimaURL
     */
    @Override
    public ResponseEntity getZhimaURL(String reqURL, ZhimaAuthParamDto dto) {
        try {
            Map<String, String> params = AuthZhimaHttpConvert.checkAndMapZhimaAuthParams(dto);
            ResponseEntity responseEntity = CommonUtil.doPost(reqURL, params, null, ErrorConstant.ERROR_AUTH_ZHIMA);
            if (responseEntity != null && ResponseBuilder.isFinished(responseEntity) && responseEntity.getData() != null) {
                JSONObject json = (JSONObject) responseEntity.getData();
                ZhimaAuthDto responseDto = JSONObject.toJavaObject(json, ZhimaAuthDto.class);
                responseEntity.setData(responseDto);
            }
            return responseEntity;
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.ERROR_AUTH_ZHIMA, e);
        }
    }

    /**
     * @param reqURL
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 芝麻认证是否已过期
     * @author xulijie
     * @time 2017/1/23 15:23
     * @method checkZhimaAuth
     */
    @Override
    public ResponseEntity checkZhimaAuth(String reqURL, ZhimaAuthParamDto dto) {
        ResponseEntity responseEntity = null;
        try {
            Map<String, String> params = AuthZhimaHttpConvert.checkAndMapZhimaAuthParams(dto);
            responseEntity = CommonUtil.doPost(reqURL, params, null, ErrorConstant.ERROR_AUTH_ZHIMA);
            if (responseEntity != null && ResponseBuilder.isFinished(responseEntity) && responseEntity.getData() != null) {
                JSONObject json = (JSONObject) responseEntity.getData();
                ZhimaAuthDto responseDto = JSONObject.toJavaObject(json, ZhimaAuthDto.class);
                responseEntity.setData(responseDto);
            } else {
                logger.warn("======调用芝麻认证是否已过期服务异常", responseEntity);
                throw new EsbException(ErrorConstant.ERROR_AUTH_ZHIMAURL.getCode(), responseEntity.getMsg());
            }
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.ERROR_AUTH_ZHIMA, e);
        }
        return responseEntity;

    }

    /**
     * @description 查询芝麻分
     * @author hantongyang
     * @time 2017/1/23 17:59
     * @method getZhimaScore
     * @param reqUrl
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity getZhimaScore(String reqUrl, ZhimaAuthParamDto dto) {
        Map<String, String> params = AuthZhimaHttpConvert.checkAndMapZhimaAuthParams(dto);
        ResponseEntity entity = CommonUtil.doPost(reqUrl, params, null, ErrorConstant.ERROR_AUTH_ZHIMA_SCORE);
        //由于要求使用自己的DTO，所以判断返回值并重新封装
        if (entity != null && ResponseBuilder.isSuccess(entity)) {
            JSONObject json = (JSONObject) entity.getData();
            entity.setData(json.get("score"));
        }
        return entity;
    }
}
