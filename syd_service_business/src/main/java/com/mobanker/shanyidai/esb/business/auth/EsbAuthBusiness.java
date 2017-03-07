package com.mobanker.shanyidai.esb.business.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.CreditInfoParamsDto;
import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.Map;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/26 21:00
 */
public interface EsbAuthBusiness {

    /**
     * @description 实名认证
     * @author hantongyang
     * @time 2016/12/26 21:01
     * @method authRealName
     * @param realName
     * @param idCard
     * @return void
     */
    ResponseEntity authRealName(String realName, String idCard);

    /**
     * @description 认证学历
     * @author hantongyang
     * @time 2016/12/26 21:51
     * @method authEdu
     * @param name
     * @param documentNo
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity authEdu(String name, String documentNo);

    /**
     * @description 固话反查
     * @author hantongyang
     * @time 2017/1/5 20:46
     * @method fixedConstrast
     * @param tel
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity fixedConstrast(String tel);
    /**
     * @description 芝麻认证链接
     * @author xulijie
     * @time 2017/1/22 17:13
     * @method authZhima
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity authZhima(ZhimaAuthParamDto dto);
    /**
     * @description 芝麻认证授权
     * @author xulijie
     * @time 2017/1/23 15:56
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
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 查询芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:55
     * @method getAlipayInfoByUserId
     */
    public Map<String, String> getAlipayInfoByUserId(CreditInfoParamsDto paramsDto);

    /**
     * @param paramsDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 保存芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:59
     * @method saveCreditInfoByUserId
     */
    public Map<String, String> saveCreditInfoByUserId(CreditInfoParamsDto paramsDto);

}
