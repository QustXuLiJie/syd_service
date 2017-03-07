package com.mobanker.shanyidai.esb.business.product;

import com.mobanker.dto.BorrowPeriodFeeDto;
import com.mobanker.shanyidai.dubbo.dto.product.ProductTempDto;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/30 11:11
 */
public interface ProductBusiness {

    /**
     * @description 根据产品模板ID查询产品模板信息
     * @author hantongyang
     * @time 2016/12/30 11:27
     * @method getProductTemp
     * @param param
     * @return ProductTempDto
     */
    ProductTempDto getProductTemp(BorrowPeriodFeeDto param);
}
