package com.mobanker.shanyidai.esb.business.bigdata;

import com.mobanker.bigdata.sensor.microsite.contract.*;
import com.mobanker.common.utils.DateUtils;
import com.mobanker.shanyidai.dubbo.dto.auth.CreditInfoParamsDto;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowOrderParamDto;
import com.mobanker.shanyidai.dubbo.dto.gather.*;
import com.mobanker.shanyidai.dubbo.dto.user.UserLoginDto;
import com.mobanker.shanyidai.esb.common.bigdata.BigDataClient;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.model.dto.user.UserLoginBean;

import java.util.Date;
import java.util.Map;

/**
 * @desc: 大数据埋点处理
 * @author: Richard Core
 * @create time: 2017/2/13 13:48
 */
public class BigDataConvert {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(BigDataConvert.class);
    public static final String BORROW = "borrow";

    /**
     * @param mongoMobile
     * @return void
     * @description 手机设备信息采集大数据埋点
     * @author Richard Core
     * @time 2017/2/13 14:12
     * @method sendDeviceInfo
     */
    public static void sendDeviceInfo(MongoMobileDto mongoMobile) {
        if (mongoMobile == null) {
            return;
        }
        try {
            BigDataUserDeviceDto bigDataDto = new BigDataUserDeviceDto();
            bigDataDto.setUserId(mongoMobile.getUser_id().toString());//用户编号
            bigDataDto.setDeviceId(mongoMobile.getMobileDeviceId());//用户设备号
            bigDataDto.setSims(mongoMobile.getMobileNumber());//sim卡号,可能多个
            bigDataDto.setIp(mongoMobile.getMobileIp());//ip
            bigDataDto.setModel(mongoMobile.getMobileModel());//手机型号
            bigDataDto.setCapturedTime(DateUtils.convert(new Date()));//抓取时间
            BigDataClient.saveBigdata(bigDataDto);
        } catch (Exception e) {
            LOGGER.warn("手机设备信息采集大数据埋点异常" + e);
        }
    }

    /**
     * @param mongoLbs
     * @return void
     * @author xulijie
     * @method sendUserLbs
     * @description 用户lbs信息采集大数据埋点
     * @time 17:02 2017/2/13
     */
    public static void sendUserLbs(MongoLbsDto mongoLbs) {
        if (mongoLbs == null) {
            return;
        }
        try {
            BigDataUserLbsDto bigDataUserLbsDto = new BigDataUserLbsDto();
            bigDataUserLbsDto.setUserId(mongoLbs.getUser_id().toString());//用户编号
            bigDataUserLbsDto.setLat(mongoLbs.getLat());//纬度
            bigDataUserLbsDto.setLon(mongoLbs.getLon());//经度
            bigDataUserLbsDto.setCapturedTime(DateUtils.convert(new Date()));//抓取时间
            BigDataClient.saveBigdata(bigDataUserLbsDto);
        } catch (Exception e) {
            LOGGER.warn("用户lbs信息采集大数据埋点异常" + e);
        }
    }

    /**
     * @param mongoCallRecords
     * @return void
     * @author xulijie
     * @method sendCallRecords
     * @description 用户通话记录采集大数据埋点
     * @time 17:46 2017/2/13
     */
    public static void sendCallRecords(MongoCallRecordsDto mongoCallRecords) {
        if (mongoCallRecords == null) {
            return;
        }
        try {
            BigDataUserCallRecordsDto bigDataUserCallRecordsDto = new BigDataUserCallRecordsDto();
            bigDataUserCallRecordsDto.setUserId(mongoCallRecords.getUser_id().toString());//用户编号
            bigDataUserCallRecordsDto.setCalltime(mongoCallRecords.getCalltime());//通话时间
            bigDataUserCallRecordsDto.setType(String.valueOf(mongoCallRecords.getType()));//通话类型
            bigDataUserCallRecordsDto.setDuration(mongoCallRecords.getDuration());//通话持续时间
            bigDataUserCallRecordsDto.setOriginPhone(mongoCallRecords.getPhone());//原始抓取的通话phone 待确认
            bigDataUserCallRecordsDto.setCapturedTime(DateUtils.convert(new Date()));//抓取时间
            BigDataClient.saveBigdata(bigDataUserCallRecordsDto);
        } catch (Exception e) {
            LOGGER.warn("用户通话记录信息采集大数据埋点异常" + e);
        }
    }

