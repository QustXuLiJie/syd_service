package com.mobanker.shanyidai.esb.business.version;

import com.mobanker.shanyidai.dubbo.dto.version.VersionParamDto;
import com.mobanker.shanyidai.dubbo.dto.version.VersionResultDto;

/**
 * @description: app版本认证相关
 * @author: xulijie
 * @create time: 10:46 2017/2/24
 */
public interface VersionBusiness {

    /**
     * @param versionParamDto
     * @return com.mobanker.shanyidai.dubbo.dto.version.VersionResultDto
     * @author xulijie
     * @method getVersion
     * @description 查询app版本更新
     * @time 14:24 2017/2/24
     */
    VersionResultDto getVersion(VersionParamDto versionParamDto);
}
