package com.mobanker.shanyidai.esb.service.user;

import com.mobanker.cust.baseinfo.contract.params.BaseInfoParams;
import com.mobanker.cust.cardinfo.contract.params.CardInfoParams;
import com.mobanker.cust.searchengine.contract.params.SearchListByCardNumOrCardMobileParams;
import com.mobanker.shanyidai.dubbo.dto.user.BankCardDto;
import com.mobanker.shanyidai.dubbo.dto.user.BankCardParamDto;
import com.mobanker.shanyidai.dubbo.dto.user.UserLoginDto;
import com.mobanker.shanyidai.dubbo.dto.user.UserRegisterDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import com.mobanker.shanyidai.esb.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author Richard Core
 * @description 用户相关服务 参数验证  参数拷贝等
 * @date 2016/12/23 13:48
 */
public class UserEsbConvert {

    /**
     * @param dto
     * @return void
     * @description 注册服务参数验证
     * @author Richard Core
     * @time 2016/12/23 13:49
     * @method getResponseEntity
     */
    public static void checkRegistParam(UserRegisterDto dto) {
        //验证基本参数
        CommonUtil.checkBaseParam(dto);
        CommonUtil.checkPhone(dto.getPhone());
        CommonUtil.checkPassword(dto.getPassword());
    }

    /**
     * @param dto
     * @return void
     * @description 验证登录参数
     * @author Richard Core
     * @time 2016/12/24 11:47
     * @method checkLoginParam
     */
    public static void checkLoginParam(UserLoginDto dto) {
        //验证基本参数
        CommonUtil.checkBaseParam(dto);
        CommonUtil.checkPhone(dto.getPhone());
        CommonUtil.checkPassword(dto.getPassword());
    }

