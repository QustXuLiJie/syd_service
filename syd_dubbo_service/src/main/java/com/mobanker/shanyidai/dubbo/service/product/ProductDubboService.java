package com.mobanker.shanyidai.dubbo.service.product;

import com.mobanker.shanyidai.dubbo.dto.product.ProductDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @author hantongyang
 * @description 产品相关服务
 * @time 2016/12/30 10:43
 */
public interface ProductDubboService {

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据产品ID获取产品信息
     * @author hantongyang
     * @time 2016/12/30 10:40
     * @method getProductTemp
     */
    ResponseEntity getProductTemp(ProductDto dto);
}
