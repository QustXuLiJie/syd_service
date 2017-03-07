package com.mobanker.shanyidai.esb.business.user;

import com.mobanker.basedata.contract.dto.CardBinDto;
import com.mobanker.basedata.contract.params.CardBinParams;
import com.mobanker.cust.cardinfo.contract.params.CardInfoParams;
import com.mobanker.cust.searchengine.contract.params.SearchListByCardNumOrCardMobileParams;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.call.dubbo.BankCardDubbo;
import com.mobanker.shanyidai.esb.call.dubbo.BaseDataDubbo;
import com.mobanker.shanyidai.esb.call.http.BankCardHttp;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.ZkConfigConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @desc: 用户银行卡相关服务封装
 * @author: Richard Core
 * @create time: 2017/1/3 10:38
 */
@Service
public class BankCardBusinessImpl implements BankCardBusiness {
    @Resource
    private BankCardDubbo bankCardDubbo;
    @Resource
    private EsbCommonBusiness esbCommonBusiness;
    @Autowired
    private BankCardHttp bankCardHttp;
    @Resource
    private BaseDataDubbo baseDataDubbo;

    /**
     * @param params
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
    public List<Map<String, String>> getBankCardList(CardInfoParams params) {
        return bankCardDubbo.getBankCardList(params);
    }

    /**
     * @param params 字段 字段名 必填 类型 说明
     *               accountNo 银行卡号 M String 卡号
     *               idCard 身份证号 C String 3要素必传
     *               name 姓名 M String 姓名
     *               phone 手机号 C String 4元素必填
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 银行卡认证
     * @author Richard Core
     * @time 2017/1/3 16:34
     * @method bankCardVerify
     */
    @Override
    public ResponseEntity bankCardVerify(Map<String, String> params) {
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.BANK_CARD_VERIFY_URL.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(),"银行卡认证服务地址获取失败");
        return bankCardHttp.bankCardVerify(reqURL, params);
    }
    /**
     * @param params
     * @return CardBinDto
     * @description 根据卡bin查询银行行卡信息（根据卡号查询发卡行）
     * @author Richard Core
     * @time 2017/1/4 14:13
     * @method getCardBinByCardNo
     */
    @Override
    public CardBinDto getCardBinByCardNo(CardBinParams params) {
        CardBinDto result = baseDataDubbo.getCardBinByCardNo(params);
        return result;
    }
    /**
     * @param params
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @description 根据卡号或者预留手机号查询用户的银行卡信息
     * @author Richard Core
     * @time 2017/1/3 14:42
     * @method searchCardByPhoneOrCard
     */
    @Override
    public List<Map<String,String>> getCardByPhoneOrCard(SearchListByCardNumOrCardMobileParams params) {
        return bankCardDubbo.searchCardByPhoneOrCard(params);
    }

    /**
     * @param params
     * @return void
     * @description 添加银行卡
     * @author Richard Core
     * @time 2017/1/4 13:40
     * @method addCardInfoByUserId
     */
    @Override
    public void addCardInfoByUserId(CardInfoParams params) {
        bankCardDubbo.addCardInfoByUserId(params);
    }
}
