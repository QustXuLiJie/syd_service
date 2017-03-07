package com.mobanker.shanyidai.esb.service.user;

import com.mobanker.shanyidai.dubbo.dto.user.UserBaseInfoDto;
import com.mobanker.shanyidai.dubbo.service.user.UserDubboService;
import com.mobanker.shanyidai.esb.business.user.UserBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/26 11:41
 */
public class UserServiceImpl implements UserDubboService {

    @Autowired
    private UserBusiness userBusiness;

    /**
     * @param userId
     * @param fields
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据用户ID查询用户详情信息
     * @author hantongyang
     * @time 2016/12/26 11:41
     * @method getUserInfoByUserId
     */
    @Override
    public ResponseEntity getUserInfoByUserId(Long userId, List<String> fields) {
        if (userId == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.LOGIN_TIME_OUT, this.getClass().getSimpleName(), "getUserInfoByUserId");
        }
        if (fields == null || fields.isEmpty()) {
            fields = UserEsbConvert.initSelectField();
        }
        try {
            Map<String, String> list = userBusiness.getUserInfo(userId, fields);
            return ResponseBuilder.normalResponse(list);
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.GET_USER_INFO_ERROR, this.getClass().getSimpleName(), "getUserInfoByUserId");
        }
    }

    /**
     * @param phone
     * @param fields
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据手机号查询用户详情信息
     * @author R.Core
     * @time 2016/12/28 11:41
     * @method getUserInfoByUserId
     */
    @Override
    public ResponseEntity getUserInfoByPhone(String phone, List<String> fields) {
        if (StringUtils.isBlank(phone)) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAMS_PHONE, this.getClass().getSimpleName(), "getUserInfoByPhone");
        }
        if (fields == null || fields.isEmpty()) {
            fields = UserEsbConvert.initSelectField();
        }
        try {
            Map<String, String> list = userBusiness.getUserInfoByPhone(phone, fields);
            return ResponseBuilder.normalResponse(list);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.GET_USER_INFO_ERROR, e, this.getClass().getSimpleName(), "getUserInfoByPhone");
        }
    }

    /**
     * @param baseInfoDto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 修改用户信息
     * @author hantongyang
     * @time 2016/12/26 22:24
     * @method updateUserInfo
     */
    @Override
    public ResponseEntity updateUserInfo(UserBaseInfoDto baseInfoDto) {
//        CommonUtil.checkBaseParam(baseInfoDto);
        if (baseInfoDto == null) {
            throw new EsbException(ErrorConstant.PARAMS_ILLEGE);
        }
        if(baseInfoDto.getUserId() == null){
            return ResponseBuilder.errorResponse(ErrorConstant.LOGIN_TIME_OUT, this.getClass().getSimpleName(), "updateUserInfo");
        }
        Map<String, Object> saveFields = baseInfoDto.getSaveFields();
        if (saveFields == null || saveFields.isEmpty()) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAMS_ILLEGE, this.getClass().getSimpleName(), "updateUserInfo");
        }
        try {
            userBusiness.updateUserInfo(baseInfoDto.getUserId(), saveFields, baseInfoDto.getCommonFields());
            return ResponseBuilder.normalResponse();
        } catch (EsbException e) {
            return ResponseBuilder.errorResponse(ErrorConstant.UPDATE_USER_INFO_ERROR, this.getClass().getSimpleName(), "getUserInfoByUserId");
        }
    }

    /**
     * @description 根据身份证号查询用户信息
     * @author hantongyang
     * @time 2017/3/4 14:35
     * @method getUserInfoByCardNo
     * @param cardNo
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity getUserInfoByCardNo(String cardNo) {
        if (StringUtils.isBlank(cardNo)) {
            return ResponseBuilder.errorResponse(ErrorConstant.ID_CARD_NULL, this.getClass().getSimpleName(), "getUserInfoByCardNo");
        }
        try {
            Map<String, String> list = userBusiness.getUserByCardNo(cardNo, UserEsbConvert.initSelectField());
            return ResponseBuilder.normalResponse(list);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.GET_USER_INFO_BY_CARDNO_ERROR, e, this.getClass().getSimpleName(), "getUserInfoByCardNo");
        }
    }
}
