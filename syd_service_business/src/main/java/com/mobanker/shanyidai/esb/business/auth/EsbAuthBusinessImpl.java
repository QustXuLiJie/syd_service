package com.mobanker.shanyidai.esb.business.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.CreditInfoParamsDto;
import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthParamDto;
import com.mobanker.shanyidai.esb.business.bigdata.BigDataConvert;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.call.dubbo.UserCreditInfoDubbo;
import com.mobanker.shanyidai.esb.call.http.AuthZhimaHttp;
import com.mobanker.shanyidai.esb.call.http.UserDataHttp;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.ZkConfigConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/26 21:00
 */
@Service
public class EsbAuthBusinessImpl implements EsbAuthBusiness {


    @Resource
    private EsbCommonBusiness esbCommonBusiness;
    @Resource
    private UserDataHttp userDataHttp;
    @Resource
    private AuthZhimaHttp authZhimaHttp;
    @Resource
    private UserCreditInfoDubbo userCreditInfoDubbo;


    /**
     * @param realName
     * @param idCard
     * @return void
     * @description 实名认证
     * @author hantongyang
     * @time 2016/12/26 21:01
     * @method authRealName
     */
    @Override
    public ResponseEntity authRealName(String realName, String idCard) {
        if (StringUtils.isBlank(realName) || StringUtils.isBlank(idCard)) {
            throw new EsbException(ErrorConstant.USER_REALNAME_ERROR);
        }
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.USER_REALNAME_URL.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(), "实名认证服务地址获取失败");
        Map<String, String> params = new HashMap<String, String>();
        params.put("realName", realName);
        params.put("idCard", idCard);
        return userDataHttp.authRealName(reqURL, params);
    }

    /**
     * @param name
     * @param documentNo
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 认证学历
     * @author hantongyang
     * @time 2016/12/26 21:51
     * @method authEdu
     */
    @Override
    public ResponseEntity authEdu(String name, String documentNo) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(documentNo)) {
            throw new EsbException(ErrorConstant.USER_EDU_ERROR);
        }
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.USER_EDU_URL.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(), "学历认证服务地址获取失败");
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("documentNo", documentNo);
        return userDataHttp.authEdu(reqURL, params);
    }

    /**
     * @param tel
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 固话反查
     * @author hantongyang
     * @time 2017/1/5 20:46
     * @method fixedConstrast
     */
    @Override
    public ResponseEntity fixedConstrast(String tel) {
        if (StringUtils.isBlank(tel)) {
            throw new EsbException(ErrorConstant.ERROR_AUTH_TEL);
        }
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.PHONE_FIXED_CONSTRAST.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(), "固话反查服务地址获取失败");
        Map<String, String> params = new HashMap<String, String>();
        params.put("tel", tel);
        return userDataHttp.fixedConstrast(reqURL, params);
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 芝麻认证链接
     * @author xulijie
     * @time 2017/1/22 18:00
     * @method authZhima
     */
    @Override
    public ResponseEntity authZhima(ZhimaAuthParamDto dto) {
        if (StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getCertNo())) {
            throw new EsbException(ErrorConstant.ERROR_AUTH_ZHIMA);
        }
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.USER_ZHIMAAUTH_URL.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(), "芝麻认证服务地址获取失败");
        return authZhimaHttp.getZhimaURL(reqURL, dto);
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 芝麻认证授权
     * @author xulijie
     * @time 2017/1/23 15:56
     * @method checkZhimaAuth
     */
    @Override
    public ResponseEntity checkZhimaAuth(ZhimaAuthParamDto dto) {
        if (StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getCertNo())) {
            throw new EsbException(ErrorConstant.ERROR_AUTH_ZHIMA);
        }
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.USER_ZHIMACHECK_URL.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(), "芝麻认证检查用户授权服务地址获取失败");
        return authZhimaHttp.checkZhimaAuth(reqURL, dto);
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 查询芝麻分
     * @author hantongyang
     * @time 2017/1/23 17:55
     * @method getZhimaScore
     */
    @Override
    public ResponseEntity getZhimaScore(ZhimaAuthParamDto dto) {
        if (StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getCertNo())) {
            throw new EsbException(ErrorConstant.ERROR_AUTH_ZHIMA_SCORE);
        }
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.USER_ZHIMA_SCORE_URL.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(), "芝麻分查询服务地址获取失败");
        return authZhimaHttp.getZhimaScore(reqURL, dto);
    }

    /**
     * @param paramsDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 查询芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:55
     * @method getAlipayInfoByUserId
     */
    @Override
    public Map<String, String> getAlipayInfoByUserId(CreditInfoParamsDto paramsDto) {
        return userCreditInfoDubbo.getAlipayInfoByUserId(paramsDto);
    }

    /**
     * @param paramsDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 保存芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:59
     * @method saveCreditInfoByUserId
     */
    @Override
    public Map<String, String> saveCreditInfoByUserId(CreditInfoParamsDto paramsDto) {
        BigDataConvert.sendZhiMaAuthBaseInfo(paramsDto);
        return userCreditInfoDubbo.saveCreditInfoByUserId(paramsDto);
    }

}
