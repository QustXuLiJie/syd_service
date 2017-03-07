package com.mobanker.shanyidai.esb.business.system;

import com.mobanker.shanyidai.dubbo.dto.system.UserDefaultBankDto;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/14 10:17
 */
public interface UserDefaultBankBusiness {

    /**
     * @description 根据用户ID查询默认入账银行
     * @author hantongyang
     * @time 2017/2/14 10:21
     * @method findByUserId
     * @param userId
     * @return com.mobanker.shanyidai.dubbo.dto.system.UserDefaultBankDto
     */
    UserDefaultBankDto findByUserId(Long userId);

    /**
     * @description 设置用户入账银行卡
     * @author hantongyang
     * @time 2017/2/14 15:30
     * @method setDefaultCard
     * @param userId
     * @param bankCard
     * @return int
     */
    void setDefaultCard(Long userId, String bankCard);
}
