package com.mobanker.shanyidai.dubbo.service.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.CreditInfoParamsDto;
import com.mobanker.shanyidai.dubbo.dto.auth.RealNameDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthDto;
import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthParamDto;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/26 20:45
 */
public interface AuthDubboService {

    /**
     * @description 实名认证
     * @author hantongyang
     * @time 2016/12/26 20:48
     * @method authRealName
     * @param dto
     * @return void
     */
    ResponseEntity authRealName(RealNameDto dto);

    /**
     * @description 学历认证
     * @author hantongyang
     * @time 2016/12/26 22:02
     * @method authEdu
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity authEdu(RealNameDto dto);

    ResponseEntity fixedConstrast(String tel);
    /**
     * @description 获取芝麻链接
     * @author xulijie
     * @time 2017/1/22 14:23
     * @method getZhimaURL
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity getZhimaURL(ZhimaAuthParamDto dto);
    /**
     * @description 获取芝麻链接
     * @author xulijie
     * @time 2017/1/23 16:23
     * @method checkZhimaAuth
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity checkZhimaAuth(ZhimaAuthParamDto dto);

    /**
     * @description 查询芝麻分
     * @author hantongyang
     * @time 2017/1/23 17:51
     * @method getZhimaScore
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity getZhimaScore(ZhimaAuthParamDto dto);
    /**
     * @param paramsDto
     * @return ResponseEntity
     * @description 查询芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:55
     * @method getAlipayInfoByUserId
     */
    public ResponseEntity getAlipayInfoByUserId(CreditInfoParamsDto paramsDto);

    /**
     * @param paramsDto
     * @return ResponseEntity
     * @description 保存芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:59
     * @method saveCreditInfoByUserId
     */
    public ResponseEntity saveCreditInfoByUserId(CreditInfoParamsDto paramsDto);


}
