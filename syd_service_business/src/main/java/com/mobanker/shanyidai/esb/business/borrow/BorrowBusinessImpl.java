/**
 * 
 */
package com.mobanker.shanyidai.esb.business.borrow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.message.Event;
import com.mobanker.framework.tracking.EE;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowInfoParamDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowOrderParamDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.OldBorrowOrderDto;
import com.mobanker.shanyidai.esb.business.bigdata.BigDataConvert;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.business.common.EsbRedisBusiness;
import com.mobanker.shanyidai.esb.call.dubbo.BorrowDubbo;
import com.mobanker.shanyidai.esb.call.http.BorrowHttp;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.EsbRedisKeyEnum;
import com.mobanker.shanyidai.esb.common.constants.EsbSystemEnum;
import com.mobanker.shanyidai.esb.common.constants.ZkConfigConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.dao.borrow.BorrowMapper;
import com.mobanker.shanyidai.esb.dao.rabbitmq.MqConsumerOrderLogMapper;
import com.mobanker.shanyidai.esb.dao.rabbitmq.MqProducerOrderLogMapper;
import com.mobanker.shanyidai.esb.model.dto.rabbitmq.OrderConsumerMessage;
import com.mobanker.shanyidai.esb.model.entity.borrow.BorrowEntity;
import com.mobanker.shanyidai.esb.model.entity.rabbitmq.MqConsumerOrderLogEntity;
import com.mobanker.shanyidai.esb.model.entity.rabbitmq.MqProducerOrderLogEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.mobanker.shanyidai.esb.business.borrow.util.BorrowConvert.*;

/**
 * 借款业务逻辑服务实现
 * 
 * @author chenjianping
 * @data 2016年12月16日
 */
@Service
public class BorrowBusinessImpl implements BorrowBusiness {

    public static final Logger logger = LogManager.getSlf4jLogger(BorrowBusinessImpl.class);

    @Resource
    private BorrowDubbo borrowDubbo;
    @Resource
    private BorrowHttp borrowHttp;
    @Resource
    private EsbCommonBusiness esbCommonBusiness;
    @Resource
    private BorrowMapper borrowMapper;
    @Resource
    private EsbRedisBusiness esbRedisBusiness;
    @Resource
    private MqConsumerOrderLogMapper mqConsumerOrderLogMapper;
    @Resource
    private MqProducerOrderLogMapper mqProducerOrderLogMapper;

    /**
     * @description 是否在前隆借款中
     * @author hantongyang
     * @time 2017/1/10 15:57
     * @method checkIsQLBorrowIng
     * @param userId
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity checkIsQLBorrowIng(Long userId) {
        if(userId == null){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        return borrowDubbo.checkIsQLBorrowIng(userId);
    }
    /**
     * @param param
     * @return
     * @description 查询借款历史
     * @author Richard Core
     * @time 2017/1/12 9:39
     * @method storyList
     */
    @Override
    public ResponseEntity getHistoryList(BorrowInfoParamDto param){
        return borrowDubbo.getHistoryList(param);
    }

    /**
     * @description 根据订单编号获取借款详情
     * @author hantongyang
     * @time 2017/1/12 15:10
     * @method getBorrowInfo
     * @param nid
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity getBorrowInfo(String nid) {
        if(StringUtils.isBlank(nid)){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.BORROW_GET_INFO.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(),"获取借款详情服务地址获取失败");
        return borrowHttp.getBorrowInfo(reqURL, nid);
    }

    /**
     * @description 保存借款单信息到流水表中
     * @author hantongyang
     * @time 2017/1/19 19:51
     * @method addBorrowRecord
     * @param param
     * @return Long
     */
    @Override
    public Long addBorrowRecord(BorrowOrderParamDto param) {
        checkBorrowOrderParam(param);
        BorrowEntity borrowEntity = initBorrowEntity(param);
        int saveCount = borrowMapper.insert(borrowEntity);
        if(saveCount < 1){
            throw new EsbException(ErrorConstant.ERROR_BORROW_ADD_ORDER_RECORD);
        }
        //大数据信息采集
        BigDataConvert.sendBorrowChannel(param);
        BigDataConvert.sendUserCookie(param);
        return borrowEntity.getId();
    }

