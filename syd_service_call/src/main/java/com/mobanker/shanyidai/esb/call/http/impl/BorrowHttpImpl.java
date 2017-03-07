package com.mobanker.shanyidai.esb.call.http.impl;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.shanyidai.dubbo.dto.borrow.BorrowInfoDto;
import com.mobanker.shanyidai.esb.call.http.BorrowHttp;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/11 16:52
 */
@Service
public class BorrowHttpImpl implements BorrowHttp {

    /**
     * @description 根据订单号，获取借款详情
     * @author hantongyang
     * @time 2017/1/11 16:07
     * @method getBorrowInfo
     * @param restUrl
     * @param borrowNid
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    @Override
    public ResponseEntity getBorrowInfo(String restUrl, String borrowNid) {
        if(StringUtils.isBlank(borrowNid)){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("borrowNid", borrowNid);
        ResponseEntity entity = CommonUtil.doGet(restUrl, params, ErrorConstant.ERROR_BORROW_GET_INFO);
        //由于要求使用自己的DTO，所以判断返回值并重新封装
        if(entity != null && ResponseBuilder.isFinished(entity) && entity.getData() != null){
            JSONObject json = (JSONObject) entity.getData();
            BorrowInfoDto borrowInfoDto = BeanUtil.parseJson(json.toJSONString(), BorrowInfoDto.class);
            entity.setData(borrowInfoDto);
        }
        return entity;
    }
}