    /**
     * @param mongoPhoneBook
     * @return void
     * @author xulijie
     * @method sendContacts
     * @description 用户通讯录信息采集大数据
     * @time 18:02 2017/2/13
     */
    public static void sendContacts(MongoPhoneBookDto mongoPhoneBook) {
        if (mongoPhoneBook == null) {
            return;
        }
        try {
            BigDataUserContactsDto bigDataUserContactsDto = new BigDataUserContactsDto();
            bigDataUserContactsDto.setUserId(mongoPhoneBook.getUser_id().toString());//用户编号
            bigDataUserContactsDto.setOriginPhone(mongoPhoneBook.getTel());//原始抓取的通讯录tel
            bigDataUserContactsDto.setName(mongoPhoneBook.getName());//通讯录姓名
            bigDataUserContactsDto.setCapturedTime(DateUtils.convert(new Date()));//抓取时间
            BigDataClient.saveBigdata(bigDataUserContactsDto);
        } catch (Exception e) {
            LOGGER.warn("用户通讯录采集大数据埋点异常" + e);
        }
    }

    /**
     * @param mongoSms
     * @return void
     * @author xulijie
     * @method sendSms
     * @description 用户短信大数据上传
     * @time 18:11 2017/2/13
     */
    public static void sendSms(MongoSmsInfoDto mongoSms) {
        if (mongoSms == null) {
            return;
        }
        try {
            BigDataUserSmsDto bigDataUserSmsDto = new BigDataUserSmsDto();
            bigDataUserSmsDto.setUserId(mongoSms.getUser_id().toString());//用户编号
            bigDataUserSmsDto.setCalltime(mongoSms.getCalltime());//短信发生时间
            bigDataUserSmsDto.setContent(mongoSms.getContent());//短信内容
            bigDataUserSmsDto.setType(String.valueOf(mongoSms.getType()));//短信类型
            bigDataUserSmsDto.setOriginPhone(mongoSms.getPhone());//原始抓取的短信phone 待确认
            bigDataUserSmsDto.setName(mongoSms.getName());//短信对象姓名
            bigDataUserSmsDto.setCapturedTime(DateUtils.convert(new Date()));//抓取时间
            BigDataClient.saveBigdata(bigDataUserSmsDto);
        } catch (Exception e) {
            LOGGER.warn("用户短信采集大数据埋点异常" + e);
        }
    }

    /**
     * @param appOperationActivationDto
     * @return void
     * @author xulijie
     * @method sendActivation
     * @description 用户激活信息上传大数据
     * @time 11:32 2017/2/28
     */
    public static void sendActivation(AppOperationActivationDto appOperationActivationDto) {
        if (appOperationActivationDto == null) {
            return;
        }
        try {
            BigDataUserActivationDto bigDataUserActivationDto = new BigDataUserActivationDto();
            bigDataUserActivationDto.setDeviceId(appOperationActivationDto.getMobileDeviceId());//用户编号
            bigDataUserActivationDto.setAddChannel(appOperationActivationDto.getChannel());//来源渠道
            bigDataUserActivationDto.setAddProduct(appOperationActivationDto.getProduct());//来源产品
            bigDataUserActivationDto.setActivationTime(DateUtils.convert(new Date()));//激活时间
            BigDataClient.saveBigdata(bigDataUserActivationDto);
        } catch (Exception e) {
            LOGGER.warn("设备激活采集大数据埋点异常" + e);
        }
    }

    /**
     * @param creditInfoParamsDto
     * @return void
     * @author xulijie
     * @method sendZhiMaAuthBaseInfo
     * @description 芝麻授权基本信息
     * @time 11:32 2017/2/28
     */
    public static void sendZhiMaAuthBaseInfo(CreditInfoParamsDto creditInfoParamsDto) {
        if (creditInfoParamsDto == null) {
            return;
        }
        try {
            BigDataZhiMaAuthBaseInfoDto bigDataZhiMaAuthBaseInfoDto = new BigDataZhiMaAuthBaseInfoDto();
            bigDataZhiMaAuthBaseInfoDto.setUserId(creditInfoParamsDto.getUserId().toString());//用户编号
            Map<String, Object> commonFields = creditInfoParamsDto.getCommonFields();
            //授权产品 shoujidai | kayidai | uzone | shanyidai
            bigDataZhiMaAuthBaseInfoDto.setAddProduct(commonFields.get("addProduct").toString());
            bigDataZhiMaAuthBaseInfoDto.setAddChannel(commonFields.get("addChannel").toString());//添加渠道
            bigDataZhiMaAuthBaseInfoDto.setRealname(creditInfoParamsDto.getRealName());//真实姓名
            bigDataZhiMaAuthBaseInfoDto.setCardId(creditInfoParamsDto.getCardId());//身份证号
            //获取芝麻跳过状态：先获取map，在根据id取出value后转换成string SkipAuthorize:借款来源是否跳过 0：未跳过 1：跳过
            bigDataZhiMaAuthBaseInfoDto.setSkipAuthorize(creditInfoParamsDto.getSaveFields().get("alipaySkipAuthorize").toString());
            BigDataClient.saveBigdata(bigDataZhiMaAuthBaseInfoDto);
        } catch (Exception e) {
            LOGGER.warn("芝麻认证采集大数据异常" + e);
        }
    }