    /**
     * @param dto
     * @return com.mobanker.cust.baseinfo.contract.params.BaseInfoParams
     * @description 基础服务 - 创建用户参数封装
     * @author Richard Core
     * @time 2016/12/23 14:13
     * @method getUserCreateparam
     */
    public static BaseInfoParams getUserCreateparam(UserRegisterDto dto) {
        BaseInfoParams params = new BaseInfoParams();
        //用户的基本信息
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("phone", dto.getPhone());
        data.put("password", dto.getPassword());
        data.put("regTime", DateUtil.getNowSeconds());
        data.put("regChannel", dto.getChannel());
        data.put("regProduct", dto.getProduct());
        data.put("regVersion", dto.getVersion());
        params.setSaveFields(data);
        //协议参数
        Map<String, Object> commonFields = new HashMap<String, Object>();
        commonFields.put("remoteIp", dto.getIp());
        commonFields.put("addChannel", dto.getChannel());
        commonFields.put("addProduct", dto.getProduct());
        commonFields.put("addVersion", dto.getVersion());
        commonFields.put("addUser", "system");
        params.setCommonFields(commonFields);
        return params;
    }
    /**
     * @param dto
     * @return com.mobanker.cust.cardinfo.contract.params.CardInfoParams
     * @description 添加银行卡验证参数
     * @author Richard Core
     * @time 2017/1/4 15:57
     * @method mapAddBankCardParams
     */
    public static CardInfoParams mapAddBankCardParams(BankCardParamDto dto) {
        CommonUtil.checkBaseParam(dto);
        if (dto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        if (StringUtils.isBlank(dto.getType())) {
            throw new EsbException(ErrorConstant.BANK_CARD_TYPE_NULL);
        }
        Map<String, Object> commonFields = dto.getCommonFields();
        if (commonFields == null || commonFields.isEmpty()) {
            throw new EsbException(ErrorConstant.BASE_ADD_PARAM_NULL);
        }
        Map<String, Object> saveFields = dto.getSaveFields();
        if (saveFields == null || saveFields.isEmpty()) {
            throw new EsbException(ErrorConstant.BANK_INFO_NULL);
        }

        CardInfoParams params = new CardInfoParams();
        params.setUserId(dto.getUserId());
        params.setType(dto.getType());//银行卡类型 debitCard
        params.setSaveFields(saveFields);
        params.setCommonFields(commonFields);
        return params;
    }

    /**
     * @param
     * @return java.util.List<java.lang.String>
     * @description 初始化获取的用户信息
     * @author hantongyang
     * @time 2016/12/26 14:22
     * @method initSelectField
     */
    public static List<String> initSelectField() {
        List<String> fields = new ArrayList<String>();
        fields.add("phone");
        fields.add("password");
        fields.add("userStatus");
        fields.add("realname");
        fields.add("realnameTimes");
        fields.add("sex");
        fields.add("birthday");
        fields.add("edu");
        fields.add("marriage");
        fields.add("whetherLost");
        fields.add("idCard");
        fields.add("idCardAddress");
        fields.add("addressTel");
        fields.add("address");
        fields.add("jobTypeId");
        fields.add("companyName");
        fields.add("companyTelCountry");
        fields.add("companyTelFixed");
        fields.add("companyTelSuffix");
        fields.add("jobTitle");
        fields.add("jobInfoAllIsExpire");
        fields.add("realnameAllStatus");
        fields.add("idCardStatus");
        fields.add("eduEducationDegree");
        return fields;
    }

    /**
     * @param dto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 银行卡认证参数验证和处理
     * @author Richard Core
     * @time 2017/1/4 10:38
     * @method mapBankCardVerifyParam
     */
    public static Map<String, String> mapBankCardVerifyParam(BankCardDto dto) {
        CommonUtil.checkBaseParam(dto);
        if (StringUtils.isBlank(dto.getBankCardNo())) {
            throw new EsbException(ErrorConstant.BANK_CARD_NO_NULL);
        }
        if (StringUtils.isBlank(dto.getIdCard()) && StringUtils.isBlank(dto.getPhone())) {
            throw new EsbException(ErrorConstant.ID_CARD_AND_PHONE_NULL);
        }
        if (StringUtils.isBlank(dto.getRealName())) {
            throw new EsbException(ErrorConstant.NO_REAL_NAME);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("accountNo", dto.getBankCardNo());
        params.put("idCard", dto.getIdCard());
        params.put("name", dto.getRealName());
        params.put("phone", dto.getPhone());
        return params;
    }

    /**
     * @param dto
     * @return SearchListByCardNumOrCardMobileParams
     * @description 封装根据手机号或者银行卡号查询银行卡信息接口参数
     * @author Richard Core
     * @time 2017/1/4 15:27
     * @method mapFindByPhoneOrCardNoParams
     */
    public static SearchListByCardNumOrCardMobileParams mapFindByPhoneOrCardNoParams(BankCardDto dto) {
        CommonUtil.checkBaseParam(dto);
//        CommonUtil.checkPhone(dto.getPhone());
        if (StringUtils.isBlank(dto.getBankCardNo()) &&
                StringUtils.isBlank(dto.getPhone())) {
            throw new EsbException(ErrorConstant.BANK_CARD_NO_PHONE_NULL);
        }
        if (StringUtils.isBlank(dto.getCardType())) {
            throw new EsbException(ErrorConstant.BANK_CARD_TYPE_NULL);
        }
        if (dto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        List<String> fields = Arrays.asList("cardId", "cardNum", "cardBankName", "cardMobile", "cardYstatus");
        SearchListByCardNumOrCardMobileParams params = new SearchListByCardNumOrCardMobileParams();
        params.setUserId(dto.getUserId());
        params.setCardNum(dto.getBankCardNo());
        params.setCardMobile(dto.getPhone());
        params.setType(dto.getCardType());
        params.setFields(fields);
        return params;
    }

    /**
     * @param dto
     * @return com.mobanker.cust.cardinfo.contract.params.CardInfoParams
     * @description 查询银行卡列表参数验证
     * @author Richard Core
     * @time 2017/1/3 11:23
     * @method getCardInfoParams
     */
    public static CardInfoParams getCardInfoParams(BankCardParamDto dto) {
        CommonUtil.checkBaseParam(dto);
        if (dto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        CardInfoParams params = new CardInfoParams();
        params.setUserId(dto.getUserId());
        if (dto.getFields() == null || dto.getFields().isEmpty()) {
            List<String> fields = new ArrayList<>();
            fields.add("cardId");
            fields.add("cardNum");
            fields.add("cardBankName");
            fields.add("cardMobile");
            fields.add("cardYstatus");
            fields.add("cardBankBranchName");
            params.setFields(fields);
        } else {
            params.setFields(dto.getFields());
        }
        return params;
    }
}
