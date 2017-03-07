package com.mobanker.shanyidai.dubbo.service.version;

import com.mobanker.shanyidai.dubbo.dto.version.VersionParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @description: app版本更新业务
 * @author: xulijie
 * @create time: 9:41 2017/2/24
 */
public interface VersionDubboService {

    /**
     * @param versionParamDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getVersion
     * @description app版本更新相关
     * @time 11:01 2017/2/24
     */
    public ResponseEntity getVersion(VersionParamDto versionParamDto);
}