    /**
     * @param userLoginBean
     * @return void
     * @author xulijie
     * @method sendUserLogin
     * @description 用户登录信息, 即登录行为记录
     * @time 11:57 2017/2/28
     */
    public static void sendUserLogin(UserLoginBean userLoginBean) {
        if (userLoginBean == null) {
            return;
        }
        try {
            BigDataUserLoginDto bigDataUserLoginDto = new BigDataUserLoginDto();
            bigDataUserLoginDto.setUserId(userLoginBean.getUserId());//用户编号
            bigDataUserLoginDto.setPhone(userLoginBean.getPhone());//手机号
//            bigDataUserLoginDto.setOpenId();//微信openId
            bigDataUserLoginDto.setLoginTime(DateUtils.convert(new Date()));//登录时间
            bigDataUserLoginDto.setLoginResult(userLoginBean.getLoginResult());//登录结果 suc/fail
            bigDataUserLoginDto.setLoginType(userLoginBean.getLoginType());//登录方式 普通登录 normal /微信登录 weChat /免密登录 noPwd
            BigDataClient.saveBigdata(bigDataUserLoginDto);
        } catch (Exception e) {
            LOGGER.warn("登录行为记录采集大数据异常" + e);
        }
    }

    /**
     * @param userLoginBean
     * @return void
     * @author xulijie
     * @method sendUserSignin
     * @description 用户登入信息, 即登录成功流水
     * @time 13:46 2017/2/28
     */
    public static void sendUserSignin(UserLoginBean userLoginBean) {
        if (userLoginBean == null) {
            return;
        }
        try {
            BigDataUserSigninDto bigDataUserSigninDto = new BigDataUserSigninDto();
            bigDataUserSigninDto.setUserId(userLoginBean.getUserId());//用户编号
            bigDataUserSigninDto.setLoginTime(DateUtils.convert(new Date()));//登录时间
            BigDataClient.saveBigdata(bigDataUserSigninDto);
        } catch (Exception e) {
            LOGGER.warn("登录成功流水大数据采集异常" + e);
        }
    }

    /**
     * @param borrowOrderParamDto
     * @return void
     * @author xulijie
     * @method send
     * @description 借款及引流渠道来源记录表
     * @time 16:36 2017/2/28
     */
    public static void sendBorrowChannel(BorrowOrderParamDto borrowOrderParamDto) {
        if (borrowOrderParamDto == null) {
            return;
        }
        try {
            BigDataBorrowChannelDto bigDataBorrowChannelDto = new BigDataBorrowChannelDto();
            bigDataBorrowChannelDto.setUserId(borrowOrderParamDto.getUserId().toString());//用户编号
            bigDataBorrowChannelDto.setAddChannel(borrowOrderParamDto.getChannel());//借款渠道
            bigDataBorrowChannelDto.setFromSource(borrowOrderParamDto.getProduct());//借款来源
            bigDataBorrowChannelDto.setOrderId(borrowOrderParamDto.getBorrowNid());//借款单号
            BigDataClient.saveBigdata(bigDataBorrowChannelDto);
        } catch (Exception e) {
            LOGGER.warn("借款及引流渠道来源记录大数据采集失败" + e);
        }
    }

    /**
     * @param borrowOrderParamDto
     * @return void
     * @author xulijie
     * @method sendUserCookie
     * @description 用户cookie信息采集
     * @time 17:35 2017/2/28
     */
    public static void sendUserCookie(BorrowOrderParamDto borrowOrderParamDto) {
        if (borrowOrderParamDto == null) {
            return;
        }
        try {
            BigDataUserCookieDto bigDataUserCookieDto = new BigDataUserCookieDto();
            bigDataUserCookieDto.setUserId(borrowOrderParamDto.getUserId().toString());//用户编号
            bigDataUserCookieDto.setAddChannel(borrowOrderParamDto.getChannel());//添加渠道
            bigDataUserCookieDto.setAddProudct(borrowOrderParamDto.getProduct());//添加产品
            bigDataUserCookieDto.setIp(borrowOrderParamDto.getIp());//IP
            bigDataUserCookieDto.setType(BORROW);//类型
            bigDataUserCookieDto.setCapturedTime(DateUtils.convert(new Date()));//抓取时间
            bigDataUserCookieDto.setUserCookie(borrowOrderParamDto.getUserCookie());//用户cookieId
            BigDataClient.saveBigdata(bigDataUserCookieDto);
        } catch (Exception e) {
            LOGGER.warn("用户cookie信息大数据采集失败" + e);
        }
    }
}
