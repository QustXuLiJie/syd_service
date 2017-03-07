package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.mobanker.config.ConfigApiManager;
import com.mobanker.dto.BorrowPeriodFeeDto;
import com.mobanker.shanyidai.dubbo.dto.product.ProductTempDto;
import com.mobanker.shanyidai.esb.call.dubbo.ProductDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.mobanker.shanyidai.esb.common.utils.BeanUtil.cloneBean;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/30 11:36
 */
@Service
public class ProductDubboImpl implements ProductDubbo {

    public static final Logger logger = LogManager.getSlf4jLogger(ProductDubboImpl.class);

    @Resource
    private ConfigApiManager dubboConfigApiManager;

    /**
     * @description 根据产品模板Id查询产品模板信息
     * @author hantongyang
     * @time 2016/12/30 11:41
     * @method getProductTempById
     * @param param
     * @return com.mobanker.framework.dto.ResponseEntity
     */
    @Override
    public ResponseEntity getProductTempById(BorrowPeriodFeeDto param) {
        if(param == null){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        try {
            com.mobanker.framework.dto.ResponseEntity backResult = dubboConfigApiManager.getSingleFee(param);
            ResponseEntity result = BeanUtil.cloneBean(backResult, ResponseEntity.class);
            if(ResponseBuilder.isFinished(result)){
                ProductTempDto productDto = BeanUtil.cloneBean((BorrowPeriodFeeDto) result.getData(), ProductTempDto.class);
                //由于dubbo传值时认为是数字导致调用异常，所以强制转换成integer
                productDto.setPeriod(Integer.parseInt(((BorrowPeriodFeeDto) result.getData()).getPeriod()));
                result.setData(productDto);
                result.setError(ErrorConstant.SUCCESS.getCode());
            }else{
                logger.warn("根据产品模板Id查询产品模板信息结果失败，", result);
                result.setError(ErrorConstant.SERVICE_FAIL.getCode());
            }
            return result;
        }catch (Exception e){
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), e.getMessage());
        }
    }
}
