package com.mobanker.shanyidai.esb.service.system;

import com.mobanker.shanyidai.dubbo.dto.system.UserDefaultBankDto;
import com.mobanker.shanyidai.dubbo.service.system.UserDefaultBankDubboService;
import com.mobanker.shanyidai.esb.business.system.UserDefaultBankBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

import static com.sun.tools.doclint.Entity.nu;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/14 10:40
 */
public class UserDefaultBankDubboServiceImpl implements UserDefaultBankDubboService {

    public static final Logger logger = LogManager.getSlf4jLogger(UserDefaultBankDubboServiceImpl.class);

    @Resource
    private UserDefaultBankBusiness userDefaultBankBusiness;

    /**
     * @description 根据用户ID查询用户默认银行卡
     * @author hantongyang
     * @time 2017/2/14 10:41
     * @method findDefaultBankByUserId
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity findDefaultBankByUserId(Long userId) {
        try{
            UserDefaultBankDto dto = userDefaultBankBusiness.findByUserId(userId);
            return ResponseBuilder.normalResponse(dto);
        }catch (Exception e){
            logger.error("======获取用户默认入账银行失败", e);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_USER_DEFAULT_CREDIT_BANK.getCode(), ErrorConstant.ERROR_USER_DEFAULT_CREDIT_BANK.getDesc());
        }
    }

    /**
     * @description 根据用户ID，新增或修改用户的入账银行卡
     * @author hantongyang
     * @time 2017/2/14 17:07
     * @method setDefaultBankByUserId
     * @param userId
     * @param bankCard
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity setDefaultBankByUserId(Long userId, String bankCard) {
        if(userId == null){
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        if(StringUtils.isBlank(bankCard)){
            throw new EsbException(ErrorConstant.BANK_CARD_NO_NULL);
        }
        try{
            userDefaultBankBusiness.setDefaultCard(userId, bankCard);
        return ResponseBuilder.normalResponse();
        }catch (Exception e){
            logger.error("======设置用户默认入账银行失败", e);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_SET_USER_DEFAULT_BANK.getCode(), ErrorConstant.ERROR_SET_USER_DEFAULT_BANK.getDesc());
        }
    }
}
