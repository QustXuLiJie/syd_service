package com.mobanker.shanyidai.esb.service.basedata;

import com.mobanker.shanyidai.dubbo.service.basedata.BaseDataDubboService;
import com.mobanker.shanyidai.esb.business.basedata.BaseDataBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.service.user.BankCardServiceImpl;

import javax.annotation.Resource;

/** 获取职业类型列表
 * Created by zhouqianqian on 2017/2/13.
 */

public class BaseDataServiceImpl implements BaseDataDubboService {
    public static final Logger logger = LogManager.getSlf4jLogger(BankCardServiceImpl.class);
    @Resource
    private BaseDataBusiness baseDataBusiness;
    /**
     * @return String
     * @description 获取职位列表
     * @author zhouqianqian
     * @method getJobTypeList
     */
    @Override
    public ResponseEntity getJobTypeList(){
        try {

            String jobTypeList = baseDataBusiness.getJobTypeList();

            return ResponseBuilder.normalResponse(jobTypeList);

        } catch (Exception e) {
            logger.error("获取职位类型列表异常", e);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_JOBTYPE_GET_INFO, e, this.getClass().getSimpleName(), "getJobTypeList");
        }

    }
    /**
     * @return String
     * @description 获取联系人关系
     * @author zhouqianqian
     * @method getJobTypeList
     */
    @Override
    public ResponseEntity getRelation(String product){
        try {

            String relationList = baseDataBusiness.getRelation(product);

            return ResponseBuilder.normalResponse(relationList);

        } catch (Exception e) {
            logger.error("获取联系人关系列表异常", e);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_RELATION_GET_INFO, e, this.getClass().getSimpleName(), "getRelation");
        }

    }

    /**
     * @return String
     * @description 获取学历列表
     * @author zhouqianqian
     * @method getEducation
     */
    @Override
    public ResponseEntity getEducation(String product){
        try {

            String educationList = baseDataBusiness.getEducation(product);

            return ResponseBuilder.normalResponse(educationList);

        } catch (Exception e) {
            logger.error("获取学历列表异常", e);
            return ResponseBuilder.errorResponse(ErrorConstant.ERROR_EDUCATION_GET_INFO, e, this.getClass().getSimpleName(), "getEducation");
        }

    }
}
