package com.mobanker.shanyidai.dubbo.service.user;

import com.mobanker.shanyidai.dubbo.dto.user.BankCardDto;
import com.mobanker.shanyidai.dubbo.dto.user.BankCardParamDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @desc:用户银行卡相关综合服务
 * @author: Richard Core
 * @create time: 2017/1/3 10:43
 */
public interface BankCardDubboService {
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
    public ResponseEntity getBankCardList(BankCardParamDto dto);

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
    public ResponseEntity getBankCardVerify(BankCardDto dto);

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据卡号查询发卡行信息
     * @author Richard Core
     * @time 2017/1/4 15:06
     * @method getCardBinByCardNo
     */
    public ResponseEntity getCardBinByCardNo(BankCardDto dto);

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据手机号或者银行卡号查询银行卡信息
     * @author Richard Core
     * @time 2017/1/4 15:31
     * @method getCardByPhoneOrCardNo
     */
    public ResponseEntity getCardByPhoneOrCardNo(BankCardDto dto);


    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 添加银行卡
     * @author Richard Core
     * @time 2017/1/4 15:56
     * @method addCardInfoByUserId
     */
    public ResponseEntity addCardInfoByUserId(BankCardParamDto dto);

}
