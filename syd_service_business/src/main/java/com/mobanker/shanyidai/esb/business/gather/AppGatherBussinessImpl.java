package com.mobanker.shanyidai.esb.business.gather;

import com.mobanker.shanyidai.dubbo.dto.gather.*;
import com.mobanker.shanyidai.esb.business.bigdata.BigDataConvert;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.dao.terminal.AppOperationActivationMapper;
import com.mobanker.shanyidai.esb.model.entity.device.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc: 终端信息采集接口
 * @author: Richard Core
 * @create time: 2017/2/8 10:28
 */
@Service
public class AppGatherBussinessImpl implements AppGatherBusiness {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(AppGatherBussinessImpl.class);
    private final static String ZERO = "0";
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private AppOperationActivationMapper activationMapper;

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastContact
     * @description 获取最后一次采集通讯录的时间
     * @time 15:26 2017/2/14
     */
    @Override
    public ResponseEntity getLastContact(Long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userId)).with(new Sort(new Sort.Order(Sort.Direction.DESC, "addtime")));
        MongoPhoneBookEntity ms = mongoTemplate.findOne(query, MongoPhoneBookEntity.class, "ncontacts");
        MongoPhoneBookDto result = BeanUtil.cloneBean(ms, MongoPhoneBookDto.class);
        Object data = null;
        if (result != null) {
            data = result.getAddtime();
        } else if (result == null) {
            data = ZERO;
        }
        ResponseEntity responseEntity = ResponseBuilder.normalResponse(data);
        return responseEntity;
    }

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastSms
     * @description 获取最后一次采集短信信息的时间
     * @time 15:32 2017/2/14
     */
    @Override
    public ResponseEntity getLastSms(Long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userId)).with(new Sort(new Sort.Order(Sort.Direction.DESC, "addtime")));
        MongoSmsInfoEntity ms = mongoTemplate.findOne(query, MongoSmsInfoEntity.class, "nsms");
        MongoSmsInfoDto result = BeanUtil.cloneBean(ms, MongoSmsInfoDto.class);
        Object data = null;
        if (result != null) {
            data = result.getAddtime();
        } else if (result == null) {
            data = ZERO;
        }

        ResponseEntity responseEntity = ResponseBuilder.normalResponse(data);
        return responseEntity;
    }

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastCallRedcords
     * @description 获取最后一次采集通话记录的时间
     * @time 15:34 2017/2/14
     */
    @Override
    public ResponseEntity getLastCallRecords(Long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userId)).with(new Sort(new Sort.Order(Sort.Direction.DESC, "addtime")));
        MongoCallRecordsEntity ms = mongoTemplate.findOne(query, MongoCallRecordsEntity.class, "ncallrecords");
        MongoCallRecordsDto result = BeanUtil.cloneBean(ms, MongoCallRecordsDto.class);
        Object data = null;
        //如果data为空说明没有上传过，data返回“0”
        if (result != null) {
            data = result.getAddtime();
        } else if (result == null) {
            data = ZERO;
        }
        ResponseEntity responseEntity = ResponseBuilder.normalResponse(data);
        return responseEntity;
    }

    /**
     * @param mongoMobile
     * @return void
     * @description 设备信息采集
     * @author Richard Core
     * @time 2017/2/8 15:20
     * @method saveDevice
     */
    @Override
    public void saveDevice(MongoMobileDto mongoMobile) {
        if (mongoMobile == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        MongoMobileEntity mongoMobileEntity = BeanUtil.cloneBean(mongoMobile, MongoMobileEntity.class);
        mongoTemplate.save(mongoMobileEntity, "mobile");
        //大数据埋点
        BigDataConvert.sendDeviceInfo(mongoMobile);
    }

    /**
     * @param mongoLbs
     * @return void
     * @author xulijie
     * @method saveLbs
     * @description lbs信息采集
     * @time 20:23 2017/2/9
     */
    @Override
    public void saveLbs(MongoLbsDto mongoLbs) {
        //参数校验
        if (mongoLbs == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        MongoLbsEntity mongoLbsEntity = BeanUtil.cloneBean(mongoLbs, MongoLbsEntity.class);
        mongoTemplate.save(mongoLbsEntity, "lbs");
        //大数据埋点
        BigDataConvert.sendUserLbs(mongoLbs);
    }

    /**
     * @param mongoCallRecords
     * @return void
     * @author xulijie
     * @method saveCallRecords
     * @description 通话记录信息采集
     * @time 11:02 2017/2/10
     */
    @Override
    public void saveCallRecords(MongoCallRecordsDto mongoCallRecords) {
        if (mongoCallRecords == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        MongoCallRecordsEntity mongoCallRecordsEntity = BeanUtil.cloneBean(mongoCallRecords, MongoCallRecordsEntity.class);
        mongoTemplate.save(mongoCallRecordsEntity, "ncallrecords");
        //大数据埋点
        BigDataConvert.sendCallRecords(mongoCallRecords);
    }

    /**
     * @param mongoPhoneBook
     * @return void
     * @author xulijie
     * @method savePhoneBook
     * @description 通讯录信息采集
     * @time 11:26 2017/2/10
     */
    @Override
    public void savePhoneBook(MongoPhoneBookDto mongoPhoneBook) {
        if (mongoPhoneBook == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        MongoPhoneBookEntity mongoPhoneBookEntity = BeanUtil.cloneBean(mongoPhoneBook, MongoPhoneBookEntity.class);
        mongoTemplate.save(mongoPhoneBookEntity, "ncontacts");
        //大数据埋点
        BigDataConvert.sendContacts(mongoPhoneBook);
    }

    /**
     * @param mongoSmsInfo
     * @return void
     * @author xulijie
     * @method saveSmsInfo
     * @description 短信信息采集
     * @time 15:50 2017/2/10
     */
    @Override
    public void saveSmsInfo(MongoSmsInfoDto mongoSmsInfo) {
        if (mongoSmsInfo == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        MongoSmsInfoEntity mongoSmsInfoEntity = BeanUtil.cloneBean(mongoSmsInfo, MongoSmsInfoEntity.class);
        mongoTemplate.save(mongoSmsInfoEntity, "nsms");
        //大数据埋点
        BigDataConvert.sendSms(mongoSmsInfo);
    }

    /**
     * @param appOperationActivationDto
     * @return void
     * @author xulijie
     * @method appOperationActivation
     * @description 设备激活
     * @time 11:43 2017/2/27
     */
    @Override
    public void appOperationActivation(AppOperationActivationDto appOperationActivationDto) {
        if (appOperationActivationDto == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED.getCode(),"激活信息为空");
        }
        //大数据埋点
        BigDataConvert.sendActivation(appOperationActivationDto);
        ActivationEntity activationEntity = BeanUtil.cloneBean(appOperationActivationDto, ActivationEntity.class);
        int insert = activationMapper.insert(activationEntity);
        if (insert < 1) {
            throw new EsbException(ErrorConstant.ACTIVATION_DAO_FAIL);
        }
    }
}
