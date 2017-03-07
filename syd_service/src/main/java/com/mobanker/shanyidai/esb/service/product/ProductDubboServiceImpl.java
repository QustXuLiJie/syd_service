package com.mobanker.shanyidai.esb.service.product;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.mobanker.dto.BorrowPeriodFeeDto;
import com.mobanker.enums.BorrowType;
import com.mobanker.enums.ProductType;
import com.mobanker.shanyidai.dubbo.dto.product.ProductDto;
import com.mobanker.shanyidai.dubbo.dto.product.ProductTempDto;
import com.mobanker.shanyidai.dubbo.service.product.ProductDubboService;
import com.mobanker.shanyidai.esb.business.product.ProductBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/30 10:44
 */
public class ProductDubboServiceImpl implements ProductDubboService {

    @Resource
    private ProductBusiness productBusiness;

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据产品ID获取产品信息
     * @author hantongyang
     * @time 2016/12/30 10:40
     * @method getProductTemp
     */
    @Override
    public ResponseEntity getProductTemp(ProductDto dto) {
        if(dto == null){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "getProductTemp");
        }
        if(StringUtils.isBlank(dto.getPeriod()) || StringUtils.isBlank(dto.getChannel())
                ||StringUtils.isBlank(dto.getProductType()) || StringUtils.isBlank(dto.getMerchant())
                || StringUtils.isBlank(dto.getBorrowType()) || StringUtils.isBlank(dto.getAppVersion())){
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "getProductTemp");
        }
        BorrowPeriodFeeDto param = BeanUtil.cloneBean(dto, BorrowPeriodFeeDto.class);
        param.setBorrowType(BorrowType.MICROLOANS);
        //TODO 需要改为闪宜贷
        param.setProductType(ProductType.shanyidai);
        try{
            ProductTempDto data = productBusiness.getProductTemp(param);
            return ResponseBuilder.normalResponse(data);
        }catch (Exception e){
            return ResponseBuilder.errorResponse(ErrorConstant.SERVICE_FAIL, e, this.getClass().getSimpleName(), "getProductTemp");
        }
    }
}
