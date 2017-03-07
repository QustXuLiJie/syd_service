package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.cust.cardinfo.contract.params.CardInfoParams;
import com.mobanker.cust.searchengine.contract.params.SearchListByCardNumOrCardMobileParams;

import java.util.List;
import java.util.Map;

/**
 * @desc:银行卡基础服务
 * @author: Richard Core
 * @create time: 2016/12/30 16:34
 */
public interface BankCardDubbo {
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
    public List<Map<String, String>> getBankCardList(CardInfoParams params);

    /**
     * @param params
     * @return void
     * @description 添加银行卡
     * @author Richard Core
     * @time 2017/1/4 13:40
     * @method addCardInfoByUserId
     */
    public void addCardInfoByUserId(CardInfoParams params);

    /**
     * @param params
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @description 根据卡号或者预留手机号查询用户的银行卡信息
     * @author Richard Core
     * @time 2017/1/3 14:42
     * @method searchCardByPhoneOrCard
     */
    public List<Map<String, String>> searchCardByPhoneOrCard(SearchListByCardNumOrCardMobileParams params);
}
