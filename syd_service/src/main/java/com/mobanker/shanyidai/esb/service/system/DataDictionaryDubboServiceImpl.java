package com.mobanker.shanyidai.esb.service.system;

import com.mobanker.shanyidai.dubbo.dto.system.DataDictionaryDto;
import com.mobanker.shanyidai.dubbo.service.system.DataDictionaryDubboService;
import com.mobanker.shanyidai.esb.business.system.DataDictionaryBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/23 13:57
 */
public class DataDictionaryDubboServiceImpl implements DataDictionaryDubboService {

    @Resource
    private DataDictionaryBusiness dataDictionaryBusiness;

    /**
     * @description 根据类型查询数据字典列表
     * @author hantongyang
     * @time 2017/1/23 13:57
     * @method getDictionaryByType
     * @param type
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity getDictionaryByType(String type) {
        try{
            List<DataDictionaryDto> data = dataDictionaryBusiness.getDictionaryByType(type);
            return ResponseBuilder.normalResponse(data);
        }catch (Exception e){
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_DATA_DICTIONARY_FAILED, e, this.getClass().getSimpleName(), "getDictionaryByType");
        }
    }

}
