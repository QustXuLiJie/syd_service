package com.mobanker.shanyidai.esb.business.system;

import com.mobanker.shanyidai.dubbo.dto.system.UserDefaultBankDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.dao.system.UserDefaultBankMapper;
import com.mobanker.shanyidai.esb.model.entity.system.UserDefaultBankEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.mobanker.shanyidai.esb.business.system.util.SystemConvert.initSearchUserDefaultBankEntity;
import static com.mobanker.shanyidai.esb.business.system.util.SystemConvert.initUpdateUserDefaultBankEntity;
import static com.mobanker.shanyidai.esb.business.system.util.SystemConvert.initUserDefaultBankEntity;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/14 10:18
 */
@Service
public class UserDefaultBankBusinessImpl implements UserDefaultBankBusiness {

    @Resource
    private UserDefaultBankMapper userDefaultBankMapper;

    /**
     * @description 根据用户ID查询默认入账银行
     * @author hantongyang
     * @time 2017/2/14 10:21
     * @method findByUserId
     * @param userId
     * @return com.mobanker.shanyidai.dubbo.dto.system.UserDefaultBankDto
     */
    @Override
    public UserDefaultBankDto findByUserId(Long userId) {
        if(userId == null){
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        //查询数据库
        UserDefaultBankEntity result = userDefaultBankMapper.getOne(initSearchUserDefaultBankEntity(userId));
        //将Entity转换为Dto，并返回
        return BeanUtil.cloneBean(result, UserDefaultBankDto.class);
    }

    /**
     * @description 设置用户入账银行卡
     * @author hantongyang
     * @time 2017/2/14 15:30
     * @method setDefaultCard
     * @param userId
     * @param bankCard
     * @return int
     */
    @Override
    public void setDefaultCard(Long userId, String bankCard) {
        if(userId == null){
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        if(StringUtils.isBlank(bankCard)){
            throw new EsbException(ErrorConstant.BANK_CARD_NO_NULL);
        }
        //查询默认银行卡
        UserDefaultBankDto bankDto = findByUserId(userId);
        int count = 0;
        if(bankDto != null){
            //如果新传入的银行卡号和保存的银行卡号一致，不需要修改
            if(bankDto.getCreditBankCard().equals(bankCard)){
                return;
            }
            //更新用户的入账银行卡
            count = userDefaultBankMapper.update(initUpdateUserDefaultBankEntity(bankDto, bankCard));
        }else{
            //新增用户的入账银行卡
            count = userDefaultBankMapper.insert(initUserDefaultBankEntity(userId, bankCard));
        }
        //如果数量为0，表示没有新增或更新数据库，业务处理失败
        if(count == 0){
            throw new EsbException(ErrorConstant.ERROR_SET_USER_DEFAULT_BANK);
        }
    }
}
