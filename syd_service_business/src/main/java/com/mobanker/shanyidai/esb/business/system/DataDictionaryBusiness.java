package com.mobanker.shanyidai.esb.business.system;

import com.mobanker.shanyidai.dubbo.dto.system.DataDictionaryDto;

import java.util.List;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/23 11:33
 */
public interface DataDictionaryBusiness {

    /**
     * @description 根据字典类型获取字典对应的字典项
     * @author hantongyang
     * @time 2017/1/23 11:34
     * @method getDictionaryByType
     * @param type
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    List<DataDictionaryDto> getDictionaryByType(String type);

}
