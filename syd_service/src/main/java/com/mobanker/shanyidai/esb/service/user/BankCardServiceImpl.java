package com.mobanker.shanyidai.esb.service.user;

import com.mobanker.basedata.contract.dto.CardBinDto;
import com.mobanker.basedata.contract.params.CardBinParams;
import com.mobanker.cust.cardinfo.contract.params.CardInfoParams;
import com.mobanker.cust.searchengine.contract.params.SearchListByCardNumOrCardMobileParams;
import com.mobanker.shanyidai.dubbo.dto.user.BankCardDto;
import com.mobanker.shanyidai.dubbo.dto.user.BankCardParamDto;
import com.mobanker.shanyidai.dubbo.dto.user.CardBinInfoDto;
import com.mobanker.shanyidai.dubbo.service.user.BankCardDubboService;
import com.mobanker.shanyidai.esb.business.user.BankCardBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @desc: 银行卡相关综合服务实现
 * @author: Richard Core
 * @create time: 2017/1/3 10:50
 */
public class BankCardServiceImpl implements BankCardDubboService {
    public static final Logger logger = LogManager.getSlf4jLogger(BankCardServiceImpl.class);
    private static BankCardParamDto dto;
    @Resource
    private BankCardBusiness bankCardBusiness;

    /**
     * @param dto
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * 返回map字段说明：
     * userId;//用户标识
     * cardNum;//银行卡号
     * cardBankName;//银行卡银行名称
     * cardMobile;//银行卡银行预留手机号
     * cardYstatus;//银行卡验证状态
     * @description 根据用户userId获取用户的银行卡列表
     * @author Richard Core
     * @time 2017/1/3 10:28
     * @method getBankCardList
     */
    @Override
    public ResponseEntity getBankCardList(BankCardParamDto dto) {
        try {
            //参数验证和处理
            CardInfoParams params = UserEsbConvert.getCardInfoParams(dto);
            //查询列表
            List<Map<String, String>> bankCardList = bankCardBusiness.getBankCardList(params);
            return ResponseBuilder.normalResponse(bankCardList);
        } catch (EsbException e) {
            logger.error("根据用户userId获取银行卡列表出错", e);
            return ResponseBuilder.errorResponse(ErrorConstant.BANK_CARD_LIST_ERROR, e, this.getClass().getSimpleName(), "getBankCardList");
        }
    }

    /**
     * @param dto 字段 字段名 必填 类型 说明
     *            accountNo 银行卡号 M String 卡号
     *            idCard 身份证号 C String 3要素必传
     *            name 姓名 M String 姓名
     *            phone 手机号 C String 4元素必填
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 银行卡认证
     * @author Richard Core
     * @time 2017/1/3 16:34
     * @method getBankCardVerify
     */
    @Override
    public ResponseEntity getBankCardVerify(BankCardDto dto) {
        //验证参数
        try {
            Map<String, String> params = UserEsbConvert.mapBankCardVerifyParam(dto);
            ResponseEntity response = bankCardBusiness.bankCardVerify(params);
            return response;
        } catch (Exception e) {
            logger.error("银行卡认证异常", e);
            return ResponseBuilder.errorResponse(ErrorConstant.BANK_CARD_LIST_ERROR, e, this.getClass().getSimpleName(), "getBankCardList");
        }
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据卡号查询发卡行信息
     * @author Richard Core
     * @time 2017/1/4 15:06
     * @method getCardBinByCardNo
     */
    @Override
    public ResponseEntity getCardBinByCardNo(BankCardDto dto) {

        try {
            //验证参数
            CommonUtil.checkBaseParam(dto);
            if (StringUtils.isBlank(dto.getBankCardNo())) {
                throw new EsbException(ErrorConstant.BANK_CARD_NO_NULL);
            }
            CardBinParams params = new CardBinParams();
            params.setCardNum(dto.getBankCardNo());
            CardBinDto cardBinByCardNo = bankCardBusiness.getCardBinByCardNo(params);
            CardBinInfoDto cardBinInfoDto = BeanUtil.cloneBean(cardBinByCardNo, CardBinInfoDto.class);
            return ResponseBuilder.normalResponse(cardBinInfoDto);
        } catch (EsbException e) {
            logger.error("根据卡号查询发卡行信息出错", e);
            return ResponseBuilder.errorResponse(ErrorConstant.CARD_BIN_ERROR, e, this.getClass().getSimpleName(), "getCardBinByCardNo");
        }
    }


    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据手机号或者银行卡号查询银行卡信息
     * @author Richard Core
     * @time 2017/1/4 15:31
     * @method getCardByPhoneOrCardNo
     */
    public ResponseEntity getCardByPhoneOrCardNo(BankCardDto dto) {
        try {
            //验证参数
            SearchListByCardNumOrCardMobileParams params = UserEsbConvert.mapFindByPhoneOrCardNoParams(dto);
            List<Map<String, String>> result = bankCardBusiness.getCardByPhoneOrCard(params);
            return ResponseBuilder.normalResponse(result);
        } catch (Exception e) {
            logger.error("根据手机号或者银行卡号查询银行卡信息出错", e);
            return ResponseBuilder.errorResponse(ErrorConstant.BANK_CARD_NO_PHONE_ERROR, e, this.getClass().getSimpleName(), "getCardByPhoneOrCardNo");
        }
    }


    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 添加银行卡
     * @author Richard Core
     * @time 2017/1/4 15:56
     * @method addCardInfoByUserId
     */
    public ResponseEntity addCardInfoByUserId(BankCardParamDto dto) {
        try {
            //参数验证和获取
            CardInfoParams params = UserEsbConvert.mapAddBankCardParams(dto);
            //添加银行卡
            bankCardBusiness.addCardInfoByUserId(params);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            logger.error("根据手机号或者银行卡号查询银行卡信息出错", e);
            return ResponseBuilder.errorResponse(ErrorConstant.BANK_CARD_NO_PHONE_ERROR, e, this.getClass().getSimpleName(), "getCardByPhoneOrCardNo");
        }
    }


}
