package com.mobanker.shanyidai.esb.call.http;


import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthDto;
import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.Map;

/**
 * @desc: 芝麻认证相关的http接口调用
 * @author: Richard Core
 * @create time: 2017/1/22 15:32
 */
public interface AuthZhimaHttp {
    /**
     * @param reqURL
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 芝麻认证链接
     * @author xulijie
     * @time 2017/1/22 20:23
     * @method getZhimaURL
     */
    ResponseEntity getZhimaURL(String reqURL , ZhimaAuthParamDto dto);
    /**
     * @param reqURL
     * @param dto
     * @return com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthDto
     * @description 芝麻认证是否已过期
     * @author xulijie
     * @time 2017/1/23 15:23
     * @method checkZhimaAuth
     */
    ResponseEntity checkZhimaAuth(String reqURL , ZhimaAuthParamDto dto);

    /**
     * @description 查询芝麻分
     * @author hantongyang
     * @time 2017/1/23 17:59
     * @method getZhimaScore
     * @param reqUrl
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity getZhimaScore(String reqUrl, ZhimaAuthParamDto dto);
}
