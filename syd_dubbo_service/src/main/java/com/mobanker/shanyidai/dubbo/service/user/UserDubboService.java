package com.mobanker.shanyidai.dubbo.service.user;

import com.mobanker.shanyidai.dubbo.dto.user.UserBaseInfoDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.List;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/22 20:06
 */
public interface UserDubboService {

    /**
     * @param userId
     * @param fields
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据用户ID查询用户详情信息
     * @author hantongyang
     * @time 2016/12/26 11:41
     * @method getUserInfoByUserId
     */
    public ResponseEntity getUserInfoByUserId(Long userId, List<String> fields);

    /**
     * @param phone
     * @param fields
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据手机号查询用户详情信息
     * @author R.Core
     * @time 2016/12/28 11:41
     * @method getUserInfoByUserId
     */
    public ResponseEntity getUserInfoByPhone(String phone, List<String> fields);

    /**
     * @param baseInfoDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 修改用户信息
     * @author R.Core
     * @time 2016/12/26 22:24
     * @method updateUserInfo
     */
    public ResponseEntity updateUserInfo(UserBaseInfoDto baseInfoDto);

    /**
     * @param cardNo
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据身份证号查询用户信息
     * @author hantongyang
     * @time 2017/3/4 14:35
     * @method getUserInfoByCardNo
     */
    ResponseEntity getUserInfoByCardNo(String cardNo);

}
