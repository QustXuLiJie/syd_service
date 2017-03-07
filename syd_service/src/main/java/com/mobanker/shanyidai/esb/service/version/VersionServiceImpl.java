package com.mobanker.shanyidai.esb.service.version;

import com.mobanker.shanyidai.dubbo.dto.version.VersionParamDto;
import com.mobanker.shanyidai.dubbo.dto.version.VersionResultDto;
import com.mobanker.shanyidai.dubbo.service.version.VersionDubboService;
import com.mobanker.shanyidai.esb.business.version.VersionBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @description: app版本更新
 * @author: xulijie
 * @create time: 9:45 2017/2/24
 */
public class VersionServiceImpl implements VersionDubboService {

    @Resource
    private VersionBusiness versionBusiness;

    /**
     * @param versionParamDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getVersion
     * @description app版本更新相关
     * @time 11:01 2017/2/24
     */
    @Override
    public ResponseEntity getVersion(VersionParamDto versionParamDto) {
        //参数验证
        if (versionParamDto == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_GET_APPVERSION, this.getClass().getSimpleName(), "getVersion");
        }
        if (StringUtils.isBlank(versionParamDto.getType())
                || StringUtils.isBlank(versionParamDto.getProduct())) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_GET_APPVERSION, this.getClass().getSimpleName(), "getVersion");
        }
        try {
            VersionResultDto resultDto = versionBusiness.getVersion(versionParamDto);
            return ResponseBuilder.normalResponse(resultDto);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_GET_APPVERSION, e, this.getClass().getSimpleName(), "findVoiceAuthProcessByUserId");
        }
    }
}
