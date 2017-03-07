package com.mobanker.shanyidai.esb.service.gather;

import com.mobanker.shanyidai.dubbo.dto.gather.*;
import com.mobanker.shanyidai.dubbo.service.gather.GatherDubboService;
import com.mobanker.shanyidai.esb.business.gather.AppGatherBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc: 数据采集
 * @author: Richard Core
 * @create time: 2017/2/8 15:17
 */
@Service
public class GatherServiceImpl implements GatherDubboService {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(GatherServiceImpl.class);
    @Resource
    private AppGatherBusiness appGatherBusiness;

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastContact
     * @description 获取最后一次采集通讯录信息的时间
     * @time 14:51 2017/2/14
     */
    @Override
    public ResponseEntity getLastContact(Long userId) {
        //验证userId
        if (userId == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.LOGIN_TIME_OUT, this.getClass().getSimpleName(), "getLastContact");
        }
        try {
            return appGatherBusiness.getLastContact(userId);
        } catch (Exception e) {
            LOGGER.warn("获取最后一次采集通讯录信息的时间异常" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastSms
     * @description 获取最后一次采集短信的时间
     * @time 14:56 2017/2/14
     */
    @Override
    public ResponseEntity getLastSms(Long userId) {
        if (userId == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.LOGIN_TIME_OUT, this.getClass().getSimpleName(), "getLastSms");
        }
        try {
            return appGatherBusiness.getLastSms(userId);
        } catch (Exception e) {
            LOGGER.warn("获取最后一次采集短信的时间异常" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastCallRecords
     * @description 获取最后一次采集通话记录的时间
     * @time 14:58 2017/2/14
     */
    @Override
    public ResponseEntity getLastCallRecords(Long userId) {
        if (userId == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.LOGIN_TIME_OUT, this.getClass().getSimpleName(), "getLastCallRecords");
        }
        try {
            return appGatherBusiness.getLastCallRecords(userId);
        } catch (Exception e) {
            LOGGER.warn("获取最后一次采集通话记录的时间异常" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @param mongoMobile
     * @return ResponseEntity
     * @description 设备信息采集
     * @author Richard Core
     * @time 2017/2/8 15:20
     * @method saveDevice
     */
    @Override
    public ResponseEntity saveDevice(MongoMobileDto mongoMobile) {
        try {
            if (mongoMobile == null) {
                throw new EsbException(ErrorConstant.PARAM_REQUIRED);
            }
            appGatherBusiness.saveDevice(mongoMobile);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            LOGGER.warn("信息采集保存异常" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @param mongoLbs
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method saveLbs
     * @description LBS信息采集
     * @time 19:50 2017/2/9
     */
    @Override
    public ResponseEntity saveLbs(MongoLbsDto mongoLbs) {
        try {
            if (mongoLbs == null) {
                throw new EsbException(ErrorConstant.PARAM_REQUIRED);
            }
            appGatherBusiness.saveLbs(mongoLbs);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            LOGGER.warn("信息采集保存异常" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @param mongoCallRecords
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method saveCallRecords
     * @description 通话记录信息采集
     * @time 10:51 2017/2/10
     */
    @Override
    public ResponseEntity saveCallRecords(MongoCallRecordsDto mongoCallRecords) {
        try {
            if (mongoCallRecords == null) {
                throw new EsbException(ErrorConstant.PARAM_REQUIRED);
            }
            appGatherBusiness.saveCallRecords(mongoCallRecords);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            LOGGER.warn("信息采集保存异常" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @param mongoPhoneBook
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method savePhoneBook
     * @description 通讯录信息采集
     * @time 11:08 2017/2/10
     */
    @Override
    public ResponseEntity savePhoneBook(MongoPhoneBookDto mongoPhoneBook) {
        try {
            if (mongoPhoneBook == null) {
                throw new EsbException(ErrorConstant.PARAM_REQUIRED);
            }
            appGatherBusiness.savePhoneBook(mongoPhoneBook);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            LOGGER.warn("信息采集保存异常" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @param mongoSmsInfo
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method savaSmsInfo
     * @description
     * @time 13:52 2017/2/10
     */
    @Override
    public ResponseEntity savaSmsInfo(MongoSmsInfoDto mongoSmsInfo) {
        try {
            if (mongoSmsInfo == null) {
                throw new EsbException(ErrorConstant.PARAM_REQUIRED);
            }
            appGatherBusiness.saveSmsInfo(mongoSmsInfo);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            LOGGER.warn("信息采集保存异常" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * @param appOperationActivationDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method appOperationActivation
     * @description 设备激活信息
     * @time 16:55 2017/2/27
     */
    @Override
    public ResponseEntity appOperationActivation(AppOperationActivationDto appOperationActivationDto) {
        if (appOperationActivationDto == null) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        try {
            appGatherBusiness.appOperationActivation(appOperationActivationDto);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.ACTIVATION_FAIL.getCode(), e.getMessage());
        }
    }
}
