package com.mobanker.shanyidai.esb.business.product;

import com.mobanker.dto.BorrowPeriodFeeDto;
import com.mobanker.shanyidai.dubbo.dto.product.ProductTempDto;
import com.mobanker.shanyidai.esb.call.dubbo.ProductDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/30 11:11
 */
@Service
public class ProductBusinessImpl implements ProductBusiness {

    @Resource
    private ProductDubbo productDubbo;

    /**
     * @description 根据产品模板ID查询产品模板信息
     * @author hantongyang
     * @time 2016/12/30 11:27
     * @method getProductTemp
     * @param param
     * @return java.lang.String
     */
    @Override
    public ProductTempDto getProductTemp(BorrowPeriodFeeDto param) {
        if(param == null){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        ResponseEntity result = productDubbo.getProductTempById(param);
        if(!ResponseBuilder.isFinished(result)){
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        return (ProductTempDto)result.getData();
    }
}
