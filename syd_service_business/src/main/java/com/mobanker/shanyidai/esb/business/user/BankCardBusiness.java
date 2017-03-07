package com.mobanker.shanyidai.esb.business.user;

import com.mobanker.basedata.contract.dto.CardBinDto;
import com.mobanker.basedata.contract.params.CardBinParams;
import com.mobanker.cust.cardinfo.contract.params.CardInfoParams;
import com.mobanker.cust.searchengine.contract.params.SearchListByCardNumOrCardMobileParams;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/1/3 10:38
 */
public interface BankCardBusiness {
    /**
     * @param params
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * 返回map字段说明：
     * private long userId;//用户标识
     * private String cardNum;//银行卡号
     * private String cardBankName;//银行卡银行名称
     * private String cardMobile;//银行卡银行预留手机号
     * private int cardYstatus;//银行卡验证状态
     * @description 根据用户userId获取用户的银行卡列表
     * @author Richard Core
     * @time 2017/1/3 10:28
     * @method getBankCardList
     */
    public List<Map<String, String>> getBankCardList(CardInfoParams params);

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
    public ResponseEntity bankCardVerify(Map<String, String> params);

    /**
     * @param params
     * @return CardBinDto
     * @description 根据卡bin查询银行行卡信息（根据卡号查询发卡行）
     * @author Richard Core
     * @time 2017/1/4 14:13
     * @method getCardBinByCardNo
     */
    public CardBinDto getCardBinByCardNo(CardBinParams params);

    /**
     * @param params
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @description 根据卡号或者预留手机号查询用户的银行卡信息
     * @author Richard Core
     * @time 2017/1/3 14:42
     * @method searchCardByPhoneOrCard
     */
    public List<Map<String, String>> getCardByPhoneOrCard(SearchListByCardNumOrCardMobileParams params);

    /**
     * @param params
     * @return void
     * @description 添加银行卡
     * @author Richard Core
     * @time 2017/1/4 13:40
     * @method addCardInfoByUserId
     */
    public void addCardInfoByUserId(CardInfoParams params);
}
