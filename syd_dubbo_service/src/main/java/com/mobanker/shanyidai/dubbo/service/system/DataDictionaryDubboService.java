package com.mobanker.shanyidai.dubbo.service.system;

import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/23 13:55
 */
public interface DataDictionaryDubboService {

    /**
     * @description 根据类型查询数据字典列表
     * @author hantongyang
     * @time 2017/1/23 13:57
     * @method getDictionaryByType
     * @param type
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity getDictionaryByType(String type);

}
