package com.mobanker.shanyidai.esb.service.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.CreditInfoParamsDto;
import com.mobanker.shanyidai.dubbo.dto.auth.RealNameDto;
import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthParamDto;
import com.mobanker.shanyidai.dubbo.service.auth.AuthDubboService;
import com.mobanker.shanyidai.esb.business.auth.EsbAuthBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/26 20:48
 */
public class AuthDubboServiceImpl implements AuthDubboService {

    @Resource
    private EsbAuthBusiness esbAuthBusiness;


    /**
     * @param dto
     * @return void
     * @description 实名认证
     * @author hantongyang
     * @time 2016/12/26 20:48
     * @method authRealName
     */
    @Override
    public ResponseEntity authRealName(RealNameDto dto) {
        if (dto == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.USER_REALNAME_ERROR, this.getClass().getSimpleName(), "authRealName");
        }
        if (StringUtils.isBlank(dto.getRealName()) || StringUtils.isBlank(dto.getIdCard())) {
            return ResponseBuilder.errorResponse(ErrorConstant.USER_REALNAME_ERROR, this.getClass().getSimpleName(), "authRealName");
        }
        try {
            return esbAuthBusiness.authRealName(dto.getRealName(), dto.getIdCard());
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.USER_REALNAME_ERROR, e, this.getClass().getSimpleName(), "authRealName");
        }
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 学历认证
     * @author hantongyang
     * @time 2016/12/26 22:02
     * @method authEdu
     */
    @Override
    public ResponseEntity authEdu(RealNameDto dto) {
        if (dto == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.USER_EDU_ERROR, this.getClass().getSimpleName(), "authEdu");
        }
        if (StringUtils.isBlank(dto.getRealName()) || StringUtils.isBlank(dto.getIdCard())) {
            return ResponseBuilder.errorResponse(ErrorConstant.USER_EDU_ERROR, this.getClass().getSimpleName(), "authEdu");
        }
        try {
            return esbAuthBusiness.authEdu(dto.getRealName(), dto.getIdCard());
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.USER_EDU_ERROR, e, this.getClass().getSimpleName(), "authEdu");
        }
    }

    /**
     * @param tel
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 固话反查
     * @author hantongyang
     * @time 2017/1/5 20:54
     * @method fixedConstrast
     */
    @Override
    public ResponseEntity fixedConstrast(String tel) {
        if (StringUtils.isBlank(tel)) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_TEL, this.getClass().getSimpleName(), "fixedConstrast");
        }
        if (!CommonUtil.isDigit(tel)) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_TEL, this.getClass().getSimpleName(), "fixedConstrast");
        }
        try {
            return esbAuthBusiness.fixedConstrast(tel);
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_TEL, e, this.getClass().getSimpleName(), "fixedConstrast");
        }
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 获取芝麻链接
     * @author xulijie
     * @time 2017/1/22 14:23
     * @method getZhimaURL
     */
    @Override
    public ResponseEntity getZhimaURL(ZhimaAuthParamDto dto) {
        if (dto == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_ZHIMA, this.getClass().getSimpleName(), "authZhima");
        }
        if (StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getCertNo()) || StringUtils.isBlank(dto.getCode())) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_ZHIMA, this.getClass().getSimpleName(), "authZhima");
        }
        try {
            return esbAuthBusiness.authZhima(dto);
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_ZHIMAURL, e, this.getClass().getSimpleName(), "authZhima");
        }
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 获取芝麻授权
     * @author xulijie
     * @time 2017/1/23 16:23
     * @method checkZhimaAuth
     */
    @Override
    public ResponseEntity checkZhimaAuth(ZhimaAuthParamDto dto) {
        //校验参数
        if (dto == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_ZHIMA, this.getClass().getSimpleName(), "authZhima");
        }
        if (StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getCertNo()) || StringUtils.isBlank(dto.getCode())) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_ZHIMA, this.getClass().getSimpleName(), "authZhima");
        }
        try {
            return esbAuthBusiness.checkZhimaAuth(dto);
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_CHEXKZHIMAURL, e, this.getClass().getSimpleName(), "authZhima");
        }
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 查询芝麻分
     * @author hantongyang
     * @time 2017/1/23 17:51
     * @method getZhimaScore
     */
    @Override
    public ResponseEntity getZhimaScore(ZhimaAuthParamDto dto) {
        //校验参数
        if (dto == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_ZHIMA_SCORE, this.getClass().getSimpleName(), "getZhimaScore");
        }
        if (StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getCertNo())
                || StringUtils.isBlank(dto.getCode())) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_ZHIMA_SCORE, this.getClass().getSimpleName(), "getZhimaScore");
        }
        try {
            return esbAuthBusiness.getZhimaScore(dto);
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_AUTH_ZHIMA_SCORE, e, this.getClass().getSimpleName(), "getZhimaScore");
        }
    }

    /**
     * @param paramsDto
     * @return ResponseEntity
     * @description 查询芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:55
     * @method getAlipayInfoByUserId
     */
    @Override
    public ResponseEntity getAlipayInfoByUserId(CreditInfoParamsDto paramsDto) {
        try {
            Map<String, String> result = esbAuthBusiness.getAlipayInfoByUserId(paramsDto);
            return ResponseBuilder.normalResponse(result);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_GET_ZHIMA_SCORE, e, this.getClass().getSimpleName(), "getAlipayInfoByUserId");
        }
    }

    /**
     * @param paramsDto
     * @return ResponseEntity
     * @description 保存芝麻分
     * @author Richard Core
     * @time 2017/1/23 19:59
     * @method saveCreditInfoByUserId
     */
    @Override
    public ResponseEntity saveCreditInfoByUserId(CreditInfoParamsDto paramsDto) {
        try {
            Map<String, String> result = esbAuthBusiness.saveCreditInfoByUserId(paramsDto);
            return ResponseBuilder.normalResponse(result);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_SAVE_ZHIMA_SCORE, e, this.getClass().getSimpleName(), "saveCreditInfoByUserId");
        }
    }


}
