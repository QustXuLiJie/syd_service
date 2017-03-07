package com.mobanker.shanyidai.esb.business.system;

import com.mobanker.shanyidai.dubbo.dto.system.DataDictionaryDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.dao.system.DataDictionaryMapper;
import com.mobanker.shanyidai.esb.model.entity.system.DataDictionaryEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.mobanker.shanyidai.esb.business.system.util.SystemConvert.initDataDictionaryParam;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/23 11:33
 */
@Service
public class DataDictionaryBusinessImpl implements DataDictionaryBusiness {
    @Resource
    private DataDictionaryMapper dataDictionaryMapper;

    /**
     * @description 根据字典类型获取字典对应的字典项
     * @author hantongyang
     * @time 2017/1/23 11:34
     * @method getDictionaryByType
     * @param type
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public List<DataDictionaryDto> getDictionaryByType(String type) {
        if(StringUtils.isBlank(type)){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        return findByParam(initDataDictionaryParam(type));
    }

    /**
     * @description 根据条件查询数据字典列表
     * @author hantongyang
     * @time 2017/1/23 13:46
     * @method findByParam
     * @param bean
     * @return java.util.List<com.mobanker.shanyidai.dubbo.dto.system.DataDictionaryDto>
     */
    private List<DataDictionaryDto> findByParam(DataDictionaryEntity bean){
        List<DataDictionaryEntity> dataDictionaryEntities = dataDictionaryMapper.get(bean);
        if(dataDictionaryEntities == null || dataDictionaryEntities.isEmpty()){
            return null;
        }
        List<DataDictionaryDto> dtoList = new ArrayList<DataDictionaryDto>();
        for (DataDictionaryEntity dataDictionaryEntity : dataDictionaryEntities) {
            if(dataDictionaryEntity == null){
                continue;
            }
            dtoList.add(BeanUtil.cloneBean(dataDictionaryEntity, DataDictionaryDto.class));
        }
        return dtoList;
    }
}
