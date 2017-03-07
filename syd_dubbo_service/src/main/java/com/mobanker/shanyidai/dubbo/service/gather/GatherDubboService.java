package com.mobanker.shanyidai.dubbo.service.gather;

import com.mobanker.shanyidai.dubbo.dto.gather.*;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/8 15:16
 */
public interface GatherDubboService {

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastContact
     * @description 获取最后一次采集通讯录信息的时间
     * @time 14:51 2017/2/14
     */
    public ResponseEntity getLastContact(Long userId);

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastSms
     * @description 获取最后一次采集短信的时间
     * @time 14:56 2017/2/14
     */
    public ResponseEntity getLastSms(Long userId);

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastCallRecords
     * @description 获取最后一次采集通话记录的时间
     * @time 14:58 2017/2/14
     */
    public ResponseEntity getLastCallRecords(Long userId);

    /**
     * @param mongoMobile
     * @return ResponseEntity
     * @description 设备信息采集
     * @author Richard Core
     * @time 2017/2/8 15:20
     * @method saveDevice
     */
    public ResponseEntity saveDevice(MongoMobileDto mongoMobile);

    /**
     * @param mongoLbs
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method saveLbs
     * @description LBS信息采集
     * @time 19:50 2017/2/9
     */
    public ResponseEntity saveLbs(MongoLbsDto mongoLbs);

    /**
     * @param mongoCallRecords
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method saveCallRecords
     * @description 通话记录信息采集
     * @time 10:51 2017/2/10
     */
    public ResponseEntity saveCallRecords(MongoCallRecordsDto mongoCallRecords);

    /**
     * @param mongoPhoneBook
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method savePhoneBook
     * @description 通讯录信息采集
     * @time 11:08 2017/2/10
     */
    public ResponseEntity savePhoneBook(MongoPhoneBookDto mongoPhoneBook);

    /**
     * @param mongoSmsInfo
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method savaSmsInfo
     * @description
     * @time 13:52 2017/2/10
     */
    public ResponseEntity savaSmsInfo(MongoSmsInfoDto mongoSmsInfo);

    /**
     * @param appOperationActivationDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method appOperationActivation
     * @description 设备激活信息
     * @time 16:55 2017/2/27
     */
    public ResponseEntity appOperationActivation(AppOperationActivationDto appOperationActivationDto);
}
