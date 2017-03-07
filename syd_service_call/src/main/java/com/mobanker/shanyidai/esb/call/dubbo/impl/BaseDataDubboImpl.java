package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.mobanker.basedata.contract.dto.CardBinDto;
import com.mobanker.basedata.contract.dto.EducationDto;
import com.mobanker.basedata.contract.dto.JobTypeDto;
import com.mobanker.basedata.contract.dto.RelationDto;
import com.mobanker.basedata.contract.manager.DubboBaseDataContract;
import com.mobanker.basedata.contract.params.CardBinParams;
import com.mobanker.basedata.contract.params.RelationParams;
import com.mobanker.framework.contract.dto.ResponseEntityDto;
import com.mobanker.shanyidai.esb.call.dubbo.BaseDataDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/1/4 13:50
 */
@Service
public class BaseDataDubboImpl implements BaseDataDubbo {
    public static final Logger logger = LogManager.getSlf4jLogger(BaseDataDubboImpl.class);
    @Resource
    private DubboBaseDataContract dubboBaseDataContract;
//    @Resource
//    private DubboBaseDataManager dubboBaseDataManager; 废弃

    /**
     * @param params
     * @return void
     * @description 根据卡bin查询银行行卡信息（根据卡号查询发卡行）
     * @author Richard Core
     * @time 2017/1/4 14:13
     * @method getCardBinByCardNo
     */
    @Override
    public CardBinDto getCardBinByCardNo(CardBinParams params) {
        ResponseEntityDto<CardBinDto> result = null;
        try {
            result = dubboBaseDataContract.getCardBinByCardNum(params);
        } catch (Exception e) {
            logger.error("根据卡bin查询银行卡信息异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL, e);
        }
        com.mobanker.shanyidai.esb.common.packet.ResponseEntity responseEntity = BeanUtil.cloneBean(result, com.mobanker.shanyidai.esb.common.packet.ResponseEntity.class);
        if (!ResponseBuilder.isSuccess(responseEntity))  {
            logger.warn("根据卡bin查询银行卡信息异常", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        return result.getData();
    }

    /**
     * @return String
     * @description 获取职位列表
     * @author zhouqianqian
     * @method getJobTypeList
     */
    @Override
    public String getJobTypeList() {
        ResponseEntityDto<List<JobTypeDto>> allJobType = null;
        try {
            allJobType = dubboBaseDataContract.getAllJobType();
        } catch (Exception e) {
            logger.error("查询职业类型列表异常", e);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), e.getMessage(), e);
        }
        if (allJobType == null || allJobType.getData() == null) {
            throw new EsbException(ErrorConstant.ERROR_RESPONSE);
        }
        com.mobanker.shanyidai.esb.common.packet.ResponseEntity responseEntity = BeanUtil.cloneBean(allJobType, com.mobanker.shanyidai.esb.common.packet.ResponseEntity.class);
        if (!ResponseBuilder.isSuccess(responseEntity)) {
            logger.error("查询职业类型列表异常" + responseEntity.getError() + responseEntity.getMsg());
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), responseEntity.getMsg());
        }
        String result = JSON.toJSONString(allJobType.getData());

        return result;
    }


    /**
     * @return String
     * @description 获取联系人关系
     * @author zhouqianqian
     * @method getRelation
     */
    @Override
    public String getRelation(String product, String value) {
        ResponseEntityDto<Map<String, List<RelationDto>>> allRelation = null;

        RelationParams relationParams = new RelationParams();

        String temp = null;
        List<String> temp2 = null;
        try {
            //temp = ZkConfigConstant.getByKey("basedata_relation_type").getZkValue();
            // temp=ZkConfigConstant.getByKey("basedata_relation_type").getDefaultValue();
            temp2 = new ArrayList<String>();
            temp2 = Splitter.on(",").omitEmptyStrings().splitToList(value);
            relationParams.setTypes(temp2);
            relationParams.setProduct(product);
            allRelation = dubboBaseDataContract.getRelationByTypes(relationParams);
        } catch (Exception e) {
            logger.error("查询联系人关系列表异常", e);
            throw new EsbException(ErrorConstant.ERROR_RELATION_GET_INFO.getCode(), e.getMessage(), e);
        }

        if (allRelation == null || allRelation.getData() == null) {
            throw new EsbException(ErrorConstant.ERROR_RESPONSE);
        }
        com.mobanker.shanyidai.esb.common.packet.ResponseEntity responseEntity = BeanUtil.cloneBean(allRelation, com.mobanker.shanyidai.esb.common.packet.ResponseEntity.class);
        if (!ResponseBuilder.isSuccess(responseEntity)) {
            logger.error("查询联系人关系列表异常" + responseEntity.getError() + responseEntity.getMsg());
            throw new EsbException(ErrorConstant.ERROR_RELATION_GET_INFO.getCode(), responseEntity.getMsg());
        }
        String result = JSON.toJSONString(allRelation.getData());

        return result;
    }


    /**
     * @return String
     * @description 获取学历列表
     * @author zhouqianqian
     * @method getEducation
     */
    @Override
    public String getEducation(String product) {
        ResponseEntityDto<List<EducationDto>> educationList = null;



        String temp = null;
        List<String> temp2 = null;
        try {

            educationList = dubboBaseDataContract.getAllEduType(product);
        } catch (Exception e) {
            logger.error("查询学历列表异常", e);
            throw new EsbException(ErrorConstant.ERROR_EDUCATION_GET_INFO.getCode(), e.getMessage(), e);
        }

        if (educationList == null || educationList.getData() == null) {
            throw new EsbException(ErrorConstant.ERROR_RESPONSE);
        }
        com.mobanker.shanyidai.esb.common.packet.ResponseEntity responseEntity = BeanUtil.cloneBean(educationList, com.mobanker.shanyidai.esb.common.packet.ResponseEntity.class);
        if (!ResponseBuilder.isSuccess(responseEntity)) {
            logger.error("查询学历列表列表异常" + responseEntity.getError() + responseEntity.getMsg());
            throw new EsbException(ErrorConstant.ERROR_EDUCATION_GET_INFO.getCode(), responseEntity.getMsg());
        }
        String result = JSON.toJSONString(educationList.getData());

        return result;
    }

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("text", "测试带链接的内容");
        json.put("img_url", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2321132798,597499114&fm=170&s=F3980F295C316E98D25095C7030070B3&w=640&h=342&img.JPEG");
        System.out.println(json.toJSONString());
    }
}
