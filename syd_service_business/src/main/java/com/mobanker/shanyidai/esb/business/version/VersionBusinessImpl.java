package com.mobanker.shanyidai.esb.business.version;


import com.mobanker.shanyidai.dubbo.dto.version.VersionParamDto;
import com.mobanker.shanyidai.dubbo.dto.version.VersionResultDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.dao.version.GetVersionMapper;
import com.mobanker.shanyidai.esb.model.entity.version.VersionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: xulijie
 * @create time: 10:47 2017/2/24
 */
@Service
public class VersionBusinessImpl implements VersionBusiness {
    public static final String updateTure = "1";
    public static final String updateFail = "0";
    @Resource
    private GetVersionMapper getVersionMapper;

    /**
     * @param versionParamDto
     * @return com.mobanker.shanyidai.dubbo.dto.version.VersionResultDto
     * @author xulijie
     * @method getVersion
     * @description 查询app版本更新
     * @time 14:24 2017/2/24
     */
    @Override
    public VersionResultDto getVersion(VersionParamDto versionParamDto) {
        //参数验证
        if (StringUtils.isBlank(versionParamDto.getProduct()) || StringUtils.isBlank(versionParamDto.getType())) {
            throw new EsbException(ErrorConstant.ERROR_GET_APPVERSION);
        }
        //拼装查询参数实体
        VersionEntity entity = new VersionEntity();
        entity.setType(versionParamDto.getType());
        entity.setProduct(versionParamDto.getProduct());
        entity.setStatus(VersionParamDto.status);
        entity.setUpdateForce(VersionParamDto.updateForce);
        //查询数据库
        VersionEntity entityResult = getVersionMapper.findByProductAndType(entity);
        VersionResultDto versionResultDto = BeanUtil.cloneBean(entityResult, VersionResultDto.class);
        return versionResultDto;
    }
}
