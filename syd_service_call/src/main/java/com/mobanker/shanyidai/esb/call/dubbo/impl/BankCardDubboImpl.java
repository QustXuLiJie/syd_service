package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.mobanker.cust.cardinfo.contract.manager.DubboCardInfoManager;
import com.mobanker.cust.cardinfo.contract.params.CardInfoParams;
import com.mobanker.cust.searchengine.contract.manager.DubboSearchByCardOrCardMobileManager;
import com.mobanker.cust.searchengine.contract.params.SearchListByCardNumOrCardMobileParams;
import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.shanyidai.esb.call.dubbo.BankCardDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @desc: 银行卡基础服务
 * @author: Richard Core
 * @create time: 2016/12/30 16:35
 */
@Service
public class BankCardDubboImpl implements BankCardDubbo {
    public static final Logger logger = LogManager.getLogger(BankCardDubboImpl.class);
    @Resource
    private DubboCardInfoManager dubboCardInfoManager;
    @Resource
    private DubboSearchByCardOrCardMobileManager dubboSearchByCardOrCardMobileManager;

    /**
     * @param params
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @description 根据卡号或者预留手机号查询用户的银行卡信息
     * @author Richard Core
     * @time 2017/1/3 14:42
     * @method searchCardByPhoneOrCard
     */
    @Override
    public List<Map<String,String>> searchCardByPhoneOrCard(SearchListByCardNumOrCardMobileParams params) {
        ResponseEntity<List<Map<String, String>>> response = null;
        try {
            response = dubboSearchByCardOrCardMobileManager.getCardInfoNumNotNullByCardOrCardMobile(params);
        } catch (Exception e) {
            logger.error("根据卡号和手机号查询银行卡信息异常");
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (!CallResultUtil.isSuccess(response)) {
            logger.warn("根据卡号和手机号查询银行卡信息失败", response);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(),response.getMsg());
        }
        return response.getData();
    }
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
        ResponseEntity<List<Map<String, String>>> response = null;
        try {
            response = dubboCardInfoManager.getCardInfoByUserId(params);
        } catch (Exception e) {
            logger.error("调用银行卡服务，获取用户银行卡列表接口出错", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (!CallResultUtil.isSuccess(response)) {
            logger.warn("调用银行卡服务，获取用户银行卡列表接口失败", response);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), response.getMsg());
        }
        return response.getData();
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
        ResponseEntity<List<Map<String, String>>> response = null;
        try {
            response = dubboCardInfoManager.addCardInfoByUserId(params);
        } catch (Exception e) {
            logger.error("调用添加银行卡接口异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        if (!CallResultUtil.isSuccess(response)) {
            logger.warn("调用添加银行卡接口失败", response);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), response.getMsg());
        }
    }

}
