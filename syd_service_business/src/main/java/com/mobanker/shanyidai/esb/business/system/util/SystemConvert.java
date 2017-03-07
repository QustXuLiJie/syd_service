package com.mobanker.shanyidai.esb.business.system.util;

import com.mobanker.shanyidai.dubbo.dto.system.UserDefaultBankDto;
import com.mobanker.shanyidai.esb.common.constants.EsbSystemEnum;
import com.mobanker.shanyidai.esb.common.constants.SydConstant;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.model.entity.system.DataDictionaryEntity;
import com.mobanker.shanyidai.esb.model.entity.system.UserDefaultBankEntity;

import java.util.Date;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/23 13:40
 */
public class SystemConvert {

    /**
     * @description 初始化数据字典查询实体
     * @author hantongyang
     * @time 2017/1/23 13:41
     * @method initDataDictionaryParam
     * @param type
     * @return com.mobanker.shanyidai.esb.model.entity.system.DataDictionaryEntity
     */
    public static DataDictionaryEntity initDataDictionaryParam(String type){
        DataDictionaryEntity bean = new DataDictionaryEntity();
        bean.setDicType(type);
        bean.setStatus(EsbSystemEnum.DB_STATUS_SUCCESS.getValue());
        return bean;
    }

    /**
     * @description 初始化用户入账银行卡查询实体
     * @author hantongyang
     * @time 2017/2/14 16:57
     * @method initSearchUserDefaultBankEntity
     * @param userId
     * @return com.mobanker.shanyidai.esb.model.entity.system.UserDefaultBankEntity
     */
    public static UserDefaultBankEntity initSearchUserDefaultBankEntity(Long userId){
        UserDefaultBankEntity param = new UserDefaultBankEntity();
        param.setUserId(userId);
        param.setStatus(SydConstant.RSP_STATUS_SUCCESS);
        return param;
    }

    /**
     * @description 初始化用户入账银行卡保存实体
     * @author hantongyang
     * @time 2017/2/14 16:55
     * @method initUserDefaultBankEntity
     * @param userId
     * @param cardNo
     * @return com.mobanker.shanyidai.esb.model.entity.system.UserDefaultBankEntity
     */
    public static UserDefaultBankEntity initUserDefaultBankEntity(Long userId, String cardNo){
        UserDefaultBankEntity entity = new UserDefaultBankEntity();
        entity.setUserId(userId);
        entity.setCreditBankCard(cardNo);
        entity.setStatus(SydConstant.RSP_STATUS_SUCCESS);
        return entity;
    }

    /**
     * @description 初始化用户入账银行卡更新实体
     * @author hantongyang
     * @time 2017/2/14 16:58
     * @method initUpdateUserDefaultBankEntity
     * @param dto
     * @return com.mobanker.shanyidai.esb.model.entity.system.UserDefaultBankEntity
     */
    public static UserDefaultBankEntity initUpdateUserDefaultBankEntity(UserDefaultBankDto dto, String cardNo){
        if(dto == null){
            return null;
        }
        UserDefaultBankEntity entity = BeanUtil.cloneBean(dto, UserDefaultBankEntity.class);
        entity.setCreditBankCard(cardNo);
        entity.setUpdateTime(new Date());
        return entity;
    }
}
