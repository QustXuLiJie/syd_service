package com.mobanker.shanyidai.dubbo.service.system;

import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/14 10:39
 */
public interface UserDefaultBankDubboService {

    /**
     * @description 根据用户ID查询用户默认银行卡
     * @author hantongyang
     * @time 2017/2/14 10:41
     * @method findDefaultBankByUserId
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity findDefaultBankByUserId(Long userId);

    /**
     * @description 根据用户ID，新增或修改用户的入账银行卡
     * @author hantongyang
     * @time 2017/2/14 17:07
     * @method setDefaultBankByUserId
     * @param userId
     * @param bankCard
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    ResponseEntity setDefaultBankByUserId(Long userId, String bankCard);
}
