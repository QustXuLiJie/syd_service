package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.basedata.contract.dto.CardBinDto;
import com.mobanker.basedata.contract.params.CardBinParams;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/1/4 13:49
 */
public interface BaseDataDubbo {
    /**
     * @param params
     * @return void
     * @description 根据卡bin查询银行行卡信息（根据卡号查询发卡行）
     * @author Richard Core
     * @time 2017/1/4 14:13
     * @method getCardBinByCardNo
     */
    public CardBinDto getCardBinByCardNo(CardBinParams params);
    /**
     * 获取职位类型列表
     * Created by zhouqianqian on 2017/2/13.
     */
    public String getJobTypeList();
    /**
     * 获取联系人关系
     * Created by zhouqianqian on 2017/2/13.
     */
    public String getRelation(String product,String value);
    /**
     * 获取学历列表
     * Created by zhouqianqian on 2017/2/13.
     */
    public String getEducation(String product);



}