    /**
     * @description 保存借款单MQ回调流水
     * @author hantongyang
     * @time 2017/1/20 17:20
     * @method addBorrowCallBackEntity
     * @param param
     * @return java.lang.Long
     */
    @Override
    public Long addBorrowCallBackEntity(OrderConsumerMessage param) {
        if(param == null){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        MqConsumerOrderLogEntity entity = initMqOrderinfoSydEntity(param);
        int saveCount = mqConsumerOrderLogMapper.insert(entity);
        if(saveCount < 1){
            throw new EsbException(ErrorConstant.ERROR_BORROW_CALLBACK_RECORD);
        }
        return entity.getId();
    }

    /**
     * @description 根据ID和产品查询闪宜贷数据库的借款单流水信息
     * @author hantongyang
     * @time 2017/1/20 16:50
     * @method getBorrowByIdAndProd
     * @param id
     * @param product
     * @return com.mobanker.shanyidai.esb.model.entity.borrow.BorrowEntity
     */
    @Override
    public BorrowEntity getBorrowByIdAndProd(Long id, String product) {
        //封装查询参数
        BorrowEntity entity = new BorrowEntity();
        entity.setId(id);
        entity.setProduct(product);
        //查询数据库中订单流水表
        List<BorrowEntity> borrowEntities = borrowMapper.get(entity);
        if(borrowEntities == null || borrowEntities.isEmpty()){
            return null;
        }
        //循环获取流水记录
        for (BorrowEntity borrowEntity : borrowEntities) {
            if(borrowEntity == null){
                continue;
            }
            return borrowEntity;
        }
        return null;
    }

    /**
     * @description 修改借款单流水信息
     * @author hantongyang
     * @time 2017/1/20 17:59
     * @method updateBorrowRecord
     * @param entity
     * @return java.lang.Long
     */
    @Override
    public Long updateBorrowRecord(BorrowEntity entity) {
        if(entity == null){
            throw new EsbException(ErrorConstant.ERROR_BORROW_UPDATE_ORDER_RECORD);
        }
        int update = borrowMapper.update(entity);
        if(update < 1){
            throw new EsbException(ErrorConstant.ERROR_BORROW_UPDATE_ORDER_RECORD);
        }
        return entity.getId();
    }

    /**
     * @description 保存旧订单中心订单
     * @author hantongyang
     * @time 2017/1/20 18:04
     * @method addOldBorrowOrder
     * @param entity
     * @return java.lang.Long
     */
    @Override
    public Long addOldBorrowOrder(BorrowEntity entity) {
        if(entity == null){
            throw new EsbException(ErrorConstant.ERROR_BORROW_ADD_OLD_RECORD);
        }
        OldBorrowOrderDto dto = BeanUtil.cloneBean(entity, OldBorrowOrderDto.class);
        ResponseEntity responseEntity = borrowDubbo.addOldBorrowOrder(dto);
        if(ResponseBuilder.isSuccess(responseEntity)){
            Integer result = (Integer) responseEntity.getData();
            return result == null ? null : result.longValue();
        }
        return null;
    }

    /**
     * @description 保存调用订单中心MQ流水
     * @author hantongyang
     * @time 2017/1/23 10:31
     * @method addMqProducerOrderEntity
     * @param param
     * @return java.lang.Long
     */
    @Override
    public Long addMqProducerOrderEntity(BorrowOrderParamDto param) {
        if(param == null){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        MqProducerOrderLogEntity bean = BeanUtil.cloneBean(param, MqProducerOrderLogEntity.class);
        int saveCount = mqProducerOrderLogMapper.insert(bean);
        if(saveCount < 1){
            throw new EsbException(ErrorConstant.ERROR_BORROW_PRODUCER_ORDER_LOG);
        }
        return bean.getId();
    }

    /**
     * @description 订单系统MQ回调监控
     * @author hantongyang
     * @time 2017/2/17 13:43
     * @method callBackBorrowOrder
     * @param callMsg
     * @param queue
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity callBackBorrowOrder(String callMsg, String queue) {
        logger.debug("保存新订单中心回调方法开始：", callMsg, queue);
        //1、转换参数，订单系统会根据传入参数直接返回，所以可以直接转换为OrderConsumerMessage
        if(StringUtils.isBlank(callMsg)){
            return ResponseBuilder.normalResponse();
        }
        OrderConsumerMessage callBean = JSONObject.parseObject(callMsg, OrderConsumerMessage.class);
        //验证参数是否为空
        if(callBean == null || StringUtils.isBlank(callBean.getOrderNo())
                || StringUtils.isBlank(callBean.getOrderNid())) {
            return ResponseBuilder.normalResponse();
        }
        //验证产品是否是闪宜贷产品
        if(StringUtils.isBlank(callBean.getAddProduct()) ||
                !EsbSystemEnum.PRODUCT.getValue().equals(callBean.getAddProduct())){
            return ResponseBuilder.normalResponse();
        }
        try{
            //2、入库rabbitMq消费流水
            addBorrowCallBackEntity(callBean);
            //3、根据转换后的参数订单号，查询是否是闪宜贷产品
            BorrowEntity entity = getBorrowByIdAndProd(Long.parseLong(callBean.getOrderNo()), callBean.getAddProduct());
            if(entity == null){
                return ResponseBuilder.normalResponse();
            }
            //3、状态判断并封装参数
            initBorrowEntityByMq(entity, callBean);
            //4、生成旧订单中心订单
            addOldBorrowOrder(entity);
            //5、更新申请记录表状态数据
            updateBorrowRecord(entity);
            //6、删除缓存中的借款信息
            esbRedisBusiness.delValue(EsbRedisKeyEnum.SYD_ADD_BORROW_ORDER.getValue() + entity.getUserId());
        }catch (Exception e){
            logger.debug("保存新订单中心回调方法异常：", e);
            //7、异常鹰眼埋点
            EE.logEvent("Monitor_Rabbitmq_Consumer", "EEROR-OrderStatusConsumer", Event.SUCCESS, JSON.toJSONString(callBean));
        }
        return ResponseBuilder.normalResponse();
    }
}
