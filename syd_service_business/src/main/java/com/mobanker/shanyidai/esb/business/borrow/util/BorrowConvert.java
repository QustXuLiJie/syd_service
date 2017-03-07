package com.mobanker.shanyidai.esb.business.borrow.util;

import com.mobanker.enums.BorrowType;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowOrderParamDto;
import com.mobanker.shanyidai.esb.common.constants.*;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.common.utils.DateUtil;
import com.mobanker.shanyidai.esb.model.dto.rabbitmq.BorrowProducerMessage;
import com.mobanker.shanyidai.esb.model.dto.rabbitmq.OrderConsumerMessage;
import com.mobanker.shanyidai.esb.model.entity.borrow.BorrowEntity;
import com.mobanker.shanyidai.esb.model.entity.rabbitmq.MqConsumerOrderLogEntity;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/17 15:25
 */
public class BorrowConvert {

    public static void checkBorrowOrderParam(BorrowOrderParamDto dto){
        if(dto == null || dto.getUserId() == null){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        //银行卡、产品模板ID、订单ID
        if(StringUtils.isBlank(dto.getBankCard())){
            throw new EsbException(ErrorConstant.BANK_CARD_NO_NULL);
        }
//        if(StringUtils.isBlank(dto.getBorrowNid())){
//            throw new EsbException(ErrorConstant.ERROR_BORROW_NID_NULL);
//        }
        if(StringUtils.isBlank(dto.getProductId())){
            throw new EsbException(ErrorConstant.ERROR_PRODUCT_ID_NULL);
        }
        //借款金额
        if(dto.getBorrowAmount() == null || dto.getBorrowAmount().doubleValue() < 0){
            throw new EsbException(ErrorConstant.ERROR_BORROW_DAYS);
        }
        //借款天数
        if(dto.getBorrowDays() == null || dto.getBorrowDays().intValue() <= 0){
            throw new EsbException(ErrorConstant.ERROR_BORROW_DAYS);
        }
        //借款时间
        if(dto.getBorrowTime() == null){
            dto.setBorrowTime(new Date());
        }
    }

    /**
     * @description 初始化订单MQ实体
     * @author hantongyang
     * @time 2017/1/17 16:05
     * @method initBorrowProducerMessage
     * @param dto
     * @return com.mobanker.shanyidai.esb.model.dto.rabbitmq.BorrowMessage
     */
    public static BorrowProducerMessage initBorrowProducerMessage(BorrowOrderParamDto dto){
        BorrowProducerMessage mqBean = new BorrowProducerMessage();
        mqBean.setUserId(dto.getUserId().toString());
        mqBean.setAmount(dto.getBorrowAmount().toString()); //金额
        mqBean.setOrderNo(dto.getBorrowId().toString()); //订单号
        mqBean.setPeriodDays(dto.getBorrowDays().toString()); //每期天数
        mqBean.setProductModelId(dto.getProductId()); //产品模板ID
        mqBean.setBorrowTime(dto.getBorrowTime().getTime()+""); //下单时间
        //枚举参数
        mqBean.setBorrowType(BorrowType.MICROLOANS.getBusinessType()); //订单类型 7
        mqBean.setStatus(BorrowEnum.BORROW_STATUS.getValue()); //订单初始状态
        mqBean.setPeriod(BorrowEnum.BORROW_PERIOD.getValue()); //期数
        mqBean.setQueueName(EsbSystemEnum.PRODUCT_ORDER_NO.getValue()); //队列名称
        mqBean.setType(BorrowEnum.BORROW_MQ_TYPE.getValue());
        //基础参数
        mqBean.setSystemName(dto.getProduct());
        mqBean.setAddChannel(dto.getChannel());
        mqBean.setAddProduct(dto.getProduct());
        mqBean.setAppversion(dto.getVersion());
        mqBean.setIp(dto.getIp());
        return mqBean;
    }

    /**
     * @description 初始化借款单流水实体
     * @author hantongyang
     * @time 2017/1/20 17:01
     * @method initBorrowEntity
     * @param param
     * @return com.mobanker.shanyidai.esb.model.entity.borrow.BorrowEntity
     */
    public static BorrowEntity initBorrowEntity(BorrowOrderParamDto param){
        BorrowEntity entity = BeanUtil.cloneBean(param, BorrowEntity.class);
        entity.setBorrowStatus(BorrowEnum.BORROW_STATUS.getValue()); //订单状态
        entity.setBorrowPeriod(Integer.parseInt(BorrowEnum.BORROW_PERIOD.getValue())); //订单期数
        entity.setPeriodDays(param.getBorrowDays()); //每期天数
        entity.setBorrowType(BorrowType.MICROLOANS.getBusinessType()); //订单类型
        if(StringUtils.isBlank(param.getMobileInfo())){
            entity.setUserPhone(param.getPhone()); //电话
        }else{
            entity.setUserPhone(param.getMobileInfo());
        }
        entity.setProductModelId(param.getProductId());
        entity.setRemoteIp(param.getIp()); //IP
        entity.setUserCode(param.getCode()); //用户Code
        return entity;
    }

    /**
     * @description 初始化订单MQ回调实体
     * @author hantongyang
     * @time 2017/1/20 17:22
     * @method initMqOrderinfoSydEntity
     * @param param
     * @return com.mobanker.shanyidai.esb.model.entity.rabbitmq.MqConsumerOrderLogEntity
     */
    public static MqConsumerOrderLogEntity initMqOrderinfoSydEntity(OrderConsumerMessage param){
        MqConsumerOrderLogEntity bean = BeanUtil.cloneBean(param, MqConsumerOrderLogEntity.class);
        bean.setUserId(StringUtils.isBlank(param.getUserId()) ? null : Long.parseLong(param.getUserId()));
        bean.setProduct(param.getAddProduct());
        bean.setRemoteIp(param.getIp());
        bean.setChannel(param.getAddChannel());
        bean.setVersion(param.getAppversion());
        bean.setBorrowAmount(StringUtils.isBlank(param.getAmount()) ? null : new BigDecimal(param.getAmount()));
        bean.setBorrowStatus(OrderCenterStatusEnum.getByCode(param.getStatus()).getStatus());
        bean.setBorrowId(StringUtils.isBlank(param.getOrderNo()) ? null : Long.parseLong(param.getOrderNo()));
        bean.setBorrowNid(param.getOrderNid());
        bean.setBorrowTime(DateUtil.parse2Date(param.getBorrowTime(), DatePattern.DATE_TIME.getPattern()));
        bean.setPeriodDays(StringUtils.isBlank(param.getPeriodDays()) ? null : Integer.parseInt(param.getPeriodDays()));
        bean.setPeriod(StringUtils.isBlank(param.getPeriod()) ? null : Integer.parseInt(param.getPeriod()));
        return bean;
    }

    /**
     * @description 初始化借款单流水部分实体
     * @author hantongyang
     * @time 2017/1/22 15:53
     * @method initBorrowEntityByMq
     * @param entity
     * @param callBean
     * @return void
     */
    public static void initBorrowEntityByMq(BorrowEntity entity, OrderConsumerMessage callBean){
        String borrowStatus = OrderCenterStatusEnum.getByCode(callBean.getStatus()).getStatus();
        entity.setBorrowStatus(borrowStatus);
        entity.setBorrowSuccessTime(new Date());
        entity.setProductModelId(callBean.getProductModelId());
        entity.setBorrowNid(callBean.getOrderNid());
    }
}
