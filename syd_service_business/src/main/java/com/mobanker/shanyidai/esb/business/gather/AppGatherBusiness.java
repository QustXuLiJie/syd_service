package com.mobanker.shanyidai.esb.business.gather;

import com.mobanker.shanyidai.dubbo.dto.gather.*;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.apache.poi.ss.formula.functions.T;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/8 10:28
 */
public interface AppGatherBusiness {

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastContact
     * @description 获取最后一次采集通讯录的时间
     * @time 15:26 2017/2/14
     */
    public ResponseEntity getLastContact(Long userId);

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastSms
     * @description 获取最后一次采集短信信息的时间
     * @time 15:32 2017/2/14
     */
    public ResponseEntity getLastSms(Long userId);

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @author xulijie
     * @method getLastCallRedcords
     * @description 获取最后一次采集通讯录的时间
     * @time 15:34 2017/2/14
     */
    public ResponseEntity getLastCallRecords(Long userId);

    /**
     * @param mongoMobile
     * @return void
     * @description 设备信息采集
     * @author Richard Core
     * @time 2017/2/8 15:20
     * @method saveDevice
     */
    public void saveDevice(MongoMobileDto mongoMobile);

    /**
     * @param mongoLbs
     * @return void
     * @author xulijie
     * @method saveLbs
     * @description lbs信息采集
     * @time 20:23 2017/2/9
     */
    public void saveLbs(MongoLbsDto mongoLbs);

    /**
     * @param mongoCallRecords
     * @return void
     * @author xulijie
     * @method saveCallRecords
     * @description 通话记录信息采集
     * @time 11:02 2017/2/10
     */
    public void saveCallRecords(MongoCallRecordsDto mongoCallRecords);

    /**
     * @param mongoPhoneBook
     * @return void
     * @author xulijie
     * @method savePhoneBook
     * @description 通讯录信息采集
     * @time 11:26 2017/2/10
     */
    public void savePhoneBook(MongoPhoneBookDto mongoPhoneBook);

    /**
     * @param mongoSmsInfo
     * @return void
     * @author xulijie
     * @method saveSmsInfo
     * @description 短信信息采集
     * @time 15:50 2017/2/10
     */
    public void saveSmsInfo(MongoSmsInfoDto mongoSmsInfo);

    /**
     * @param appOperationActivationDto
     * @return void
     * @author xulijie
     * @method appOperationActivation
     * @description 设备激活
     * @time 11:43 2017/2/27
     */
    public void appOperationActivation(AppOperationActivationDto appOperationActivationDto);
}
